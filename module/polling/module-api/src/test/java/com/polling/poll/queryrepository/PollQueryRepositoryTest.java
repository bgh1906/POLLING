package com.polling.poll.queryrepository;

import static org.assertj.core.api.Assertions.assertThat;

import com.polling.entity.poll.Poll;
import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.response.FindPollPageResponseDto;
import com.polling.queryrepository.PollQueryRepository;
import com.polling.repository.poll.PollRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@Slf4j
public class PollQueryRepositoryTest {

  @Autowired
  private PollRepository pollRepository;
  @Autowired
  private PollQueryRepository pollQueryRepository;


  @Test
  public void 투표페이지조회_현재진행중() throws Exception {
    //given
    String format = "2022-03-31 23:59";
    LocalDateTime end = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        .atStartOfDay();
    Poll poll1 = createPoll(end.minusDays(5), end);
    Poll poll2 = createPoll(end.minusDays(5), end);

    poll1.changePollStatus(PollStatus.DONE);
    poll2.changePollStatus(PollStatus.IN_PROGRESS);

    pollRepository.save(poll1);
    Poll savedPoll2 = pollRepository.save(poll2);

    //when
    List<FindPollPageResponseDto> result = pollQueryRepository.findPollPageByStatus(
        PollStatus.IN_PROGRESS, 0, 10);

    //then
    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0).getId()).isEqualTo(savedPoll2.getId());
  }

  @Test
  public void 투표페이지조회_결과() throws Exception {
    //given
    String format = "2022-03-31 23:59";
    LocalDateTime end = LocalDateTime.parse(format,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    Poll poll1 = createPoll(end.minusDays(5), end);
    Poll poll2 = createPoll(end.minusDays(5), end);

    poll1.changePollStatus(PollStatus.DONE);
    poll2.changePollStatus(PollStatus.IN_PROGRESS);
    Poll savedPoll = pollRepository.save(poll1);
    pollRepository.save(poll2);

    //when
    List<FindPollPageResponseDto> result = pollQueryRepository.findPollPageByStatus(
        PollStatus.DONE, 0, 10);

    //then
    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0).getId()).isEqualTo(savedPoll.getId());
  }

  @Test
  public void 투표조회_종료시간이끝난() throws Exception {
    //given
    String format = "2022-03-31 23:59";
    LocalDateTime end = LocalDateTime.parse(format,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    log.trace("================================={}", end.plusMinutes(2));
    Poll poll1 = createPoll(end.minusDays(5), end);
    Poll poll2 = createPoll(end.minusDays(5), end.plusMinutes(2));
    poll1.changePollStatus(PollStatus.IN_PROGRESS);
    poll2.changePollStatus(PollStatus.IN_PROGRESS);

    pollRepository.save(poll1);
    pollRepository.save(poll2);
    format = "2022-04-01 00:00";
    LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        .atStartOfDay();

    //when
    List<Poll> findList = pollQueryRepository.findByCurrentBeforeEndTime(current);

    //then
    assertThat(findList.size()).isEqualTo(1);
    assertThat(findList.get(0).getEndDate()).isEqualTo(end);
  }

  private Poll createPoll(LocalDateTime start, LocalDateTime end) {
    return Poll.builder()
        .title("title")
        .content("testContent")
        .startDate(start)
        .endDate(end)
        .build();
  }
}
