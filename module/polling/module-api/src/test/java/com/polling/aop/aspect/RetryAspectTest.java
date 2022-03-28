package com.polling.aop.aspect;

import com.polling.aop.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(RetryAspect.class)
@SpringBootTest
class RetryAspectTest {

  @Autowired
  ExamService service;

  @Test
  public void Retry적용() throws Exception {
    //given

    //when
    for (int i = 0; i < 5; i++) {
      service.requestKeep(i);
    }

    //then
  }
}