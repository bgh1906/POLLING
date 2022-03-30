package com.polling.contact.controller;

import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.auth.dto.MemberDto;
import com.polling.contact.dto.FindAllContactResponseDto;
import com.polling.contact.dto.FindContactResponseDto;
import com.polling.contact.dto.SaveAnswerRequestDto;
import com.polling.contact.dto.SaveContactRequestDto;
import com.polling.contact.service.ContactService;
import com.polling.queryrepository.ContactQueryRepository;
import com.polling.repository.contact.ContactRepository;
import com.polling.security.CurrentUser;
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
  @DeleteMapping("/{id}")
  @ApiOperation(value = "1:1문의 취소")
  public ResponseEntity<Void> delete(@CurrentUser MemberDto memberDto, @PathVariable Long id) {
    contactRepository.deleteById(id);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @GetMapping
  @ApiOperation(value = "1:1문의 전체조회")
  public ResponseEntity<List<FindContactResponseDto>> getContacts(
      @CurrentUser MemberDto memberDto) {
    List<FindContactResponseDto> list = contactQueryRepository.findContactByMemberId(
        memberDto.getId());
    return ResponseEntity.status(200).body(list);
  }

  @Trace
  @GetMapping("/admin")
  @ApiOperation(value = "1:1문의 전체조회")
  public ResponseEntity<List<FindAllContactResponseDto>> getAllContacts(
      @CurrentUser MemberDto memberDto) {
    List<FindAllContactResponseDto> list = contactQueryRepository.findAllContact();
    return ResponseEntity.status(200).body(list);
  }

  @Trace
  @PostMapping("/admin")
  @ApiOperation(value = "1:1문의 답변")
  public ResponseEntity<Void> response(@CurrentUser MemberDto memberDto,
      SaveAnswerRequestDto requestDto) {
    contactService.saveAnswer(requestDto);
    return ResponseEntity.status(200).build();
  }
}
