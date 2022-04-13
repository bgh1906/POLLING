package com.polling.contact.repository;


import com.polling.contact.dto.FindContactPageResponseDto;
import com.polling.contact.dto.FindContactResponseDto;
import java.util.List;

public interface ContactQueryRepository {

  List<FindContactResponseDto> findByMemberId(Long memberId);

  List<FindContactPageResponseDto> findPageOrderByCreateDate(int page, int limit);
}