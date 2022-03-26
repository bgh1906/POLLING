package com.polling.aop.logtrace;

import lombok.Getter;

@Getter
public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMillis;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMillis, String message) {
        this.traceId = traceId;
        this.startTimeMillis = startTimeMillis;
        this.message = message;
    }

}
