package com.polling.member.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.member.dto.response.FindMemberResponseDto;
import com.polling.member.entity.Member;
import com.polling.member.entity.status.MemberRole;
import com.polling.member.repository.MemberRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

  private final String email = "test@email.com";
  private final String nickname = "testNick";
  @InjectMocks
  private MemberService target;
  @Mock
  private MemberRepository memberRepository;
  @Mock
  private PasswordEncoder passwordEncoder;

  @Test
  public void memberServiceIsNotNull() throws Exception {
    assertThat(target).isNotNull();
    assertThat(memberRepository).isNotNull();
  }

  @Test
  public void 멤버등록실패_이미존재함() throws Exception {
    //given
    doReturn(true).when(memberRepository).existsByEmail(email);
    SaveNativeMemberRequestDto requestDto =
        new SaveNativeMemberRequestDto(nickname, "wallet", email, "test", "01012345678",
            MemberRole.ROLE_USER);

    //when
    final CustomException result = assertThrows(CustomException.class,
        () -> target.join(requestDto));

    //then
    assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.DUPLICATE_EMAIL);
  }

  @Test
  public void 멤버등록성공() throws Exception {
    //given
    doReturn(false).when(memberRepository).existsByEmail(email);
    doReturn(createMember()).when(memberRepository).save(any(Member.class));
    SaveNativeMemberRequestDto requestDto = new SaveNativeMemberRequestDto(nickname, "wallet",
        email,
        "test", "01012345678", MemberRole.ROLE_USER);

    //when
    target.join(requestDto);

    //then
    verify(memberRepository, times(1)).existsByEmail(email);
    verify(memberRepository, times(1)).save(any(Member.class));
  }

  @Test
  public void 멤버조회실패_멤버없음() throws Exception {
    //given
    doReturn(Optional.empty()).when(memberRepository).findById(any(Long.class));

    //when
    final CustomException result = assertThrows(CustomException.class,
        () -> target.findMember(1L));

    //then
    assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.USER_NOT_FOUND);
  }

  @Test
  public void 멤버조회성공() throws Exception {
    //given
    doReturn(Optional.of(createMember())).when(memberRepository).findById(any(Long.class));

    //when
    FindMemberResponseDto findMember = target.findMember(1L);

    //then
    assertThat(findMember.getEmail()).isEqualTo(email);
    verify(memberRepository, times(1)).findById(any(Long.class));
  }

  private Member createMember() {
    return Member.builder()
        .nickname(nickname)
        .password("test")
        .email(email)
        .phoneNumber("01012345678")
        .build();
  }
}
