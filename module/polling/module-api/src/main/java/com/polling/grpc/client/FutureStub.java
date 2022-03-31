package com.polling.grpc.client;

import com.google.common.util.concurrent.ListenableFuture;
import com.polling.grpc.*;
import com.polling.grpc.client.dto.request.WinningRequestDto;
import io.grpc.Deadline;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/grpc/future")
public class FutureStub extends AbstractStub {

  private EventServiceGrpc.EventServiceFutureStub stub() {
    return EventServiceGrpc.newFutureStub(channel())
        .withDeadline(Deadline.after(3000, TimeUnit.MILLISECONDS));
  }

  @PostMapping("/sendEvent")
  public String sendEventTest() {

    EventServiceGrpc.EventServiceFutureStub stub = stub();

    EventRequest request = EventRequest.newBuilder()
        .setId(1)
        .setType(Type.NORMAL)
        .build();

    ListenableFuture<EventResponse> responseFuture = stub.sendEvent(request);

    try {
      EventResponse response = responseFuture.get(5000, TimeUnit.MILLISECONDS); //5 sec
      log.info("response : {}", response.toString());

    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    return "OK";
  }

  @PostMapping("/winning")
  public String winning(@RequestBody WinningRequestDto requestDto) {

    EventServiceGrpc.EventServiceFutureStub stub = stub();

    WinningRequest request = WinningRequest.newBuilder()
            .setType(Type.NORMAL)
            .setEmail(requestDto.getUserEmail())
            .setGiftType(requestDto.getGiftType())
            .build();

    ListenableFuture<WinningResponse> responseFuture = stub.winning(request);

    try {
      WinningResponse response = responseFuture.get(300000, TimeUnit.MILLISECONDS); //300 sec
      log.info("response : {}", response.toString());

    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    return "OK";
  }
}
