package ru.planet.auth;

import io.grpc.BindableService;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import io.netty.channel.EventLoopGroup;
import io.undertow.connector.ByteBufferPool;
import java.lang.Double;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;
import org.slf4j.ILoggerFactory;
import org.xnio.XnioWorker;
import ru.planet.auth.api.AuthApiController;
import ru.planet.auth.api.AuthApiResponses;
import ru.planet.auth.api.AuthApiServerResponseMappers;
import ru.planet.auth.cache.RoleCache;
import ru.planet.auth.cache.UserCache;
import ru.planet.auth.configuration.properties.$BcryptProperties_ConfigValueExtractor;
import ru.planet.auth.configuration.properties.$JwtProperties_ConfigValueExtractor;
import ru.planet.auth.configuration.properties.BcryptProperties;
import ru.planet.auth.configuration.properties.JwtProperties;
import ru.planet.auth.controller.$AuthGrpcController__AopProxy;
import ru.planet.auth.controller.$AuthRestController__AopProxy;
import ru.planet.auth.controller.AuthGrpcController;
import ru.planet.auth.controller.AuthRestController;
import ru.planet.auth.controller.OpenApiController;
import ru.planet.auth.dto.$User_JdbcResultSetMapper;
import ru.planet.auth.dto.User;
import ru.planet.auth.exception.HttpExceptionHandler;
import ru.planet.auth.helper.JwtService;
import ru.planet.auth.helper.OpenApiProvider;
import ru.planet.auth.model.$AuthErrorResponse_JsonWriter;
import ru.planet.auth.model.$AuthRequest_JsonReader;
import ru.planet.auth.model.$AuthResponse_JsonWriter;
import ru.planet.auth.model.$CheckToken400Response_JsonWriter;
import ru.planet.auth.model.$Login400Response_JsonWriter;
import ru.planet.auth.model.$ValidateRequest_JsonReader;
import ru.planet.auth.model.$ValidateResponse_JsonWriter;
import ru.planet.auth.model.AuthErrorResponse;
import ru.planet.auth.model.AuthRequest;
import ru.planet.auth.model.AuthResponse;
import ru.planet.auth.model.CheckToken400Response;
import ru.planet.auth.model.Login400Response;
import ru.planet.auth.model.ValidateRequest;
import ru.planet.auth.model.ValidateResponse;
import ru.planet.auth.operation.CheckAdminOperation;
import ru.planet.auth.operation.CheckTokenOperation;
import ru.planet.auth.operation.CheckTokenWithIdOperation;
import ru.planet.auth.operation.LoginOperation;
import ru.planet.auth.repository.$$AuthRepository_Impl__AopProxy;
import ru.planet.auth.repository.$AuthRepository_Impl;
import ru.tinkoff.kora.application.graph.All;
import ru.tinkoff.kora.application.graph.ApplicationGraphDraw;
import ru.tinkoff.kora.application.graph.LifecycleWrapper;
import ru.tinkoff.kora.application.graph.Node;
import ru.tinkoff.kora.application.graph.TypeRef;
import ru.tinkoff.kora.application.graph.ValueOf;
import ru.tinkoff.kora.application.graph.Wrapped;
import ru.tinkoff.kora.application.graph.WrappedRefreshListener;
import ru.tinkoff.kora.cache.LoadableCache;
import ru.tinkoff.kora.cache.caffeine.$CaffeineCacheConfig_ConfigValueExtractor;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheConfig;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheFactory;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheMetricCollector;
import ru.tinkoff.kora.cache.caffeine.CaffeineCacheTelemetry;
import ru.tinkoff.kora.cache.telemetry.CacheMetrics;
import ru.tinkoff.kora.cache.telemetry.CacheTracer;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.common.readiness.ReadinessProbe;
import ru.tinkoff.kora.common.util.Size;
import ru.tinkoff.kora.config.common.Config;
import ru.tinkoff.kora.config.common.ConfigWatcher;
import ru.tinkoff.kora.config.common.extractor.ConfigValueExtractor;
import ru.tinkoff.kora.config.common.extractor.EnumConfigValueExtractor;
import ru.tinkoff.kora.config.common.origin.ConfigOrigin;
import ru.tinkoff.kora.database.common.telemetry.DataBaseLoggerFactory;
import ru.tinkoff.kora.database.common.telemetry.DataBaseMetricWriterFactory;
import ru.tinkoff.kora.database.common.telemetry.DataBaseTracerFactory;
import ru.tinkoff.kora.database.common.telemetry.DefaultDataBaseTelemetryFactory;
import ru.tinkoff.kora.database.jdbc.$JdbcDatabaseConfig_ConfigValueExtractor;
import ru.tinkoff.kora.database.jdbc.JdbcDatabase;
import ru.tinkoff.kora.database.jdbc.JdbcDatabaseConfig;
import ru.tinkoff.kora.database.jdbc.mapper.result.JdbcResultSetMapper;
import ru.tinkoff.kora.database.jdbc.mapper.result.JdbcRowMapper;
import ru.tinkoff.kora.generated.grpc.PlanetAuth;
import ru.tinkoff.kora.grpc.server.DynamicBindableService;
import ru.tinkoff.kora.grpc.server.DynamicServerInterceptor;
import ru.tinkoff.kora.grpc.server.GrpcNettyServer;
import ru.tinkoff.kora.grpc.server.config.$GrpcServerConfig_ConfigValueExtractor;
import ru.tinkoff.kora.grpc.server.config.GrpcServerConfig;
import ru.tinkoff.kora.grpc.server.telemetry.DefaultGrpcServerTelemetry;
import ru.tinkoff.kora.grpc.server.telemetry.GrpcServerMetricsFactory;
import ru.tinkoff.kora.grpc.server.telemetry.GrpcServerTelemetry;
import ru.tinkoff.kora.grpc.server.telemetry.GrpcServerTracer;
import ru.tinkoff.kora.grpc.server.telemetry.Slf4jGrpcServerLogger;
import ru.tinkoff.kora.http.server.common.$HttpServerConfig_ConfigValueExtractor;
import ru.tinkoff.kora.http.server.common.HttpServerConfig;
import ru.tinkoff.kora.http.server.common.PrivateApiHandler;
import ru.tinkoff.kora.http.server.common.handler.BlockingRequestExecutor;
import ru.tinkoff.kora.http.server.common.handler.HttpServerRequestHandler;
import ru.tinkoff.kora.http.server.common.router.PublicApiHandler;
import ru.tinkoff.kora.http.server.common.telemetry.$HttpServerLoggerConfig_ConfigValueExtractor;
import ru.tinkoff.kora.http.server.common.telemetry.$HttpServerTelemetryConfig_ConfigValueExtractor;
import ru.tinkoff.kora.http.server.common.telemetry.DefaultHttpServerTelemetryFactory;
import ru.tinkoff.kora.http.server.common.telemetry.HttpServerMetricsFactory;
import ru.tinkoff.kora.http.server.common.telemetry.HttpServerTracerFactory;
import ru.tinkoff.kora.http.server.common.telemetry.PrivateApiMetrics;
import ru.tinkoff.kora.http.server.common.telemetry.Slf4jHttpServerLoggerFactory;
import ru.tinkoff.kora.http.server.undertow.UndertowHttpServer;
import ru.tinkoff.kora.http.server.undertow.UndertowPrivateApiHandler;
import ru.tinkoff.kora.http.server.undertow.UndertowPrivateHttpServer;
import ru.tinkoff.kora.http.server.undertow.UndertowPublicApiHandler;
import ru.tinkoff.kora.json.module.http.server.JsonReaderHttpServerRequestMapper;
import ru.tinkoff.kora.json.module.http.server.JsonWriterHttpServerResponseMapper;
import ru.tinkoff.kora.logging.common.LoggingConfig;
import ru.tinkoff.kora.logging.common.LoggingLevelApplier;
import ru.tinkoff.kora.logging.common.LoggingLevelRefresher;
import ru.tinkoff.kora.logging.common.arg.StructuredArgumentMapper;
import ru.tinkoff.kora.netty.common.$NettyTransportConfig_ConfigValueExtractor;
import ru.tinkoff.kora.netty.common.NettyChannelFactory;
import ru.tinkoff.kora.netty.common.NettyTransportConfig;
import ru.tinkoff.kora.openapi.management.$OpenApiManagementConfig_ConfigValueExtractor;
import ru.tinkoff.kora.openapi.management.$OpenApiManagementConfig_RapidocConfig_ConfigValueExtractor;
import ru.tinkoff.kora.openapi.management.$OpenApiManagementConfig_SwaggerUIConfig_ConfigValueExtractor;
import ru.tinkoff.kora.openapi.management.OpenApiManagementConfig;
import ru.tinkoff.kora.telemetry.common.$TelemetryConfig_ConfigValueExtractor;
import ru.tinkoff.kora.telemetry.common.$TelemetryConfig_LogConfig_ConfigValueExtractor;
import ru.tinkoff.kora.telemetry.common.$TelemetryConfig_MetricsConfig_ConfigValueExtractor;
import ru.tinkoff.kora.telemetry.common.$TelemetryConfig_TracingConfig_ConfigValueExtractor;

