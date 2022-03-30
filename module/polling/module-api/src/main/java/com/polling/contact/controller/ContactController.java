package com.polling.contact.controller;

import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.auth.dto.MemberDto;
import com.polling.contact.dto.FindContactResponseDto;
import com.polling.contact.dto.SaveContactRequestDto;
import com.polling.security.CurrentUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {


  @Retry
  @PostMapping
  @ApiOperation(value = "1:1문의 등록")
  public ResponseEntity<Void> save(@RequestBody SaveContactRequestDto requestDto) {
    return ResponseEntity.status(200).build();
  }

  @Trace
  @DeleteMapping("/{id}")
  @ApiOperation(value = "1:1문의 취소")
  public ResponseEntity<Void> delete(@CurrentUser MemberDto memberDto, @PathVariable Long id) {
    return ResponseEntity.status(200).build();
  }

  @Trace
  @GetMapping
  @ApiOperation(value = "1:1문의 전체조회")
  public ResponseEntity<FindContactResponseDto> getContacts(@CurrentUser MemberDto memberDto) {
    FindContactResponseDto responseDto = new FindContactResponseDto();
    return ResponseEntity.status(200).body(responseDto);
  }


  @Trace
  @PatchMapping("/admin")
  @ApiOperation(value = "1:1문의 답변")
  public ResponseEntity<Void> answer(@PathVariable Long id) {
//    memberService.addAdminRole(id);
    return ResponseEntity.status(200).build();
  }
}
