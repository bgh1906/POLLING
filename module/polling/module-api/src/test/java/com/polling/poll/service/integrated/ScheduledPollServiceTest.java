package com.polling.poll.service.integrated;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.polling.poll.service.PollService;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class ScheduledPollServiceTest {

  @SpyBean
  private PollService pollService;

  @Test
  public void 투표끝났는지검사_1분뒤() throws Exception {
    await()
        .atMost(Duration.ofSeconds(61))
        .untilAsserted(() -> verify(pollService, times(1)).checkPollEndTime());
  }
}
