package com.polling.contact.controller;

import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.auth.CurrentUser;
import com.polling.auth.dto.MemberDto;
import com.polling.contact.dto.FindContactPageResponseDto;
import com.polling.contact.dto.FindContactResponseDto;
import com.polling.contact.dto.SaveContactRequestDto;
import com.polling.contact.repository.ContactQueryRepository;
import com.polling.contact.repository.ContactRepository;
import com.polling.contact.service.ContactService;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.status.MemberRole;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FAQ REST API
 */
@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

  private final ContactService contactService;
  private final ContactRepository contactRepository;
  private final ContactQueryRepository contactQueryRepository;

  @Retry
  @PostMapping
  @ApiOperation(value = "1:1문의 등록")
  public ResponseEntity<Void> save(@CurrentUser MemberDto memberDto,
      @RequestBody SaveContactRequestDto requestDto) {
    contactService.save(requestDto, memberDto.getId());
    return ResponseEntity.status(200).build();
  }

  @Trace
  @DeleteMapping()
  @ApiOperation(value = "1:1문의 취소")
  public ResponseEntity<Void> delete(@CurrentUser MemberDto memberDto) {
    contactRepository.deleteById(memberDto.getId());
    return ResponseEntity.status(200).build();
  }

  @Trace
  @GetMapping
  @ApiOperation(value = "내 문의 전체조회")
  public ResponseEntity<List<FindContactResponseDto>> getContacts(
      @CurrentUser MemberDto memberDto) {
    List<FindContactResponseDto> list = contactQueryRepository.findByMemberId(
        memberDto.getId());
    return ResponseEntity.status(200).body(list);
  }

  @Trace
  @GetMapping("/admin/{page}/{limit}")
  @ApiOperation(value = "1:1문의 전체조회")
  public ResponseEntity<List<FindContactPageResponseDto>> getAllContacts(
      @CurrentUser MemberDto memberDto, @PathVariable int page, @PathVariable int limit) {
    validateMemberRole(memberDto);
    List<FindContactPageResponseDto> list = contactQueryRepository.findPageOrderByCreateDate(page,
        limit);
    return ResponseEntity.status(200).body(list);
  }

  private void validateMemberRole(MemberDto memberDto) {
    if (!(memberDto.getMemberRole().contains(MemberRole.ROLE_ADMIN) || memberDto.getMemberRole()
        .contains(MemberRole.ROLE_COMPANY))) {
      throw new CustomException(CustomErrorResult.UNAUTHORIZED_MEMBER_ROLE);
    }
  }
}
