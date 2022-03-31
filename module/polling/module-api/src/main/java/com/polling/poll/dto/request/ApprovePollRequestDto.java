package com.polling.poll.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 인가되지 않은 투표의 인가 요청 DTO
 * 해당 투표에 소속된 후보자의 블록체인 Index 정보를 요구한다.
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApprovePollRequestDto {

  @NotNull
  Long pollId;
  @NotNull
  List<Integer> listCandidateIndex;
}
