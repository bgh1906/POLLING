package com.polling.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


abstract class AbstractStub {

  private ManagedChannel managedChannel;

  public ManagedChannel channel() {
    return ManagedChannelBuilder.forAddress("localhost", 8099).usePlaintext().build();
  }
}
