package com.polling.queryrepository;

import com.polling.contact.dto.FindContactResponseDto;
import com.polling.entity.contact.status.ContactType;
import com.polling.poll.dto.comment.response.FindCommentResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.entity.comment.QComment.comment;
import static com.polling.entity.contact.QContact.contact;
import static com.polling.entity.member.QMember.member;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class ContactQueryRepositoryImpl implements ContactQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<FindContactResponseDto> findContactByMemberId(Long memberId) {
        return query
                .select((Projections.constructor(FindContactResponseDto.class,
                        contact.id,
                        contact.contactStatus,
                        contact.contactType,
                        contact.title,
                        contact.content)))
                .from(contact)
                .where(contact.member.id.eq(memberId))
                .fetch();
    }
}
