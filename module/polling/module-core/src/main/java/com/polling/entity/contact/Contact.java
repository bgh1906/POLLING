package com.polling.entity.contact;

import com.polling.entity.common.BaseTimeEntity;
import com.polling.entity.contact.status.ContactStatus;
import com.polling.entity.contact.status.ContactType;
import com.polling.entity.member.Member;
import com.querydsl.core.annotations.QueryEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_CONTACT")
@Entity
@QueryEntity
public class Contact extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "contact_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Member member;

  @Column
  @Enumerated
  private ContactStatus contactStatus = ContactStatus.UNANSWERED;

  @Column
  @Enumerated
  private ContactType contactType;

  @Column
  private String title;

  @Column(length = 2000)
  private String content;

  @Column(length = 2000)
  private String answer;

  @Builder
  public Contact(Member member, ContactType contactType,
      String title, String content, String answer) {
    this.member = member;
    this.contactType = contactType;
    this.title = title;
    this.content = content;
    this.answer = answer;
  }

  public void changeMember(Member member){
    this.member = member;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
