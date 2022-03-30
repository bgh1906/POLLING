package com.polling.poll.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApprovePollRequestDto {

  @NotNull
  Long pollId;
  @NotNull
  List<Integer> listCandidateIndex;
}
