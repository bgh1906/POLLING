package com.polling.aop.aspect;

import com.polling.aop.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(LogTraceAspect.class)
@SpringBootTest
class LogTraceAspectTest {

  @Autowired
  ExamService service;

  @Test
  public void LogTrace적용() throws Exception {
    //given

    //when
    service.request("1");

    //then
  }
}