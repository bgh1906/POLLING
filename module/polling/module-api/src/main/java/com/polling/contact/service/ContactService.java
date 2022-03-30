package com.polling.contact.service;

import com.polling.aop.annotation.Trace;
import com.polling.contact.dto.SaveAnswerRequestDto;
import com.polling.contact.dto.SaveContactRequestDto;
import com.polling.entity.contact.Contact;
import com.polling.entity.contact.status.ContactStatus;
import com.polling.entity.member.Member;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.repository.contact.ContactRepository;
import com.polling.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final MemberRepository memberRepository;

    @Trace
    @Transactional
    public void save(SaveContactRequestDto requestDto, Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorResult.USER_NOT_FOUND));
        Contact contact = Contact.builder()
                .contactStatus(ContactStatus.UNANSWERED)
                .contactType(requestDto.getContactType())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .member(member)
                .build();
        contactRepository.save(contact);
    }

    @Trace
    @Transactional
    public void saveAnswer(SaveAnswerRequestDto requestDto) {
        Contact contact = contactRepository.findById(requestDto.getContactId())
                .orElseThrow(() -> new CustomException(CustomErrorResult.CONTACT_NOT_FOUND));
        contact.setAnswer(requestDto.getAnswer());

    }
}
