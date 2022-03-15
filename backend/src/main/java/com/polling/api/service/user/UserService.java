package com.polling.api.service.user;

import com.polling.api.controller.exception.CustomException;
import com.polling.api.controller.exception.ErrorCode;
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

    public void save(SaveNativeUserRequestDto requestDto) {
        User user = User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();

        userRepository.save(user);
    }

    public void updateNickname(String name) {
        if (userRepository.existsByName(name))
            throw new CustomException(ErrorCode.DUPLICATE_NAME);
    }


    @Transactional(readOnly=true)
    public FindUserResponseDto findUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        FindUserResponseDto responseDto = new FindUserResponseDto(user.getId(), user.getName(), user.getEmail());
        return responseDto;
    }

    public UpdateUserResponseDto updateUser(Long id, UpdateUserRequestDto requestDto) {
        if (userRepository.existsByName(requestDto.getName()))
            throw new CustomException(ErrorCode.DUPLICATE_NAME);

        User user = userRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        user.nameUpdate(requestDto.getName());
        user.passwordUpdate(requestDto.getPassword());

        userRepository.save(user);

        UpdateUserResponseDto responseDto = new UpdateUserResponseDto(user.getName(), user.getEmail(), user.getPassword(), user.getPhoneNumber());
        return responseDto;
    }

    public void updateRole(Long id, UserRole userRole) {
        if(userRole == null) {
            throw new CustomException(ErrorCode.ROLE_NOT_FOUND);
        }
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException());
        user.userRoleUpdate(userRole);

        userRepository.save(user);
    }

}
