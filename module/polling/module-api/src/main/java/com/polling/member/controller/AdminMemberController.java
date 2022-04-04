package com.polling.member.controller;

import com.polling.aop.annotation.Trace;
import com.polling.auth.CurrentUser;
import com.polling.auth.dto.MemberDto;
import com.polling.member.dto.response.FindMemberResponseDto;
import com.polling.member.repository.MemberQueryRepository;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/members")
@RequiredArgsConstructor
public class AdminMemberController {

  private final MemberQueryRepository memberQueryRepository;

  @Trace
  @GetMapping("/{page}/{limit}")
  @ApiOperation(value = "회원 정보 조회")
  public ResponseEntity<List<FindMemberResponseDto>> getMembers(@CurrentUser MemberDto memberDto,
      @PathVariable int page, @PathVariable int limit) {
    List<FindMemberResponseDto> responseDto = memberQueryRepository.findAll(page, limit);
    return ResponseEntity.status(200).body(responseDto);
  }
}
