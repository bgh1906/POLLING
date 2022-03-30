package com.polling.grpc.server;

import com.polling.grpc.EventRequest;
import com.polling.grpc.EventResponse;
import com.polling.grpc.EventServiceGrpc;
import com.polling.grpc.ResultStatus;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * EventService
 *
 * @author akageun
 * @since 2021-12-01
 */
@Slf4j
@Service
public class EventService extends EventServiceGrpc.EventServiceImplBase {

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
}
