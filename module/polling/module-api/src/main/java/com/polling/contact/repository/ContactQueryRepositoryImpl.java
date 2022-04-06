package com.polling.contact.repository;

import static com.polling.contact.entity.QContact.contact;

import com.polling.contact.dto.FindContactPageResponseDto;
import com.polling.contact.dto.FindContactResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class ContactQueryRepositoryImpl implements ContactQueryRepository {

  private final JPAQueryFactory query;

  @Override
  public List<FindContactResponseDto> findByMemberId(Long memberId) {
    return query
        .select((Projections.constructor(FindContactResponseDto.class,
            contact.id,
            contact.contactStatus,
            contact.contactType,
            contact.title,
            contact.content,
            contact.answer,
            contact.email)))
        .from(contact)
        .where(contact.member.id.eq(memberId))
        .fetch();
  }

  @Override
  public List<FindContactPageResponseDto> findPageOrderByCreateDate(int page, int limit) {
    return query
        .select((Projections.constructor(FindContactPageResponseDto.class,
            contact.id,
            contact.member.id,
            contact.contactStatus,
            contact.contactType,
            contact.title,
            contact.content,
            contact.answer,
            contact.email)))
        .from(contact)
        .orderBy(contact.createdDate.desc())
        .offset(page)
        .limit(limit)
        .fetch();
  }
}
