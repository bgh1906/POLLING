package com.polling.member.repository;

import static com.polling.member.entity.QMember.member;

import com.polling.member.dto.response.FindMemberResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

  private final JPAQueryFactory query;

  @Override
  public List<FindMemberResponseDto> findAll(int offset, int limit) {
    return query
        .select((Projections.constructor(FindMemberResponseDto.class,
            member.id,
            member.nickname,
            member.wallet,
            member.email)))
        .from(member)
        .orderBy(member.createdDate.desc())
        .offset(offset)
        .limit(limit)
        .fetch();
  }
}
