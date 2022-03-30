package com.polling.grpc.client;

import com.polling.grpc.EventRequest;
import com.polling.grpc.EventResponse;
import com.polling.grpc.EventServiceGrpc;
import com.polling.grpc.Type;
import io.grpc.Deadline;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AsyncStub
 *
 * @author akageun
 * @since 2021-12-01
 */
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncStub extends AbstractStub {

  private EventServiceGrpc.EventServiceStub stub() {
    return EventServiceGrpc.newStub(channel())
        .withDeadline(Deadline.after(3000, TimeUnit.MILLISECONDS));
  }

  @PostMapping("/sendEvent")
  public String sendEventTest() throws InterruptedException {
    EventServiceGrpc.EventServiceStub stub = stub();

    EventRequest request = EventRequest.newBuilder()
        .setId(1)
        .setType(Type.NORMAL)
        .build();

    final CountDownLatch finishLatch = new CountDownLatch(1);

    stub.sendEvent(request, new StreamObserver<EventResponse>() {
      @Override
      public void onNext(EventResponse value) {
        log.info("onNext response : {}", value.toString());
      }

      @Override
      public void onError(Throwable t) {
        log.error(t.getMessage(), t);
        finishLatch.countDown();
      }

      @Override
      public void onCompleted() {
        log.info("completed!");
        finishLatch.countDown();
      }
    });

    log.info("sendEvent Wait!");

    if (!finishLatch.await(60000, TimeUnit.MILLISECONDS)) {
      log.warn("1 min over");
    }

    return "OK";
  }

  @PostMapping("/sendEventServerStream")
  public String sendEventServerStream() throws InterruptedException {
    EventServiceGrpc.EventServiceStub stub = stub();

    EventRequest request = EventRequest.newBuilder()
        .setId(1)
        .setType(Type.NORMAL)
        .build();

    final CountDownLatch finishLatch = new CountDownLatch(1);

    stub.sendEventServerStream(request, new StreamObserver<EventResponse>() {
      @Override
      public void onNext(EventResponse value) {
        log.info("onNext response : {}", value.toString());
      }

      @Override
      public void onError(Throwable t) {
        log.error(t.getMessage(), t);
        finishLatch.countDown();
      }

      @Override
      public void onCompleted() {
        log.info("completed!");
        finishLatch.countDown();
      }
    });

    log.info("sendEventServerStream Wait!");

    if (!finishLatch.await(60000, TimeUnit.MILLISECONDS)) {
      log.warn("1 min over");
    }

    return "OK";
  }

  @PostMapping("/sendEventClientStream")
  public String sendEventClientStream() {
    EventServiceGrpc.EventServiceStub stub = stub();

    final CountDownLatch finishLatch = new CountDownLatch(1);
    StreamObserver<EventRequest> requestStreamObserver = stub.sendEventClientStream(
        new StreamObserver<EventResponse>() {
          @Override
          public void onNext(EventResponse value) {
            log.info("onNext response : {}", value.toString());
          }

          @Override
          public void onError(Throwable t) {
            log.error(t.getMessage(), t);
            finishLatch.countDown();
          }

          @Override
          public void onCompleted() {
            log.info("completed!");
            finishLatch.countDown();
          }
        });

    log.info("sendEventClientStream Wait!");

    try {
      for (int i = 0; i < 10; i++) {
        EventRequest request = EventRequest.newBuilder()
            .setId(i)
            .setType(Type.NORMAL)
            .build();

        requestStreamObserver.onNext(request);
      }

      requestStreamObserver.onCompleted();
    } catch (Exception e) {
      requestStreamObserver.onError(e);
    }

    return "OK";
  }

  @PostMapping("/sendEventStream")
  public String sendEventStream() {
    EventServiceGrpc.EventServiceStub stub = stub();

    final CountDownLatch finishLatch = new CountDownLatch(1);
    StreamObserver<EventRequest> requestStreamObserver = stub.sendEventStream(
        new StreamObserver<EventResponse>() {
          @Override
          public void onNext(EventResponse value) {
            log.info("onNext response : {}", value.toString());
          }

          @Override
          public void onError(Throwable t) {
            log.error(t.getMessage(), t);
            finishLatch.countDown();
          }

          @Override
          public void onCompleted() {
            log.info("completed!");
            finishLatch.countDown();
          }
        });

    log.info("sendEventStream Wait!");

    try {
      for (int i = 0; i < 10; i++) {
        EventRequest request = EventRequest.newBuilder()
            .setId(i)
            .setType(Type.NORMAL)
            .build();

        requestStreamObserver.onNext(request);
      }

      requestStreamObserver.onCompleted();
    } catch (Exception e) {
      requestStreamObserver.onError(e);
    }

    return "OK";
  }
}
