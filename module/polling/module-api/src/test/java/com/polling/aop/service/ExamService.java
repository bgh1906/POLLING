package com.polling.aop.service;

import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {
    private final InternalService internalService;

    @Trace
    public void request(String data){
        internalService.internalCall();
        log.info("=================" + data + " receive =================");
    }

    @Retry
    public void requestKeep(int data) throws IllegalAccessException {
        if(data == (int)(Math.random() * 100) / 5)
            throw new IllegalAccessException();
        log.info("=====================" + data + " keep going================");
    }
}