@Generated("ru.tinkoff.kora.kora.app.annotation.processor.KoraAppProcessor")
public class ApplicationGraph implements Supplier<ApplicationGraphDraw> {
  private static final ApplicationGraphDraw graphDraw;

  private static final ComponentHolder0 holder0;

  static {
    var impl = new $ApplicationImpl();
    graphDraw = new ApplicationGraphDraw(Application.class);
    holder0 = new ComponentHolder0(graphDraw, impl);
  }

  @Override
  public ApplicationGraphDraw get() {
    return graphDraw;
  }

  public static ApplicationGraphDraw graph() {
    return graphDraw;
  }

  @Generated("ru.tinkoff.kora.kora.app.annotation.processor.KoraAppProcessor")
  public static final class ComponentHolder0 {
    private final Node<ConfigOrigin> component0;

    private final Node<Optional<ValueOf<ConfigOrigin>>> component1;

    private final Node<ConfigWatcher> component2;

    private final Node<Config> component3;

    private final Node<Config> component4;

    private final Node<com.typesafe.config.Config> component5;

    private final Node<com.typesafe.config.Config> component6;

    private final Node<Config> component7;

    private final Node<Config> component8;

    private final Node<ConfigValueExtractor<Size>> component9;

    private final Node<ConfigValueExtractor<Duration>> component10;

    private final Node<$TelemetryConfig_LogConfig_ConfigValueExtractor> component11;

    private final Node<$TelemetryConfig_TracingConfig_ConfigValueExtractor> component12;

    private final Node<ConfigValueExtractor<Double>> component13;

    private final Node<ConfigValueExtractor<double[]>> component14;

    private final Node<$TelemetryConfig_MetricsConfig_ConfigValueExtractor> component15;

    private final Node<$TelemetryConfig_ConfigValueExtractor> component16;

    private final Node<$GrpcServerConfig_ConfigValueExtractor> component17;

    private final Node<GrpcServerConfig> component18;

    private final Node<$JwtProperties_ConfigValueExtractor> component19;

    private final Node<JwtProperties> component20;

    private final Node<ConfigValueExtractor<Properties>> component21;

    private final Node<$JdbcDatabaseConfig_ConfigValueExtractor> component22;

    private final Node<JdbcDatabaseConfig> component23;

    private final Node<DataBaseLoggerFactory.DefaultDataBaseLoggerFactory> component24;

    private final Node<DefaultDataBaseTelemetryFactory> component25;

    private final Node<JdbcDatabase> component26;

    private final Node<$User_JdbcResultSetMapper> component27;

    private final Node<JdbcRowMapper<String>> component28;

    private final Node<JdbcResultSetMapper<List<String>>> component29;

    private final Node<ILoggerFactory> component30;

    private final Node<$AuthRepository_Impl> component31;

    private final Node<JwtService> component32;

    private final Node<$BcryptProperties_ConfigValueExtractor> component33;

    private final Node<BcryptProperties> component34;

    private final Node<$CaffeineCacheConfig_ConfigValueExtractor> component35;

    private final Node<CaffeineCacheConfig> component36;

    private final Node<CaffeineCacheFactory> component37;

    private final Node<CaffeineCacheTelemetry> component38;

    private final Node<UserCache> component39;

    private final Node<LoadableCache<String, User>> component40;

    private final Node<CaffeineCacheConfig> component41;

    private final Node<RoleCache> component42;

    private final Node<LoadableCache<Long, List<String>>> component43;

    private final Node<LoginOperation> component44;

    private final Node<CheckTokenOperation> component45;

    private final Node<CheckTokenWithIdOperation> component46;

    private final Node<CheckAdminOperation> component47;

    private final Node<AuthGrpcController> component48;

    private final Node<WrappedRefreshListener<List<DynamicBindableService>>> component49;

    private final Node<WrappedRefreshListener<List<DynamicServerInterceptor>>> component50;

    private final Node<EnumConfigValueExtractor<NettyTransportConfig.EventLoop>> component51;

    private final Node<$NettyTransportConfig_ConfigValueExtractor> component52;

    private final Node<NettyTransportConfig> component53;

    private final Node<LifecycleWrapper<EventLoopGroup>> component54;

    private final Node<LifecycleWrapper<EventLoopGroup>> component55;

    private final Node<NettyChannelFactory> component56;

    private final Node<Slf4jGrpcServerLogger> component57;

    private final Node<DefaultGrpcServerTelemetry> component58;

    private final Node<NettyServerBuilder> component59;

    private final Node<GrpcNettyServer> component60;

    private final Node<ConfigValueExtractor<String>> component61;

    private final Node<ConfigValueExtractor<Set<String>>> component62;

    private final Node<$HttpServerLoggerConfig_ConfigValueExtractor> component63;

