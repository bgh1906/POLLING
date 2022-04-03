package com.polling.poll.queryrepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.EntityManager;

import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import com.polling.poll.poll.dto.response.FindPollPageResponseDto;
import com.polling.poll.poll.entity.Poll;
import com.polling.poll.poll.entity.status.PollStatus;
import com.polling.poll.poll.repository.PollQueryRepository;
import com.polling.poll.poll.repository.PollRepository;
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
  @Autowired
  private EntityManager em;


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
    assertThat(result.get(0).getPollId()).isEqualTo(savedPoll2.getId());
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
    assertThat(result.get(0).getPollId()).isEqualTo(savedPoll.getId());
  }

  @Test
  public void 투표조회_종료시간이끝난_데이터없음() throws Exception {
    //given
    String format = "2022-03-31 23:59";
    LocalDateTime end = LocalDateTime.parse(format,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    log.trace("================================={}", end.plusMinutes(2));
    Poll poll1 = createPoll(end.minusDays(5), end.plusMinutes(3));
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
    assertThat(findList.size()).isEqualTo(0);
    assertThat(findList.isEmpty()).isTrue();
  }


  @Test
  public void 투표조회_종료시간이끝난_데이터있음() throws Exception {
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

  @Test
  public void 투표조회_시작시간이된_데이터있음() throws Exception {
    //given
    String format = "2022-03-31 23:59";
    LocalDateTime start = LocalDateTime.parse(format,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    log.trace("================================={}", start.plusMinutes(2));
    Poll poll1 = createPoll(start, start.plusDays(3));
    Poll poll2 = createPoll(start.plusDays(1), start.plusDays(3));
    poll1.changePollStatus(PollStatus.WAIT);
    poll2.changePollStatus(PollStatus.WAIT);

    pollRepository.save(poll1);
    pollRepository.save(poll2);

    format = "2022-04-01 00:00";
    LocalDateTime current = LocalDateTime.parse(format,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    //when
    List<Poll> findList = pollQueryRepository.findByCurrentBeforeStartTime(current);

    //then
    assertThat(findList.size()).isEqualTo(1);
    assertThat(findList.get(0).getStartDate()).isEqualTo(start);
  }

  @Test
  public void 투표삭제_단일() throws Exception {
    //given
    String format = "2022-03-31 23:59";
    LocalDateTime start = LocalDateTime.parse(format,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    log.trace("================================={}", start.plusMinutes(2));
    Poll poll1 = createPoll(start, start.plusDays(3));
    poll1.changePollStatus(PollStatus.WAIT);
    Candidate candidate = Candidate.builder().build();
    candidate.addGallery(new CandidateGallery("image1"));
    candidate.addGallery(new CandidateGallery("image2"));
    candidate.addGallery(new CandidateGallery("image3"));
    poll1.addCandidate(Candidate.builder().build());
    poll1.addCandidate(Candidate.builder().build());
    poll1.addCandidate(Candidate.builder().build());
    poll1.addCandidate(Candidate.builder().build());
    poll1.addCandidate(Candidate.builder().build());
    poll1.addCandidate(Candidate.builder().build());

    Long id = pollRepository.save(poll1).getId();
    em.flush();
    em.clear();

    //when
    pollQueryRepository.deleteImageByPollId(id);
    pollQueryRepository.deleteCandidateByPollId(id);
    pollRepository.deleteById(id);

    //then
    assertThat(pollRepository.count()).isEqualTo(0);
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
