package com.polling.queryrepository;


import com.polling.contact.dto.FindAllContactResponseDto;
import com.polling.contact.dto.FindContactResponseDto;
import java.util.List;

public interface ContactQueryRepository {

  List<FindContactResponseDto> findContactByMemberId(Long memberId);

  List<FindAllContactResponseDto> findAllContact();
}