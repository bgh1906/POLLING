package com.polling.api.service.user;

import com.polling.api.controller.user.dto.request.SaveNativeUserRequestDto;
import com.polling.api.controller.user.dto.request.UpdateUserRequestDto;
import com.polling.api.controller.user.dto.response.FindUserResponseDto;
import com.polling.api.controller.user.dto.response.UpdateUserResponseDto;
import com.polling.core.entity.user.User;
import com.polling.core.entity.user.status.UserRole;
import com.polling.core.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void signUp(SaveNativeUserRequestDto requestDto) {
        User user = User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();

        userRepository.save(user);
    }

    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException());
        userRepository.deleteById(id);
        userRepository.save(user);
    }

    public FindUserResponseDto findUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException());

        FindUserResponseDto responseDto = new FindUserResponseDto(user.getId(), user.getName(), user.getEmail());
        return responseDto;
    }

    //변경가능항목은 name과 password만 가능한지?
    public UpdateUserResponseDto updateUser(Long id, UpdateUserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException());
        user.nameUpdate(requestDto.getName());
        //user.emailUpdate(requestDto.getEmail());
        user.passwordUpdate(requestDto.getPassword());
        //user.phoneNumberUpdate(requestDto.getPhoneNumber());

        userRepository.save(user);

        UpdateUserResponseDto responseDto = new UpdateUserResponseDto(user.getName(), user.getEmail(), user.getPassword(), user.getPhoneNumber());
        return responseDto;
    }

    public void updateRole(Long id, UserRole userRole) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException());
        user.userRoleUpdate(userRole);

        userRepository.save(user);
    }

}
