package com.polling.aop.service;

import com.polling.aop.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class InternalService {

    @Trace
    public void internalCall(){
        log.info("================= internalCall ================");
    }
}
