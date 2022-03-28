package com.polling.auth.service;


import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.auth.dto.AuthDto;
import com.polling.entity.member.Member;
import com.polling.entity.member.status.OAuthType;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.repository.member.MemberRepository;
import com.polling.security.oauth.KaKaoOAuthResponse;
import com.polling.security.oauth.OAuthClient;
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
  public Member auth(AuthDto requestDto) {
    //OAuthType.KAKAO
    if (requestDto.getOAuthType().equals(OAuthType.KAKAO)) {
      KaKaoOAuthResponse profile = oAuthClient.getInfo(
          requestDto.getAccessToken());    //kakao만 잡혀 있음
      Optional<Member> existMember = memberRepository.findByOauthId(profile.getId());
      //Login
      if (existMember.isPresent()) {
        return existMember.get();
      }
      //Join
      else {
        Member member = Member.builder()
            .email(profile.getOAuthEmail())
            .nickname(requestDto.getNickname())
            .oauthType(OAuthType.KAKAO)
            .oauthId(profile.getId())
            .phoneNumber(requestDto.getPhoneNumber())
            .build();
        Member savedMember = memberRepository.save(member);
        //LOGIN
        return savedMember;
      }

    }
    throw new CustomException(CustomErrorResult.UNAUTHORIZED_MEMBER);
  }


}
