package com.polling.member.repository;

import com.polling.member.dto.response.FindMemberResponseDto;
import com.polling.poll.candidate.dto.response.FindCandidateHistoryResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.member.entity.QMember.member;
import static com.polling.poll.candidate.entity.QCandidateHistory.candidateHistory;

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
