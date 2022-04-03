package com.polling.contact.service;

import com.polling.aop.annotation.Trace;
import com.polling.contact.dto.SaveAnswerRequestDto;
import com.polling.contact.dto.SaveContactRequestDto;
import com.polling.contact.entity.Contact;
import com.polling.contact.repository.ContactRepository;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 1:1 문의 서비스
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ContactService {

  private final ContactRepository contactRepository;
  private final MemberRepository memberRepository;

  @Trace
  @Transactional
  public void save(SaveContactRequestDto requestDto, Long memberId) {
    Member member = getMember(memberId);
    Contact contact = requestDto.toEntity();
    contact.changeMember(member);
    contactRepository.save(contact);
  }

  @Trace
  @Transactional
  public void saveAnswer(SaveAnswerRequestDto requestDto) {
    Contact contact = getContact(requestDto.getContactId());
    contact.setAnswer(requestDto.getAnswer());
  }

  private Member getMember(Long memberId) {
    return memberRepository.findById(memberId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.USER_NOT_FOUND));
  }

  private Contact getContact(Long contactId) {
    return contactRepository.findById(contactId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.CONTACT_NOT_FOUND));
  }
}
