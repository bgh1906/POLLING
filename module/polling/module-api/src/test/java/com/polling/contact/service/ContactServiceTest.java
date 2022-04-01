package com.polling.contact.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.polling.contact.dto.SaveContactRequestDto;
import com.polling.entity.contact.Contact;
import com.polling.entity.contact.status.ContactType;
import com.polling.entity.member.Member;
import com.polling.repository.contact.ContactRepository;
import com.polling.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContactServiceTest {

  @Autowired
  ContactService contactService;
  @Autowired
  ContactRepository contactRepository;
  @Autowired
  MemberRepository memberRepository;

  @Test
  void save_test() {
    Member member = Member.builder()
        .email("test111@gmail.com")
        .nickname("test")
        .password("asda")
        .phoneNumber("11114444124")
        .build();
    SaveContactRequestDto requestDto = new SaveContactRequestDto("회원", "title",
        "content", member.getEmail());

    Member savedMember = memberRepository.save(member);
    contactService.save(requestDto, savedMember.getId());

    //
    Contact savedContact = contactRepository.findAll().get(0);
    assertEquals("title", savedContact.getTitle());
  }

}