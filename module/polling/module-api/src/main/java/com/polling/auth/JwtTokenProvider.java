package com.polling.auth;


import com.polling.auth.service.MemberDetailsService;
import com.polling.entity.member.Member;
import com.polling.entity.member.status.MemberRole;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.repository.member.MemberRepository;
import com.polling.security.service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * JWT 토큰 생성
 */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final MemberDetailsService detailsService;
  private final RedisService redisService;
  private final MemberRepository memberRepository;
  // 어세스 토큰 유효시간 | 30m
  private final long accessTokenValidTime = 30 * 60 * 1000L;
  // 리프레시 토큰 유효시간 | 600m
  private final long refreshTokenValidTime = 600 * 60 * 1000L;
  //키
  @Value("${jwt.secret}")
  private String secretKey;
  private Key key;

  @PostConstruct
  protected void init() {
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  // Access Token 생성.
  public String createAccessToken(String id, Set<MemberRole> roles) {
    return this.createToken(id, roles, accessTokenValidTime);
  }

  // Refresh Token 생성.
  public String createRefreshToken(String email, Set<MemberRole> roles) {
    return this.createToken(email, roles, refreshTokenValidTime);
  }


  public String createToken(String id, Set<MemberRole> roles, long tokenValidityInSeconds) {
    Claims claims = Jwts.claims().setSubject(id); // claims 생성 및 payload 설정
    claims.put("roles", roles); // 권한 설정, key/ value 쌍으로 저장

    Date date = new Date();
    return Jwts.builder()
        .setClaims(claims) // 발행 유저 정보 저장
        .setIssuedAt(date) // 발행 시간 저장
        .setExpiration(new Date(date.getTime() + tokenValidityInSeconds)) // 토큰 유효 시간 저장
        .signWith(key, SignatureAlgorithm.HS256) // 해싱 알고리즘 및 키 설정
        .compact(); // 생성
  }

  // JWT 토큰에서 인증 정보 조회
  public Authentication getAuthentication(String jwtToken) {
    UserDetails userDetails = detailsService.loadUserByUsername(this.getUserId(jwtToken));
    return new UsernamePasswordAuthenticationToken(userDetails, "",
        userDetails.getAuthorities());
  }

  // 토큰에서 회원 정보 추출
  public String getUserId(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  // Request의 Header에서 AccessToken 값을 가져옵니다. "authorization" : "token'
  public String resolveAccessToken(HttpServletRequest request) {
    if (request.getHeader("authorization") != null) {
      return request.getHeader("authorization").substring(7);
    }
    return null;
  }

  // Request의 Header에서 RefreshToken 값을 가져옵니다. "authorization" : "token'
  public String resolveRefreshToken(HttpServletRequest request) {
    if (request.getHeader("refreshToken") != null) {
      return request.getHeader("refreshToken").substring(7);
    }
    return null;
  }

  // 토큰의 유효성 + 만료일자 확인
  public boolean validateToken(String jwtToken) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(jwtToken);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  // 어세스 토큰 헤더 설정
  public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
    response.setHeader("authorization", "bearer " + accessToken);
  }

  // 리프레시 토큰 헤더 설정
  public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
    response.setHeader("refreshToken", "bearer " + refreshToken);
  }

  // RefreshToken 존재유무 확인
  public boolean existsRefreshToken(String refreshToken) {
    return redisService.getValues(refreshToken) != null;
  }

  // Id로 권한 정보 가져오기
  public Set<MemberRole> getRoles(String id) {
    long memberId = Long.parseLong(id);
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.USER_NOT_FOUND));

    return member.getMemberRole();
  }
}
