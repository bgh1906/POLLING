package com.polling.member.service;


import com.polling.api.controller.exception.CustomException;
import com.polling.api.controller.exception.CustomErrorResult;
import com.polling.api.controller.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.api.service.member.MemberService;
import com.polling.core.entity.member.Member;
import com.polling.core.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService target;
    @Mock
    private MemberRepository memberRepository;

    private final String email = "test@email.com";
    private final String nickname = "testNick";
    
    @Test
    public void 멤버등록실패_이미존재함() throws Exception{
        //given
        doReturn(true).when(memberRepository).existsByEmail(email);
        SaveNativeMemberRequestDto requestDto =
                new SaveNativeMemberRequestDto(nickname, email, "test", "01012345678");

        //when
        final CustomException result = assertThrows(CustomException.class, () -> target.addMember(requestDto));

        //then
        assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.DUPLICATE_EMAIL);
    }
    
    @Test
    public void 멤버등록성공() throws Exception{
        //given
        doReturn(false).when(memberRepository).existsByEmail(email);
        doReturn(createMember()).when(memberRepository).save(any(Member.class));
        SaveNativeMemberRequestDto requestDto = new SaveNativeMemberRequestDto(nickname, email, "test", "01012345678");

        //when
        target.addMember(requestDto);
    
        //then
        verify(memberRepository, times(1)).existsByEmail(email);
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    private Member createMember(){
        return Member.builder()
                .nickname("testNickname")
                .password("test")
                .email("test@test.com")
                .phoneNumber("01012345678")
                .build();
    }
}
