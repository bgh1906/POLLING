package com.polling.common.security.service;

import com.polling.api.controller.exception.CustomErrorResult;
import com.polling.api.controller.exception.CustomException;
import com.polling.common.security.dto.AuthDto;
import com.polling.common.security.oauth.KaKaoOAuthResponse;
import com.polling.common.security.oauth.OAuthClient;
import com.polling.core.entity.member.Member;
import com.polling.core.entity.member.status.OAuthType;
import com.polling.core.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final OAuthClient oAuthClient;

    public Member auth(AuthDto requestDto){
        //OAuthType.KAKAO
        if(requestDto.getOAuthType().equals(OAuthType.KAKAO)){
            KaKaoOAuthResponse profile = oAuthClient.getInfo(requestDto.getAccessToken());    //kakao만 잡혀 있음
            Optional<Member> existMember = memberRepository.findByOauthId(profile.getId());
            //Login
            if(existMember.isPresent()){
                return existMember.get();
            }
            //Join
            else{
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
