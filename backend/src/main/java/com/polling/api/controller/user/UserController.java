package com.polling.api.controller.user;

import com.polling.api.controller.user.dto.request.UpdateUserRequestDto;
import com.polling.api.controller.user.dto.request.SaveNativeUserRequestDto;
import com.polling.api.controller.user.dto.response.FindUserResponseDto;
import com.polling.api.controller.user.dto.response.UpdateUserResponseDto;
import com.polling.api.service.user.UserService;
import com.polling.core.entity.user.status.UserRole;
import com.polling.core.repository.user.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping
    @ApiOperation(value = "회원 가입")
    public ResponseEntity<Void> save(@RequestBody SaveNativeUserRequestDto requestDto) {
        userService.save(requestDto);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{name}")
    @ApiOperation(value = "이름 중복체크")
    public ResponseEntity<Void> checkName(@PathVariable String name) {
        userService.updateNickname(name);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<Void> delete() {
        Long id=364L;
        userRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "회원 정보 조회")
    public ResponseEntity<FindUserResponseDto> get(@PathVariable Long id) {
        FindUserResponseDto responseDto = userService.findUser(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "회원 정보 수정")
    public ResponseEntity<UpdateUserResponseDto> update(@PathVariable Long id, @RequestBody UpdateUserRequestDto requestDto) {
        UpdateUserResponseDto responseDto = userService.updateUser(id, requestDto);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PatchMapping("/role/{id}")
    @ApiOperation(value = "회원 권한 수정")
    public ResponseEntity<Void> updateRole(@PathVariable Long id, UserRole userRole) {
        userService.updateRole(id, userRole);
        return ResponseEntity.status(200).build();
    }
}
