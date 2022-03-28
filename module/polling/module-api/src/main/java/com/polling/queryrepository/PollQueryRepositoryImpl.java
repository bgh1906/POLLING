package com.polling.queryrepository;


import static com.polling.entity.poll.QPoll.poll;

import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.response.FindPollPageResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class PollQueryRepositoryImpl implements PollQueryRepository {

  private final JPAQueryFactory query;

  @Override
  public List<FindPollPageResponseDto> findPollPageByStatus(PollStatus pollStatus, int offset,
      int limit) {
    return query
        .select(Projections.constructor(FindPollPageResponseDto.class,
            poll.id,
            poll.title,
            poll.thumbnail,
            poll.startDate,
            poll.endDate))
        .from(poll)
        .where(poll.pollStatus.eq(pollStatus))
        .orderBy(poll.createdDate.desc())
        .offset(offset)
        .limit(limit)
        .fetch();
  }
}