package com.polling.contact.service;

import com.polling.contact.dto.SaveContactRequestDto
import com.polling.contact.entity.Contact;
import com.polling.contact.repository.ContactRepository;
import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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