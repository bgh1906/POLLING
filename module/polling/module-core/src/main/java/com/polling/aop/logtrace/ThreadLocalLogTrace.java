package com.polling.aop.logtrace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ThreadLocalLogTrace implements LogTrace {

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX = "<X-";

  private final ThreadLocal<TraceId> traceHolder = new ThreadLocal<>();

  @Override
  public TraceStatus begin(String message) {
    syncTraceId();
    TraceId traceId = traceHolder.get();
    long startTimeMillis = System.currentTimeMillis();
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
    return new TraceStatus(traceId, startTimeMillis, message);
  }

  private void syncTraceId() {
    TraceId traceId = traceHolder.get();
    if (traceId == null) {
      traceHolder.set(new TraceId());
    } else {
      traceHolder.set(traceId.createNextId());
    }
  }

  @Override
  public void end(TraceStatus status) {
    complete(status, null);
  }

  @Override
  public void exception(TraceStatus status, Exception e) {
    complete(status, e);
  }

  private void complete(TraceStatus status, Exception e) {
    Long stopTimeMillis = System.currentTimeMillis();
    Long resultTimeMillis = stopTimeMillis - status.getStartTimeMillis();
    TraceId traceId = status.getTraceId();
    if (e == null) {
      log.info("[{}] {}{} time={}ms", traceId.getId(),
          addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
          resultTimeMillis);
    } else {
      log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
          addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMillis,
          e.toString());
    }

    releaseTraceId();
  }

  private void releaseTraceId() {
    TraceId traceId = traceHolder.get();
    if (traceId.isFirstLevel()) {
      traceHolder.remove();
    } else {
      traceHolder.set(traceId.createPreviousId());
    }
  }

  private Object addSpace(String prefix, int level) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < level; i++) {
      sb.append((i == level - 1) ? "|" + prefix : "|   ");
    }
    return sb.toString();
  }
}
