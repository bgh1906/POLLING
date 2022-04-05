package com.polling.contact.entity;

import com.polling.common.entity.BaseTimeEntity;
import com.polling.contact.entity.status.ContactStatus;
import com.polling.contact.entity.status.ContactType;
import com.polling.member.entity.Member;
import com.querydsl.core.annotations.QueryEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@QueryEntity
public class Contact extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "contact_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Enumerated(EnumType.STRING)
  private ContactStatus contactStatus = ContactStatus.UNANSWERED;

  @Enumerated(EnumType.STRING)
  private ContactType contactType;

  private String title;

  @Column(length = 2000)
  private String content;

  @Column(length = 2000)
  private String answer;

  private String email;

  @Builder
  public Contact(Member member, ContactType contactType,
      String title, String content, String answer, String email) {
    this.member = member;
    this.contactType = contactType;
    this.title = title;
    this.content = content;
    this.answer = answer;
    this.email = email;
  }

  public void changeMember(Member member) {
    this.member = member;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
