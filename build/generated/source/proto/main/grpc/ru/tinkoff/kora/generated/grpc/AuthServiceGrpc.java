package ru.tinkoff.kora.generated.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: planet-auth.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AuthServiceGrpc {

  private AuthServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ru.tinkoff.kora.generated.grpc.AuthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest,
      ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse> getCheckTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkToken",
      requestType = ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest.class,
      responseType = ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest,
      ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse> getCheckTokenMethod() {
    io.grpc.MethodDescriptor<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest, ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse> getCheckTokenMethod;
    if ((getCheckTokenMethod = AuthServiceGrpc.getCheckTokenMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getCheckTokenMethod = AuthServiceGrpc.getCheckTokenMethod) == null) {
          AuthServiceGrpc.getCheckTokenMethod = getCheckTokenMethod =
              io.grpc.MethodDescriptor.<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest, ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("checkToken"))
              .build();
        }
      }
    }
    return getCheckTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest,
      ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest.class,
      responseType = ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest,
      ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest, ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse> getLoginMethod;
    if ((getLoginMethod = AuthServiceGrpc.getLoginMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getLoginMethod = AuthServiceGrpc.getLoginMethod) == null) {
          AuthServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest, ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest,
      ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse> getCheckTokenWithIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckTokenWithId",
      requestType = ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest.class,
      responseType = ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest,
      ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse> getCheckTokenWithIdMethod() {
    io.grpc.MethodDescriptor<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest, ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse> getCheckTokenWithIdMethod;
    if ((getCheckTokenWithIdMethod = AuthServiceGrpc.getCheckTokenWithIdMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getCheckTokenWithIdMethod = AuthServiceGrpc.getCheckTokenWithIdMethod) == null) {
          AuthServiceGrpc.getCheckTokenWithIdMethod = getCheckTokenWithIdMethod =
              io.grpc.MethodDescriptor.<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest, ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckTokenWithId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("CheckTokenWithId"))
              .build();
        }
      }
    }
    return getCheckTokenWithIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub>() {
        @java.lang.Override
        public AuthServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceStub(channel, callOptions);
        }
      };
    return AuthServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub>() {
        @java.lang.Override
        public AuthServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceBlockingStub(channel, callOptions);
        }
      };
    return AuthServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub>() {
        @java.lang.Override
        public AuthServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceFutureStub(channel, callOptions);
        }
      };
    return AuthServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkToken(ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest request,
        io.grpc.stub.StreamObserver<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckTokenMethod(), responseObserver);
    }

    /**
     */
    default void login(ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest request,
        io.grpc.stub.StreamObserver<ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    default void checkTokenWithId(ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest request,
        io.grpc.stub.StreamObserver<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckTokenWithIdMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AuthService.
   */
  public static abstract class AuthServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AuthServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AuthService.
   */
  public static final class AuthServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AuthServiceStub> {
    private AuthServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkToken(ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest request,
        io.grpc.stub.StreamObserver<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void login(ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest request,
        io.grpc.stub.StreamObserver<ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkTokenWithId(ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest request,
        io.grpc.stub.StreamObserver<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckTokenWithIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AuthService.
   */
  public static final class AuthServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AuthServiceBlockingStub> {
    private AuthServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse checkToken(ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse login(ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse checkTokenWithId(ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckTokenWithIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AuthService.
   */
  public static final class AuthServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AuthServiceFutureStub> {
    private AuthServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse> checkToken(
        ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse> login(
        ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse> checkTokenWithId(
        ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckTokenWithIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_TOKEN = 0;
  private static final int METHODID_LOGIN = 1;
  private static final int METHODID_CHECK_TOKEN_WITH_ID = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_TOKEN:
          serviceImpl.checkToken((ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest) request,
              (io.grpc.stub.StreamObserver<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse>) responseObserver);
          break;
        case METHODID_LOGIN:
          serviceImpl.login((ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest) request,
              (io.grpc.stub.StreamObserver<ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse>) responseObserver);
          break;
        case METHODID_CHECK_TOKEN_WITH_ID:
          serviceImpl.checkTokenWithId((ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest) request,
              (io.grpc.stub.StreamObserver<ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCheckTokenMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenRequest,
              ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenResponse>(
                service, METHODID_CHECK_TOKEN)))
        .addMethod(
          getLoginMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginRequest,
              ru.tinkoff.kora.generated.grpc.PlanetAuth.LoginResponse>(
                service, METHODID_LOGIN)))
        .addMethod(
          getCheckTokenWithIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdRequest,
              ru.tinkoff.kora.generated.grpc.PlanetAuth.CheckTokenWithIdResponse>(
                service, METHODID_CHECK_TOKEN_WITH_ID)))
        .build();
  }

  private static abstract class AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.tinkoff.kora.generated.grpc.PlanetAuth.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthService");
    }
  }

  private static final class AuthServiceFileDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier {
    AuthServiceFileDescriptorSupplier() {}
  }

  private static final class AuthServiceMethodDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AuthServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthServiceFileDescriptorSupplier())
              .addMethod(getCheckTokenMethod())
              .addMethod(getLoginMethod())
              .addMethod(getCheckTokenWithIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