    private final Node<$HttpServerTelemetryConfig_ConfigValueExtractor> component64;

    private final Node<$HttpServerConfig_ConfigValueExtractor> component65;

    private final Node<HttpServerConfig> component66;

    private final Node<AuthRestController> component67;

    private final Node<AuthApiController> component68;

    private final Node<$AuthRequest_JsonReader> component69;

    private final Node<JsonReaderHttpServerRequestMapper<AuthRequest>> component70;

    private final Node<$AuthResponse_JsonWriter> component71;

    private final Node<JsonWriterHttpServerResponseMapper<AuthResponse>> component72;

    private final Node<$Login400Response_JsonWriter> component73;

    private final Node<JsonWriterHttpServerResponseMapper<Login400Response>> component74;

    private final Node<$AuthErrorResponse_JsonWriter> component75;

    private final Node<JsonWriterHttpServerResponseMapper<AuthErrorResponse>> component76;

    private final Node<AuthApiServerResponseMappers.LoginApiResponseMapper> component77;

    private final Node<Wrapped<XnioWorker>> component78;

    private final Node<BlockingRequestExecutor> component79;

    private final Node<HttpServerRequestHandler> component80;

    private final Node<$ValidateRequest_JsonReader> component81;

    private final Node<JsonReaderHttpServerRequestMapper<ValidateRequest>> component82;

    private final Node<$ValidateResponse_JsonWriter> component83;

    private final Node<JsonWriterHttpServerResponseMapper<ValidateResponse>> component84;

    private final Node<$CheckToken400Response_JsonWriter> component85;

    private final Node<JsonWriterHttpServerResponseMapper<CheckToken400Response>> component86;

    private final Node<AuthApiServerResponseMappers.CheckTokenApiResponseMapper> component87;

    private final Node<HttpServerRequestHandler> component88;

    private final Node<OpenApiProvider> component89;

    private final Node<OpenApiController> component90;

    private final Node<HttpServerRequestHandler> component91;

    private final Node<HttpServerRequestHandler> component92;

    private final Node<ConfigValueExtractor<List<String>>> component93;

    private final Node<$OpenApiManagementConfig_SwaggerUIConfig_ConfigValueExtractor> component94;

    private final Node<$OpenApiManagementConfig_RapidocConfig_ConfigValueExtractor> component95;

    private final Node<$OpenApiManagementConfig_ConfigValueExtractor> component96;

    private final Node<OpenApiManagementConfig> component97;

    private final Node<HttpServerRequestHandler> component98;

    private final Node<HttpServerRequestHandler> component99;

    private final Node<HttpServerRequestHandler> component100;

    private final Node<HttpExceptionHandler> component101;

    private final Node<Slf4jHttpServerLoggerFactory> component102;

    private final Node<DefaultHttpServerTelemetryFactory> component103;

    private final Node<PublicApiHandler> component104;

    private final Node<UndertowPublicApiHandler> component105;

    private final Node<ByteBufferPool> component106;

    private final Node<UndertowHttpServer> component107;

    private final Node<Optional<PrivateApiMetrics>> component108;

    private final Node<PrivateApiHandler> component109;

    private final Node<UndertowPrivateApiHandler> component110;

    private final Node<ByteBufferPool> component111;

    private final Node<UndertowPrivateHttpServer> component112;

    private final Node<ConfigValueExtractor<LoggingConfig>> component113;

    private final Node<LoggingConfig> component114;

    private final Node<LoggingLevelApplier> component115;

    private final Node<LoggingLevelRefresher> component116;

