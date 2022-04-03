package com.polling.grpc.config;

import com.linecorp.armeria.common.grpc.GrpcSerializationFormats;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.grpc.GrpcService;
import com.linecorp.armeria.server.logging.AccessLogWriter;
import com.linecorp.armeria.server.logging.LoggingService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import com.polling.grpc.notification.NotificationService;
import com.polling.grpc.server.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RequiredArgsConstructor
@Configuration
public class ArmeriaConfig {

  private final EventService eventService;
  private final NotificationService notificationService;

  @Bean
  public ArmeriaServerConfigurator armeriaServerConfigurator() {
    return serverBuilder -> {
      serverBuilder.decorator(LoggingService.newDecorator());
      serverBuilder.accessLogWriter(AccessLogWriter.combined(), false);

      serverBuilder
          .service(GrpcService.builder()
              .addService(eventService)
              .addService(notificationService)
              .supportedSerializationFormats(GrpcSerializationFormats.values())
              .enableUnframedRequests(true)
              .build());

      serverBuilder.serviceUnder("/docs", new DocService());
    };
  }
}
