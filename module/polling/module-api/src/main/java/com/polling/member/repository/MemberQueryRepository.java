package com.polling.member.repository;

import com.polling.member.dto.response.FindMemberResponseDto;
import java.util.List;

public interface MemberQueryRepository {

  List<FindMemberResponseDto> findAll(int offset,
      int limit);
}