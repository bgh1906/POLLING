package com.polling.grpc.server;

import com.polling.grpc.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * EventService
 *
 * @author akageun
 * @since 2021-12-01
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EventService extends EventServiceGrpc.EventServiceImplBase {

  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String FROM_ADDRESS;

  @Override
  public void sendEvent(EventRequest request, StreamObserver<EventResponse> responseObserver) {
    try {
      log.info("sendEvent : {}", request.toString());

      responseObserver.onNext(
          EventResponse.newBuilder()
              .setStatus(ResultStatus.newBuilder()
                  .setCode(Status.OK.getCode().value())
                  .setMessage("SUCCESS!!!")
                  .build())
              .setResult("Success Event")
              .build()
      );
      responseObserver.onCompleted();

    } catch (Exception e) {
      responseObserver.onError(e);
    }

  }

  @Override
  public void sendEventServerStream(EventRequest request,
      StreamObserver<EventResponse> responseObserver) {
    try {
      log.info("sendEventServerStream : {}", request.toString());

      for (int i = 0; i < 10; i++) {
        responseObserver.onNext(
            EventResponse.newBuilder()
                .setStatus(ResultStatus.newBuilder()
                    .setCode(Status.OK.getCode().value())
                    .setMessage("SUCCESS!!!")
                    .build())
                .setResult("Success Event " + i)
                .build()
        );
      }

      responseObserver.onCompleted();

    } catch (Exception e) {
      responseObserver.onError(e);
    }
  }

  @Override
  public StreamObserver<EventRequest> sendEventClientStream(
      StreamObserver<EventResponse> responseObserver) {
    return new StreamObserver<EventRequest>() {
      @Override
      public void onNext(EventRequest value) {
        log.info("sendEventClientStream request onNext : {}", value.toString());
      }

      @Override
      public void onError(Throwable t) {
        log.info("sendEventClientStream request onError : {}", t.getMessage());
      }

      @Override
      public void onCompleted() {
        responseObserver.onNext(
            EventResponse.newBuilder()
                .setStatus(ResultStatus.newBuilder()
                    .setCode(Status.OK.getCode().value())
                    .setMessage("SUCCESS!!!")
                    .build())
                .setResult("Success Event")
                .build()
        );
        responseObserver.onCompleted();
      }
    };
  }


  @Override
  public StreamObserver<EventRequest> sendEventStream(
      StreamObserver<EventResponse> responseObserver) {
    StreamObserver<EventRequest> requestStreamObserver = new StreamObserver<EventRequest>() {
      @Override
      public void onNext(EventRequest value) {
        for (int i = 0; i < 10; i++) {
          responseObserver.onNext(
              EventResponse.newBuilder()
                  .setStatus(ResultStatus.newBuilder()
                      .setCode(Status.OK.getCode().value())
                      .setMessage("SUCCESS!!!")
                      .build())
                  .setResult("Success Event " + i)
                  .build()
          );
        }
      }

      @Override
      public void onError(Throwable t) {
        log.info("sendEventClientStream request onError : {}", t.getMessage());
      }

      @Override
      public void onCompleted() {
        responseObserver.onCompleted();
      }
    };

    return requestStreamObserver;
  }

  @Override
  public void winning(WinningRequest request,
                      StreamObserver<WinningResponse> responseObserver) {
    try {
      log.info("winning : {}", request.toString());
      sendMail(request.getEmail(), request.getGiftType());
      responseObserver.onNext(
              WinningResponse.newBuilder()
                      .setStatus(ResultStatus.newBuilder()
                              .setCode(Status.OK.getCode().value())
                              .setMessage("Winning SUCCESS!!!")
                              .build())
                      .setResult("Success Event")
                      .build()
      );
      responseObserver.onCompleted();

    } catch (Exception e) {
      responseObserver.onError(e);
    }
  }

  public void sendMail(String userEmail, GiftType giftType){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(userEmail);
    message.setFrom(FROM_ADDRESS);
    log.info("FROM: " + FROM_ADDRESS);
    message.setSubject("경품당첨");
    log.info("GIFTTYPE: " + giftType);
    message.setText(giftType + "에 당첨되셨습니다");

    mailSender.send(message);
  }


}
