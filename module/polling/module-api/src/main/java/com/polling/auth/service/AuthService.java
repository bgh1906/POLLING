package com.polling.auth.service;


import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.auth.dto.request.AuthRequestDto;
import com.polling.auth.dto.request.ValidateMemberRequestDto;
import com.polling.entity.member.Member;
import com.polling.entity.member.status.OAuthType;
import com.polling.repository.member.MemberRepository;
import com.polling.util.oauth.KaKaoOAuthResponse;
import com.polling.util.oauth.OAuthClient;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class AuthService {

  private final MemberRepository memberRepository;
  private final OAuthClient oAuthClient;

  @Trace
  @Retry
  public Member auth(AuthRequestDto requestDto) {
    System.out.println("accessToken: " + requestDto.getAccessToken());
    KaKaoOAuthResponse profile = oAuthClient.getInfo(
        requestDto.getAccessToken());    //kakao만 잡혀 있음
    Member member = Member.builder()
        .email(profile.getOAuthEmail())
        .nickname(requestDto.getNickname())
        .oauthType(OAuthType.KAKAO)
        .oauthId(profile.getId())
        .phoneNumber(requestDto.getPhoneNumber())
        .build();
    return memberRepository.save(member);
  }

  @Trace
  @Retry
  public Member validate(ValidateMemberRequestDto requestDto) {
    KaKaoOAuthResponse profile = oAuthClient.getInfo(requestDto.getAccessToken());    //kakao만 잡혀 있음
    Optional<Member> existMember = memberRepository.findByOauthId(profile.getId());
    return existMember.orElse(null);
  }

}
