package com.polling.poll.queryrepository;

import static org.assertj.core.api.Assertions.assertThat;

import com.polling.entity.poll.Poll;
import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.response.FindPollPageResponseDto;
import com.polling.queryrepository.PollQueryRepository;
import com.polling.repository.poll.PollRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class PollQueryRepositoryTest {

  @Autowired
  private PollRepository pollRepository;
  @Autowired
  private PollQueryRepository pollQueryRepository;


  @Test
  public void 투표페이지조회_현재진행중() throws Exception {
    //given
    Poll savedPoll1 = pollRepository.save(createPoll("testTitle1"));
    pollRepository.save(createPoll("testTitle2"));
    savedPoll1.changePollStatus(PollStatus.IN_PROGRESS);

    //when
    List<FindPollPageResponseDto> result = pollQueryRepository.findPollPageByStatus(
        PollStatus.IN_PROGRESS, 0, 10);

    //then
    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0).getTitle()).isEqualTo("testTitle1");
    assertThat(result.get(0).getId()).isEqualTo(savedPoll1.getId());
  }

  @Test
  public void 투표페이지조회_결과() throws Exception {
    //given
    Poll savedPoll1 = pollRepository.save(createPoll("testTitle1"));
    pollRepository.save(createPoll("testTitle2"));
    savedPoll1.changePollStatus(PollStatus.DONE);

    //when
    List<FindPollPageResponseDto> result = pollQueryRepository.findPollPageByStatus(
        PollStatus.DONE, 0, 10);

    //then
    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0).getTitle()).isEqualTo("testTitle1");
    assertThat(result.get(0).getId()).isEqualTo(savedPoll1.getId());
  }

  private Poll createPoll(String title) {
    return Poll.builder()
        .title(title)
        .content("testContent")
        .build();
  }
}
