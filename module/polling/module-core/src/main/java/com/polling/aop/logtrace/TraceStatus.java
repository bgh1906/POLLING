package com.polling.aop.logtrace;

import lombok.Getter;

@Getter
public class TraceStatus {

  private final TraceId traceId;
  private final Long startTimeMillis;
  private final String message;

  public TraceStatus(TraceId traceId, Long startTimeMillis, String message) {
    this.traceId = traceId;
    this.startTimeMillis = startTimeMillis;
    this.message = message;
  }

}
