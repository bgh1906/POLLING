package com.polling.api.controller.member;

import com.polling.api.controller.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.api.controller.member.dto.request.UpdateMemberRequestDto;
import com.polling.api.controller.member.dto.response.FindMemberResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

    @PostMapping
    @ApiOperation(value = "회원 가입")
    public ResponseEntity<Void> save(@RequestBody SaveNativeMemberRequestDto requestDto) {
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "회원 정보 조회")
    public ResponseEntity<FindMemberResponseDto> get(@PathVariable Long id) {
        FindMemberResponseDto responseDto = new FindMemberResponseDto();
        return ResponseEntity.status(200).body(responseDto);
    }

    @PatchMapping
    @ApiOperation(value = "회원 정보 수정")
    public ResponseEntity<Void> update(@RequestBody UpdateMemberRequestDto requestDto) {
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/role")
    @ApiOperation(value = "회원 권한 수정")
    public ResponseEntity<Void> updateRole(@RequestBody UpdateMemberRequestDto requestDto) {
        return ResponseEntity.status(200).build();
    }
}
