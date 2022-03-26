package com.polling.repository.poll;

import com.polling.config.JpaConfig;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.poll.Poll;
import com.polling.entity.poll.status.PollStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@Import(JpaConfig.class)
@DataJpaTest
public class PollRepositoryTest {

    @Autowired
    private PollRepository pollRepository;

    private final String title = "test_poll_title";

    @Test
    public void pollRepositoryIsNotNull() throws Exception{
        assertThat(pollRepository).isNotNull();
    }
    
    @Test
    public void 투표생성() throws Exception{
        //given
        String format = "2022-04-01";
        LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        Poll poll = Poll.builder()
                .title(title)
                .content("hello")
                .startDate(current)
                .endDate(current.plusDays(10))
                .build();

        //when
        Poll savedPoll = pollRepository.save(poll);

        //then
        assertThat(savedPoll.getId()).isNotNull();
        assertThat(savedPoll.getTitle()).isEqualTo(title);
        assertThat(savedPoll.getContent()).isEqualTo("hello");
        assertThat(savedPoll.getPollStatus()).isEqualTo(PollStatus.UNAPPROVED);
        assertThat(savedPoll.getStartDate()).isEqualTo(current);
        assertThat(savedPoll.getEndDate()).isEqualTo(current.plusDays(10));
    }
    
    @Test
    public void 투표조회() throws Exception{
        //given
        String format = "2022-04-01";
        LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        createPoll(current, current.plusDays(10));

        //when
        Poll findPoll = pollRepository.findByTitle(title).orElseThrow();

        //then
        assertThat(findPoll.getId()).isNotNull();
        assertThat(findPoll.getTitle()).isEqualTo(title);
        assertThat(findPoll.getContent()).isEqualTo("hello");
        assertThat(findPoll.getPollStatus()).isEqualTo(PollStatus.UNAPPROVED);
        assertThat(findPoll.getStartDate()).isEqualTo(current);
        assertThat(findPoll.getEndDate()).isEqualTo(current.plusDays(10));
    }

    @Test
    public void 투표변경_제목내용() throws Exception{
        //given
        String format = "2022-04-01";
        LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        createPoll(current, current.plusDays(10));

        //when
        Poll findPoll = pollRepository.findByTitle(title).orElseThrow();
        findPoll.changeDescription("changeTitle", "changeContent");

        //then
        Poll changedPoll = pollRepository.findByTitle("changeTitle").orElseThrow();
        assertThat(changedPoll.getTitle()).isEqualTo("changeTitle");
        assertThat(changedPoll.getContent()).isEqualTo("changeContent");
    }

    @Test
    public void 투표삭제() throws Exception{
        //given
        String format = "2022-04-01";
        LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        Poll savedPoll = createPoll(current, current.plusDays(10));

        //when
        pollRepository.delete(savedPoll);

        //then
        assertThat(pollRepository.count()).isEqualTo(0);
    }

    @Test
    public void 후보자추가() throws Exception{
        //given
        String format = "2022-04-01";
        LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        Poll savedPoll = createPoll(current, current.plusDays(10));

        //when
        savedPoll.addCandidate(Candidate.builder().build());
        Poll findPoll = pollRepository.findById(savedPoll.getId()).orElseThrow();

        //then
        assertThat(findPoll.getCandidates().size()).isGreaterThan(5);
    }

    private Poll createPoll(LocalDateTime start, LocalDateTime end){
        Poll poll = Poll.builder()
                .title(title)
                .content("hello")
                .pollCreator(null)
                .startDate(start)
                .endDate(end)
                .build();
        poll.addCandidate(Candidate.builder().build());
        poll.addCandidate(Candidate.builder().build());
        poll.addCandidate(Candidate.builder().build());
        poll.addCandidate(Candidate.builder().build());
        poll.addCandidate(Candidate.builder().build());
        poll.addCandidate(Candidate.builder().build());
        poll.addCandidate(Candidate.builder().build());
        return pollRepository.save(poll);
    }

}
