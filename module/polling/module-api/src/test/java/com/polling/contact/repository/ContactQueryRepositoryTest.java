package com.polling.contact.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.polling.contact.dto.FindContactPageResponseDto;
import com.polling.contact.dto.FindContactResponseDto;
import com.polling.contact.dto.SaveContactRequestDto;
import com.polling.contact.entity.Contact;
import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class ContactQueryRepositoryTest {

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private ContactQueryRepository contactQueryRepository;

  @Autowired
  private EntityManager em;

  @Test
  public void 내문의전체조회() throws Exception {
    //given
    final SaveContactRequestDto requestDto =
        new SaveContactRequestDto("기업", "title", "content", "email");
    Contact contact = requestDto.toEntity();
    Member savedMember = memberRepository.save(Member.builder().build());

    contact.changeMember(savedMember);
    contactRepository.save(contact);

    //when
    List<FindContactResponseDto> myFAQ = contactQueryRepository.findByMemberId(
        savedMember.getId());

    //then
    assertThat(myFAQ.size()).isEqualTo(1);
  }

  @Test
  public void 관리자문의전체조회() throws Exception {
    //given
    final SaveContactRequestDto requestDto =
        new SaveContactRequestDto("기업", "title", "content", "email");
    Contact contact = requestDto.toEntity();
    Member savedMember = memberRepository.save(Member.builder().build());
    contact.changeMember(savedMember);
    contactRepository.save(contact);

    final SaveContactRequestDto requestDto2 =
        new SaveContactRequestDto("기업", "title", "content", "email");
    Contact contact2 = requestDto2.toEntity();
    Member savedMember2 = memberRepository.save(Member.builder().build());
    contact2.changeMember(savedMember2);
    contactRepository.save(contact2);

    em.flush();
    em.clear();

    //when
    List<FindContactPageResponseDto> allFAQ = contactQueryRepository.findPageOrderByCreateDate(0,
        50);

    //then
    assertThat(allFAQ.size()).isEqualTo(2);
  }
}
