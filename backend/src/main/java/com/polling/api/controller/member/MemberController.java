package com.polling.api.controller.member;


import com.polling.api.controller.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.api.controller.member.dto.request.UpdateMemberRequestDto;
import com.polling.api.controller.member.dto.response.FindMemberResponseDto;
import com.polling.api.service.member.MemberService;
import com.polling.common.security.CurrentUser;
import com.polling.common.security.dto.MemberDto;
import com.polling.core.entity.member.status.MemberRole;
import com.polling.core.repository.member.MemberRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping
    @ApiOperation(value = "회원 가입")
    public ResponseEntity<Void> save(@RequestBody SaveNativeMemberRequestDto requestDto) {
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{nickname}")
    @ApiOperation(value = "이름 중복체크")
    public ResponseEntity<Void> checkNickname(@PathVariable String nickname) {
        memberService.checkDuplicateMemberNickname(nickname);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<Void> delete() {
        Long id=364L;
        memberRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "회원 정보 조회")
    public ResponseEntity<FindMemberResponseDto> get(@PathVariable Long id) {
        FindMemberResponseDto responseDto = new FindMemberResponseDto();
        return ResponseEntity.status(200).body(responseDto);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "회원 정보 수정")
    public ResponseEntity<Void> update(@CurrentUser MemberDto memberDto, @RequestBody UpdateMemberRequestDto requestDto) {

        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/role/{id}")
    @ApiOperation(value = "회원 권한 수정")
    public ResponseEntity<Void> updateRole(@PathVariable Long id, Set<MemberRole> memberRole) {
        memberService.updateRole(id, memberRole);
        return ResponseEntity.status(200).build();
    }
}