    public ComponentHolder0(ApplicationGraphDraw graphDraw, $ApplicationImpl impl) {
      var map = new HashMap<String, Type>();
      for (var field : ComponentHolder0.class.getDeclaredFields()) {
        if (!field.getName().startsWith("component")) continue;
        map.put(field.getName(), ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);
      }
      var _type_of_component0 = map.get("component0");
      component0 = graphDraw.addNode0(_type_of_component0, new Class<?>[]{ru.tinkoff.kora.config.common.annotation.ApplicationConfig.class, }, g -> impl.applicationConfigOrigin(), List.of());
      var _type_of_component1 = map.get("component1");
      component1 = graphDraw.addNode0(_type_of_component1, new Class<?>[]{ru.tinkoff.kora.config.common.annotation.ApplicationConfig.class, }, g -> Optional.<ValueOf<ConfigOrigin>>ofNullable(
            g.valueOf(ApplicationGraph.holder0.component0).map(v -> (ConfigOrigin) v)
          ), List.of(), component0.valueOf());
      var _type_of_component2 = map.get("component2");
      component2 = graphDraw.addNode0(_type_of_component2, new Class<?>[]{}, g -> impl.configRefresher(
            g.get(ApplicationGraph.holder0.component1)
          ), List.of(), component1);
      var _type_of_component3 = map.get("component3");
      component3 = graphDraw.addNode0(_type_of_component3, new Class<?>[]{ru.tinkoff.kora.config.common.annotation.Environment.class, }, g -> impl.environmentConfig(), List.of());
      var _type_of_component4 = map.get("component4");
      component4 = graphDraw.addNode0(_type_of_component4, new Class<?>[]{ru.tinkoff.kora.config.common.annotation.SystemProperties.class, }, g -> impl.systemProperties(), List.of());
      var _type_of_component5 = map.get("component5");
      component5 = graphDraw.addNode0(_type_of_component5, new Class<?>[]{ru.tinkoff.kora.config.common.annotation.ApplicationConfig.class, }, g -> impl.applicationUnresolved(
            g.get(ApplicationGraph.holder0.component0)
          ), List.of(), component0);
      var _type_of_component6 = map.get("component6");
      component6 = graphDraw.addNode0(_type_of_component6, new Class<?>[]{}, g -> impl.hoconConfig(
            g.get(ApplicationGraph.holder0.component5)
          ), List.of(), component5);
      var _type_of_component7 = map.get("component7");
      component7 = graphDraw.addNode0(_type_of_component7, new Class<?>[]{ru.tinkoff.kora.config.common.annotation.ApplicationConfig.class, }, g -> impl.config(
            g.get(ApplicationGraph.holder0.component0),
            g.get(ApplicationGraph.holder0.component6)
          ), List.of(), component0, component6);
      var _type_of_component8 = map.get("component8");
      component8 = graphDraw.addNode0(_type_of_component8, new Class<?>[]{}, g -> impl.config(
            g.get(ApplicationGraph.holder0.component3),
            g.get(ApplicationGraph.holder0.component4),
            g.get(ApplicationGraph.holder0.component7)
          ), List.of(), component3, component4, component7);
      var _type_of_component9 = map.get("component9");
      component9 = graphDraw.addNode0(_type_of_component9, new Class<?>[]{}, g -> impl.sizeConfigValueExtractor(), List.of());
      var _type_of_component10 = map.get("component10");
      component10 = graphDraw.addNode0(_type_of_component10, new Class<?>[]{}, g -> impl.durationConfigValueExtractor(), List.of());
      var _type_of_component11 = map.get("component11");
      component11 = graphDraw.addNode0(_type_of_component11, new Class<?>[]{}, g -> new $TelemetryConfig_LogConfig_ConfigValueExtractor(), List.of());
      var _type_of_component12 = map.get("component12");
      component12 = graphDraw.addNode0(_type_of_component12, new Class<?>[]{}, g -> new $TelemetryConfig_TracingConfig_ConfigValueExtractor(), List.of());
      var _type_of_component13 = map.get("component13");
      component13 = graphDraw.addNode0(_type_of_component13, new Class<?>[]{}, g -> impl.doubleConfigValueExtractor(), List.of());
      var _type_of_component14 = map.get("component14");
      component14 = graphDraw.addNode0(_type_of_component14, new Class<?>[]{}, g -> impl.doubleArrayConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component13)
          ), List.of(), component13);
      var _type_of_component15 = map.get("component15");
      component15 = graphDraw.addNode0(_type_of_component15, new Class<?>[]{}, g -> new $TelemetryConfig_MetricsConfig_ConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component14)
          ), List.of(), component14);
      var _type_of_component16 = map.get("component16");
      component16 = graphDraw.addNode0(_type_of_component16, new Class<?>[]{}, g -> new $TelemetryConfig_ConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component11),
            g.get(ApplicationGraph.holder0.component12),
            g.get(ApplicationGraph.holder0.component15)
          ), List.of(), component11, component12, component15);
      var _type_of_component17 = map.get("component17");
      component17 = graphDraw.addNode0(_type_of_component17, new Class<?>[]{}, g -> new $GrpcServerConfig_ConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component9),
            g.get(ApplicationGraph.holder0.component10),
            g.get(ApplicationGraph.holder0.component16)
          ), List.of(), component9, component10, component16);
      var _type_of_component18 = map.get("component18");
      component18 = graphDraw.addNode0(_type_of_component18, new Class<?>[]{}, g -> impl.grpcServerConfig(
            g.get(ApplicationGraph.holder0.component8),
            g.get(ApplicationGraph.holder0.component17)
          ), List.of(), component8, component17);
      var _type_of_component19 = map.get("component19");
      component19 = graphDraw.addNode0(_type_of_component19, new Class<?>[]{}, g -> new $JwtProperties_ConfigValueExtractor(), List.of());
      var _type_of_component20 = map.get("component20");
      component20 = graphDraw.addNode0(_type_of_component20, new Class<?>[]{}, g -> impl.module5.jwtProperties(
            g.get(ApplicationGraph.holder0.component8),
            g.get(ApplicationGraph.holder0.component19)
          ), List.of(), component8, component19);
      var _type_of_component21 = map.get("component21");
      component21 = graphDraw.addNode0(_type_of_component21, new Class<?>[]{}, g -> impl.propertiesConfigValueExtractor(), List.of());
      var _type_of_component22 = map.get("component22");
      component22 = graphDraw.addNode0(_type_of_component22, new Class<?>[]{}, g -> new $JdbcDatabaseConfig_ConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component10),
            g.get(ApplicationGraph.holder0.component21),
            g.get(ApplicationGraph.holder0.component16)
          ), List.of(), component10, component21, component16);
      var _type_of_component23 = map.get("component23");
      component23 = graphDraw.addNode0(_type_of_component23, new Class<?>[]{}, g -> impl.jdbcDataBaseConfig(
            g.get(ApplicationGraph.holder0.component8),
            g.get(ApplicationGraph.holder0.component22)
          ), List.of(), component8, component22);
      var _type_of_component24 = map.get("component24");
      component24 = graphDraw.addNode0(_type_of_component24, new Class<?>[]{}, g -> impl.defaultDataBaseLoggerFactory(), List.of());
      var _type_of_component25 = map.get("component25");
      component25 = graphDraw.addNode0(_type_of_component25, new Class<?>[]{}, g -> impl.defaultDataBaseTelemetry(
            g.get(ApplicationGraph.holder0.component24),
            (DataBaseMetricWriterFactory) null,
            (DataBaseTracerFactory) null
          ), List.of(), component24);
      var _type_of_component26 = map.get("component26");
      component26 = graphDraw.addNode0(_type_of_component26, new Class<?>[]{}, g -> impl.jdbcDataBase(
            g.get(ApplicationGraph.holder0.component23),
            g.get(ApplicationGraph.holder0.component25)
          ), List.of(), component23, component25);
      var _type_of_component27 = map.get("component27");
      component27 = graphDraw.addNode0(_type_of_component27, new Class<?>[]{}, g -> new $User_JdbcResultSetMapper(), List.of());
      var _type_of_component28 = map.get("component28");
      component28 = graphDraw.addNode0(_type_of_component28, new Class<?>[]{}, g -> impl.stringJdbcRowMapper(), List.of());
      var _type_of_component29 = map.get("component29");
      component29 = graphDraw.addNode0(_type_of_component29, new Class<?>[]{}, g -> JdbcResultSetMapper.listResultSetMapper(
            g.get(ApplicationGraph.holder0.component28)
          ), List.of(), component28);
      var _type_of_component30 = map.get("component30");
      component30 = graphDraw.addNode0(_type_of_component30, new Class<?>[]{}, g -> impl.loggerFactory(), List.of());
      var _type_of_component31 = map.get("component31");
      component31 = graphDraw.addNode0(_type_of_component31, new Class<?>[]{}, g -> new $$AuthRepository_Impl__AopProxy(
            g.get(ApplicationGraph.holder0.component26),
            g.get(ApplicationGraph.holder0.component27),
            g.get(ApplicationGraph.holder0.component29),
            g.get(ApplicationGraph.holder0.component30),
            (StructuredArgumentMapper<String>) null,
            (StructuredArgumentMapper<User>) null,
            (StructuredArgumentMapper<Long>) null,
            (StructuredArgumentMapper<List<String>>) null
          ), List.of(), component26, component27, component29, component30);
      var _type_of_component32 = map.get("component32");
      component32 = graphDraw.addNode0(_type_of_component32, new Class<?>[]{}, g -> new JwtService(
            g.get(ApplicationGraph.holder0.component20),
            g.get(ApplicationGraph.holder0.component31)
          ), List.of(), component20, component31);
      var _type_of_component33 = map.get("component33");
      component33 = graphDraw.addNode0(_type_of_component33, new Class<?>[]{}, g -> new $BcryptProperties_ConfigValueExtractor(), List.of());
      var _type_of_component34 = map.get("component34");
      component34 = graphDraw.addNode0(_type_of_component34, new Class<?>[]{}, g -> impl.module4.bcryptProperties(
            g.get(ApplicationGraph.holder0.component8),
            g.get(ApplicationGraph.holder0.component33)
          ), List.of(), component8, component33);
      var _type_of_component35 = map.get("component35");
      component35 = graphDraw.addNode0(_type_of_component35, new Class<?>[]{}, g -> new $CaffeineCacheConfig_ConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component10)
          ), List.of(), component10);
      var _type_of_component36 = map.get("component36");
      component36 = graphDraw.addNode0(_type_of_component36, new Class<?>[]{ru.planet.auth.cache.UserCache.class, }, g -> impl.module2.UserCache_Config(
            g.get(ApplicationGraph.holder0.component8),
            g.get(ApplicationGraph.holder0.component35)
          ), List.of(), component8, component35);
      var _type_of_component37 = map.get("component37");
      component37 = graphDraw.addNode0(_type_of_component37, new Class<?>[]{}, g -> impl.caffeineCacheFactory(
            (CaffeineCacheMetricCollector) null
          ), List.of());
      var _type_of_component38 = map.get("component38");
      component38 = graphDraw.addNode0(_type_of_component38, new Class<?>[]{}, g -> impl.caffeineCacheTelemetry(
            (CacheMetrics) null,
            (CacheTracer) null
          ), List.of());
      var _type_of_component39 = map.get("component39");
      component39 = graphDraw.addNode0(_type_of_component39, new Class<?>[]{}, g -> impl.module2.$UserCacheImpl_Impl(
            g.get(ApplicationGraph.holder0.component36),
            g.get(ApplicationGraph.holder0.component37),
            g.get(ApplicationGraph.holder0.component38)
          ), List.of(), component36, component37, component38);
      var _type_of_component40 = map.get("component40");
      component40 = graphDraw.addNode0(_type_of_component40, new Class<?>[]{}, g -> impl.module3.userCache(
            g.get(ApplicationGraph.holder0.component39),
            g.get(ApplicationGraph.holder0.component31)
          ), List.of(), component39, component31);
      var _type_of_component41 = map.get("component41");
      component41 = graphDraw.addNode0(_type_of_component41, new Class<?>[]{ru.planet.auth.cache.RoleCache.class, }, g -> impl.module1.RoleCache_Config(
            g.get(ApplicationGraph.holder0.component8),
            g.get(ApplicationGraph.holder0.component35)
          ), List.of(), component8, component35);
      var _type_of_component42 = map.get("component42");
      component42 = graphDraw.addNode0(_type_of_component42, new Class<?>[]{}, g -> impl.module1.$RoleCacheImpl_Impl(
            g.get(ApplicationGraph.holder0.component41),
            g.get(ApplicationGraph.holder0.component37),
            g.get(ApplicationGraph.holder0.component38)
          ), List.of(), component41, component37, component38);
      var _type_of_component43 = map.get("component43");
      component43 = graphDraw.addNode0(_type_of_component43, new Class<?>[]{}, g -> impl.module3.roleCache(
            g.get(ApplicationGraph.holder0.component42),
            g.get(ApplicationGraph.holder0.component31)
          ), List.of(), component42, component31);
      var _type_of_component44 = map.get("component44");
      component44 = graphDraw.addNode0(_type_of_component44, new Class<?>[]{}, g -> new LoginOperation(
            g.get(ApplicationGraph.holder0.component32),
            g.get(ApplicationGraph.holder0.component34),
            g.get(ApplicationGraph.holder0.component40),
            g.get(ApplicationGraph.holder0.component43)
          ), List.of(), component32, component34, component40, component43);
      var _type_of_component45 = map.get("component45");
      component45 = graphDraw.addNode0(_type_of_component45, new Class<?>[]{}, g -> new CheckTokenOperation(
            g.get(ApplicationGraph.holder0.component32)
          ), List.of(), component32);
      var _type_of_component46 = map.get("component46");
      component46 = graphDraw.addNode0(_type_of_component46, new Class<?>[]{}, g -> new CheckTokenWithIdOperation(
            g.get(ApplicationGraph.holder0.component32)
          ), List.of(), component32);
      var _type_of_component47 = map.get("component47");
      component47 = graphDraw.addNode0(_type_of_component47, new Class<?>[]{}, g -> new CheckAdminOperation(
            g.get(ApplicationGraph.holder0.component32)
          ), List.of(), component32);
      var _type_of_component48 = map.get("component48");
      component48 = graphDraw.addNode0(_type_of_component48, new Class<?>[]{}, g -> new $AuthGrpcController__AopProxy(
            g.get(ApplicationGraph.holder0.component44),
            g.get(ApplicationGraph.holder0.component45),
            g.get(ApplicationGraph.holder0.component46),
            g.get(ApplicationGraph.holder0.component47),
            g.get(ApplicationGraph.holder0.component30),
            (StructuredArgumentMapper<PlanetAuth.CheckTokenRequest>) null,
            (StructuredArgumentMapper<StreamObserver<PlanetAuth.CheckTokenResponse>>) null,
            (StructuredArgumentMapper<PlanetAuth.LoginRequest>) null,
            (StructuredArgumentMapper<StreamObserver<PlanetAuth.LoginResponse>>) null,
            (StructuredArgumentMapper<PlanetAuth.CheckTokenWithIdRequest>) null,
            (StructuredArgumentMapper<StreamObserver<PlanetAuth.CheckTokenWithIdResponse>>) null,
            (StructuredArgumentMapper<PlanetAuth.CheckAdminRequest>) null,
            (StructuredArgumentMapper<StreamObserver<PlanetAuth.CheckAdminResponse>>) null
          ), List.of(), component44, component45, component46, component47, component30);
      var _type_of_component49 = map.get("component49");
      component49 = graphDraw.addNode0(_type_of_component49, new Class<?>[]{}, g -> impl.dynamicBindableServicesListener(
            All.of(
              g.valueOf(ApplicationGraph.holder0.component48).map(v -> (BindableService) v)
              )
          ), List.of(), component48.valueOf());
      var _type_of_component50 = map.get("component50");
      component50 = graphDraw.addNode0(_type_of_component50, new Class<?>[]{}, g -> impl.dynamicInterceptorsListener(
            All.of(  )
          ), List.of());
      var _type_of_component51 = map.get("component51");
      component51 = graphDraw.addNode0(_type_of_component51, new Class<?>[]{}, g -> impl.<NettyTransportConfig.EventLoop>enumConfigValueExtractor(
            TypeRef.of(NettyTransportConfig.EventLoop.class)
          ), List.of());
      var _type_of_component52 = map.get("component52");
      component52 = graphDraw.addNode0(_type_of_component52, new Class<?>[]{}, g -> new $NettyTransportConfig_ConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component51)
          ), List.of(), component51);
      var _type_of_component53 = map.get("component53");
      component53 = graphDraw.addNode0(_type_of_component53, new Class<?>[]{}, g -> impl.nettyTransportConfig(
            g.get(ApplicationGraph.holder0.component8),
            g.get(ApplicationGraph.holder0.component52)
          ), List.of(), component8, component52);
      var _type_of_component54 = map.get("component54");
      component54 = graphDraw.addNode0(_type_of_component54, new Class<?>[]{ru.tinkoff.kora.netty.common.NettyCommonModule.WorkerLoopGroup.class, }, g -> impl.nettyEventLoopGroupLifecycle(
            (ThreadFactory) null,
            g.get(ApplicationGraph.holder0.component53)
          ), List.of(), component53);
      var _type_of_component55 = map.get("component55");
      component55 = graphDraw.addNode0(_type_of_component55, new Class<?>[]{ru.tinkoff.kora.netty.common.NettyCommonModule.BossLoopGroup.class, }, g -> impl.nettyEventBossLoopGroupLifecycle(
            (ThreadFactory) null,
            g.get(ApplicationGraph.holder0.component53)
          ), List.of(), component53);
      var _type_of_component56 = map.get("component56");
      component56 = graphDraw.addNode0(_type_of_component56, new Class<?>[]{}, g -> impl.nettyChannelFactory(
            g.get(ApplicationGraph.holder0.component53)
          ), List.of(), component53);
      var _type_of_component57 = map.get("component57");
      component57 = graphDraw.addNode0(_type_of_component57, new Class<?>[]{}, g -> impl.slf4jGrpcServerLogger(), List.of());
      var _type_of_component58 = map.get("component58");
      component58 = graphDraw.addNode0(_type_of_component58, new Class<?>[]{}, g -> impl.defaultGrpcServerTelemetry(
            g.get(ApplicationGraph.holder0.component18),
            g.get(ApplicationGraph.holder0.component57),
            (GrpcServerMetricsFactory) null,
            (GrpcServerTracer) null
          ), List.of(), component18, component57);
      var _type_of_component59 = map.get("component59");
      component59 = graphDraw.addNode0(_type_of_component59, new Class<?>[]{}, g -> impl.grpcNettyServerBuilder(
            g.valueOf(ApplicationGraph.holder0.component18).map(v -> (GrpcServerConfig) v),
            g.get(ApplicationGraph.holder0.component49).value(),
            g.get(ApplicationGraph.holder0.component50).value(),
            g.get(ApplicationGraph.holder0.component54).value(),
            g.get(ApplicationGraph.holder0.component55).value(),
            g.get(ApplicationGraph.holder0.component56),
            g.valueOf(ApplicationGraph.holder0.component58).map(v -> (GrpcServerTelemetry) v)
          ), List.of(), component18.valueOf(), component49, component50, component54, component55, component56, component58.valueOf());
      var _type_of_component60 = map.get("component60");
      component60 = graphDraw.addNode0(_type_of_component60, new Class<?>[]{}, g -> impl.grpcNettyServer(
            g.valueOf(ApplicationGraph.holder0.component59).map(v -> (NettyServerBuilder) v),
            g.valueOf(ApplicationGraph.holder0.component18).map(v -> (GrpcServerConfig) v)
          ), List.of(), component59.valueOf(), component18.valueOf());
      var _type_of_component61 = map.get("component61");
      component61 = graphDraw.addNode0(_type_of_component61, new Class<?>[]{}, g -> impl.stringConfigValueExtractor(), List.of());
      var _type_of_component62 = map.get("component62");
      component62 = graphDraw.addNode0(_type_of_component62, new Class<?>[]{}, g -> impl.<String>setConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component61)
          ), List.of(), component61);
      var _type_of_component63 = map.get("component63");
      component63 = graphDraw.addNode0(_type_of_component63, new Class<?>[]{}, g -> new $HttpServerLoggerConfig_ConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component62)
          ), List.of(), component62);
      var _type_of_component64 = map.get("component64");
      component64 = graphDraw.addNode0(_type_of_component64, new Class<?>[]{}, g -> new $HttpServerTelemetryConfig_ConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component63),
            g.get(ApplicationGraph.holder0.component12),
            g.get(ApplicationGraph.holder0.component15)
          ), List.of(), component63, component12, component15);
      var _type_of_component65 = map.get("component65");
      component65 = graphDraw.addNode0(_type_of_component65, new Class<?>[]{}, g -> new $HttpServerConfig_ConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component10),
            g.get(ApplicationGraph.holder0.component64)
          ), List.of(), component10, component64);
      var _type_of_component66 = map.get("component66");
      component66 = graphDraw.addNode0(_type_of_component66, new Class<?>[]{}, g -> impl.httpServerConfig(
            g.get(ApplicationGraph.holder0.component8),
            g.get(ApplicationGraph.holder0.component65)
          ), List.of(), component8, component65);
      var _type_of_component67 = map.get("component67");
      component67 = graphDraw.addNode0(_type_of_component67, new Class<?>[]{}, g -> new $AuthRestController__AopProxy(
            g.get(ApplicationGraph.holder0.component44),
            g.get(ApplicationGraph.holder0.component45),
            g.get(ApplicationGraph.holder0.component30),
            (StructuredArgumentMapper<ValidateRequest>) null,
            (StructuredArgumentMapper<AuthApiResponses.CheckTokenApiResponse>) null,
            (StructuredArgumentMapper<AuthRequest>) null,
            (StructuredArgumentMapper<AuthApiResponses.LoginApiResponse>) null
          ), List.of(), component44, component45, component30);
      var _type_of_component68 = map.get("component68");
      component68 = graphDraw.addNode0(_type_of_component68, new Class<?>[]{}, g -> new AuthApiController(
            g.get(ApplicationGraph.holder0.component67)
          ), List.of(), component67);
      var _type_of_component69 = map.get("component69");
      component69 = graphDraw.addNode0(_type_of_component69, new Class<?>[]{}, g -> new $AuthRequest_JsonReader(), List.of());
      var _type_of_component70 = map.get("component70");
      component70 = graphDraw.addNode0(_type_of_component70, new Class<?>[]{ru.tinkoff.kora.json.common.annotation.Json.class, }, g -> impl.<AuthRequest>jsonRequestMapper(
            g.get(ApplicationGraph.holder0.component69)
          ), List.of(), component69);
      var _type_of_component71 = map.get("component71");
      component71 = graphDraw.addNode0(_type_of_component71, new Class<?>[]{}, g -> new $AuthResponse_JsonWriter(), List.of());
      var _type_of_component72 = map.get("component72");
      component72 = graphDraw.addNode0(_type_of_component72, new Class<?>[]{ru.tinkoff.kora.json.common.annotation.Json.class, }, g -> impl.<AuthResponse>jsonResponseMapper(
            g.get(ApplicationGraph.holder0.component71)
          ), List.of(), component71);
      var _type_of_component73 = map.get("component73");
      component73 = graphDraw.addNode0(_type_of_component73, new Class<?>[]{}, g -> new $Login400Response_JsonWriter(), List.of());
      var _type_of_component74 = map.get("component74");
      component74 = graphDraw.addNode0(_type_of_component74, new Class<?>[]{ru.tinkoff.kora.json.common.annotation.Json.class, }, g -> impl.<Login400Response>jsonResponseMapper(
            g.get(ApplicationGraph.holder0.component73)
          ), List.of(), component73);
      var _type_of_component75 = map.get("component75");
      component75 = graphDraw.addNode0(_type_of_component75, new Class<?>[]{}, g -> new $AuthErrorResponse_JsonWriter(), List.of());
      var _type_of_component76 = map.get("component76");
      component76 = graphDraw.addNode0(_type_of_component76, new Class<?>[]{ru.tinkoff.kora.json.common.annotation.Json.class, }, g -> impl.<AuthErrorResponse>jsonResponseMapper(
            g.get(ApplicationGraph.holder0.component75)
          ), List.of(), component75);
      var _type_of_component77 = map.get("component77");
      component77 = graphDraw.addNode0(_type_of_component77, new Class<?>[]{}, g -> new AuthApiServerResponseMappers.LoginApiResponseMapper(
            g.get(ApplicationGraph.holder0.component72),
            g.get(ApplicationGraph.holder0.component74),
            g.get(ApplicationGraph.holder0.component76)
          ), List.of(), component72, component74, component76);
      var _type_of_component78 = map.get("component78");
      component78 = graphDraw.addNode0(_type_of_component78, new Class<?>[]{io.undertow.Undertow.class, }, g -> impl.xnioWorker(
            g.valueOf(ApplicationGraph.holder0.component66).map(v -> (HttpServerConfig) v)
          ), List.of(), component66.valueOf());
      var _type_of_component79 = map.get("component79");
      component79 = graphDraw.addNode0(_type_of_component79, new Class<?>[]{}, g -> impl.undertowBlockingRequestExecutor(
            g.get(ApplicationGraph.holder0.component78).value()
          ), List.of(), component78);
      var _type_of_component80 = map.get("component80");
      component80 = graphDraw.addNode0(_type_of_component80, new Class<?>[]{}, g -> impl.module0.post_api_auth(
            g.get(ApplicationGraph.holder0.component68),
            g.get(ApplicationGraph.holder0.component70),
            g.get(ApplicationGraph.holder0.component77),
            g.get(ApplicationGraph.holder0.component79)
          ), List.of(), component68, component70, component77, component79);
      var _type_of_component81 = map.get("component81");
      component81 = graphDraw.addNode0(_type_of_component81, new Class<?>[]{}, g -> new $ValidateRequest_JsonReader(), List.of());
      var _type_of_component82 = map.get("component82");
      component82 = graphDraw.addNode0(_type_of_component82, new Class<?>[]{ru.tinkoff.kora.json.common.annotation.Json.class, }, g -> impl.<ValidateRequest>jsonRequestMapper(
            g.get(ApplicationGraph.holder0.component81)
          ), List.of(), component81);
      var _type_of_component83 = map.get("component83");
      component83 = graphDraw.addNode0(_type_of_component83, new Class<?>[]{}, g -> new $ValidateResponse_JsonWriter(), List.of());
      var _type_of_component84 = map.get("component84");
      component84 = graphDraw.addNode0(_type_of_component84, new Class<?>[]{ru.tinkoff.kora.json.common.annotation.Json.class, }, g -> impl.<ValidateResponse>jsonResponseMapper(
            g.get(ApplicationGraph.holder0.component83)
          ), List.of(), component83);
      var _type_of_component85 = map.get("component85");
      component85 = graphDraw.addNode0(_type_of_component85, new Class<?>[]{}, g -> new $CheckToken400Response_JsonWriter(), List.of());
      var _type_of_component86 = map.get("component86");
      component86 = graphDraw.addNode0(_type_of_component86, new Class<?>[]{ru.tinkoff.kora.json.common.annotation.Json.class, }, g -> impl.<CheckToken400Response>jsonResponseMapper(
            g.get(ApplicationGraph.holder0.component85)
          ), List.of(), component85);
      var _type_of_component87 = map.get("component87");
      component87 = graphDraw.addNode0(_type_of_component87, new Class<?>[]{}, g -> new AuthApiServerResponseMappers.CheckTokenApiResponseMapper(
            g.get(ApplicationGraph.holder0.component84),
            g.get(ApplicationGraph.holder0.component86)
          ), List.of(), component84, component86);
      var _type_of_component88 = map.get("component88");
      component88 = graphDraw.addNode0(_type_of_component88, new Class<?>[]{}, g -> impl.module0.post_api_validate(
            g.get(ApplicationGraph.holder0.component68),
            g.get(ApplicationGraph.holder0.component82),
            g.get(ApplicationGraph.holder0.component87),
            g.get(ApplicationGraph.holder0.component79)
          ), List.of(), component68, component82, component87, component79);
      var _type_of_component89 = map.get("component89");
      component89 = graphDraw.addNode0(_type_of_component89, new Class<?>[]{}, g -> new OpenApiProvider(
            g.get(ApplicationGraph.holder0.component6)
          ), List.of(), component6);
      var _type_of_component90 = map.get("component90");
      component90 = graphDraw.addNode0(_type_of_component90, new Class<?>[]{}, g -> new OpenApiController(
            g.get(ApplicationGraph.holder0.component89)
          ), List.of(), component89);
      var _type_of_component91 = map.get("component91");
      component91 = graphDraw.addNode0(_type_of_component91, new Class<?>[]{}, g -> impl.module6.get_docs_openapi_yaml(
            g.get(ApplicationGraph.holder0.component90),
            g.get(ApplicationGraph.holder0.component79)
          ), List.of(), component90, component79);
      var _type_of_component92 = map.get("component92");
      component92 = graphDraw.addNode0(_type_of_component92, new Class<?>[]{}, g -> impl.module6.options_(
            g.get(ApplicationGraph.holder0.component90),
            g.get(ApplicationGraph.holder0.component79)
          ), List.of(), component90, component79);
      var _type_of_component93 = map.get("component93");
      component93 = graphDraw.addNode0(_type_of_component93, new Class<?>[]{}, g -> impl.<String>listConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component61)
          ), List.of(), component61);
      var _type_of_component94 = map.get("component94");
      component94 = graphDraw.addNode0(_type_of_component94, new Class<?>[]{}, g -> new $OpenApiManagementConfig_SwaggerUIConfig_ConfigValueExtractor(), List.of());
      var _type_of_component95 = map.get("component95");
      component95 = graphDraw.addNode0(_type_of_component95, new Class<?>[]{}, g -> new $OpenApiManagementConfig_RapidocConfig_ConfigValueExtractor(), List.of());
      var _type_of_component96 = map.get("component96");
      component96 = graphDraw.addNode0(_type_of_component96, new Class<?>[]{}, g -> new $OpenApiManagementConfig_ConfigValueExtractor(
            g.get(ApplicationGraph.holder0.component93),
            g.get(ApplicationGraph.holder0.component94),
            g.get(ApplicationGraph.holder0.component95)
          ), List.of(), component93, component94, component95);
      var _type_of_component97 = map.get("component97");
      component97 = graphDraw.addNode0(_type_of_component97, new Class<?>[]{}, g -> impl.openApiManagementConfig(
            g.get(ApplicationGraph.holder0.component8),
            g.get(ApplicationGraph.holder0.component96)
          ), List.of(), component8, component96);
      var _type_of_component98 = map.get("component98");
      component98 = graphDraw.addNode0(_type_of_component98, new Class<?>[]{}, g -> impl.openApiManagementController(
            g.get(ApplicationGraph.holder0.component97)
          ), List.of(), component97);
      var _type_of_component99 = map.get("component99");
      component99 = graphDraw.addNode0(_type_of_component99, new Class<?>[]{}, g -> impl.rapidocManagementController(
            g.get(ApplicationGraph.holder0.component97)
          ), List.of(), component97);
      var _type_of_component100 = map.get("component100");
      component100 = graphDraw.addNode0(_type_of_component100, new Class<?>[]{}, g -> impl.swaggerUIManagementController(
            g.get(ApplicationGraph.holder0.component97)
          ), List.of(), component97);
      var _type_of_component101 = map.get("component101");
      component101 = graphDraw.addNode0(_type_of_component101, new Class<?>[]{ru.tinkoff.kora.http.server.common.HttpServerModule.class, }, g -> new HttpExceptionHandler(
            g.get(ApplicationGraph.holder0.component75)
          ), List.of(), component75);
      var _type_of_component102 = map.get("component102");
      component102 = graphDraw.addNode0(_type_of_component102, new Class<?>[]{}, g -> impl.slf4jHttpServerLoggerFactory(), List.of());
      var _type_of_component103 = map.get("component103");
      component103 = graphDraw.addNode0(_type_of_component103, new Class<?>[]{}, g -> impl.defaultHttpServerTelemetryFactory(
            g.get(ApplicationGraph.holder0.component102),
            (HttpServerMetricsFactory) null,
            (HttpServerTracerFactory) null
          ), List.of(), component102);
      var _type_of_component104 = map.get("component104");
      component104 = graphDraw.addNode0(_type_of_component104, new Class<?>[]{}, g -> impl.publicApiHandler(
            All.of(
              g.get(ApplicationGraph.holder0.component80),
              g.get(ApplicationGraph.holder0.component88),
              g.get(ApplicationGraph.holder0.component91),
              g.get(ApplicationGraph.holder0.component92),
              g.get(ApplicationGraph.holder0.component98),
              g.get(ApplicationGraph.holder0.component99),
              g.get(ApplicationGraph.holder0.component100)
              ),
            All.of(
              g.get(ApplicationGraph.holder0.component101)
              ),
            g.get(ApplicationGraph.holder0.component103),
            g.get(ApplicationGraph.holder0.component66)
          ), List.of(), component80, component88, component91, component92, component98, component99, component100, component101, component103, component66);
      var _type_of_component105 = map.get("component105");
      component105 = graphDraw.addNode0(_type_of_component105, new Class<?>[]{}, g -> impl.undertowPublicApiHandler(
            g.get(ApplicationGraph.holder0.component104),
            (HttpServerTracerFactory) null,
            g.get(ApplicationGraph.holder0.component66)
          ), List.of(), component104, component66);
      var _type_of_component106 = map.get("component106");
      component106 = graphDraw.addNode0(_type_of_component106, new Class<?>[]{io.undertow.Undertow.class, }, g -> impl.undertowPublicByteBufferPool(), List.of());
      var _type_of_component107 = map.get("component107");
      component107 = graphDraw.addNode0(_type_of_component107, new Class<?>[]{}, g -> impl.undertowHttpServer(
            g.valueOf(ApplicationGraph.holder0.component66).map(v -> (HttpServerConfig) v),
            g.valueOf(ApplicationGraph.holder0.component105).map(v -> (UndertowPublicApiHandler) v),
            g.get(ApplicationGraph.holder0.component78).value(),
            g.get(ApplicationGraph.holder0.component106)
          ), List.of(), component66.valueOf(), component105.valueOf(), component78, component106);
      var _type_of_component108 = map.get("component108");
      component108 = graphDraw.addNode0(_type_of_component108, new Class<?>[]{}, g -> Optional.<PrivateApiMetrics>ofNullable(
            (PrivateApiMetrics) null
          ), List.of());
      var _type_of_component109 = map.get("component109");
      component109 = graphDraw.addNode0(_type_of_component109, new Class<?>[]{}, g -> impl.privateApiHandler(
            g.valueOf(ApplicationGraph.holder0.component66).map(v -> (HttpServerConfig) v),
            g.valueOf(ApplicationGraph.holder0.component108).map(v -> (Optional<PrivateApiMetrics>) v),
            All.of(
              g.promiseOf(ApplicationGraph.holder0.component26).map(v -> (ReadinessProbe) v),
              g.promiseOf(ApplicationGraph.holder0.component60).map(v -> (ReadinessProbe) v),
              g.promiseOf(ApplicationGraph.holder0.component107).map(v -> (ReadinessProbe) v)
              ),
            All.of(  )
          ), List.of(), component66.valueOf(), component108.valueOf());
      var _type_of_component110 = map.get("component110");
      component110 = graphDraw.addNode0(_type_of_component110, new Class<?>[]{}, g -> impl.undertowPrivateApiHandler(
            g.get(ApplicationGraph.holder0.component109)
          ), List.of(), component109);
      var _type_of_component111 = map.get("component111");
      component111 = graphDraw.addNode0(_type_of_component111, new Class<?>[]{ru.tinkoff.kora.http.server.undertow.UndertowPrivateHttpServer.class, }, g -> impl.undertowPrivateByteBufferPool(), List.of());
      var _type_of_component112 = map.get("component112");
      component112 = graphDraw.addNode0(_type_of_component112, new Class<?>[]{}, g -> impl.undertowPrivateHttpServer(
            g.valueOf(ApplicationGraph.holder0.component66).map(v -> (HttpServerConfig) v),
            g.valueOf(ApplicationGraph.holder0.component110).map(v -> (UndertowPrivateApiHandler) v),
            g.get(ApplicationGraph.holder0.component78).value(),
            g.get(ApplicationGraph.holder0.component111)
          ), List.of(), component66.valueOf(), component110.valueOf(), component78, component111);
      var _type_of_component113 = map.get("component113");
      component113 = graphDraw.addNode0(_type_of_component113, new Class<?>[]{}, g -> impl.loggingLevelConfigValueExtractor(), List.of());
      var _type_of_component114 = map.get("component114");
      component114 = graphDraw.addNode0(_type_of_component114, new Class<?>[]{}, g -> impl.loggingConfig(
            g.get(ApplicationGraph.holder0.component8),
            g.get(ApplicationGraph.holder0.component113)
          ), List.of(), component8, component113);
      var _type_of_component115 = map.get("component115");
      component115 = graphDraw.addNode0(_type_of_component115, new Class<?>[]{}, g -> impl.loggingLevelApplier(), List.of());
      var _type_of_component116 = map.get("component116");
      component116 = graphDraw.addNode0(_type_of_component116, new Class<?>[]{}, g -> impl.loggingLevelRefresher(
            g.get(ApplicationGraph.holder0.component114),
            g.get(ApplicationGraph.holder0.component115)
          ), List.of(), component114, component115);
    }
  }
}
