package ru.planet.auth.repository;

import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import ru.planet.auth.dto.User;
import ru.tinkoff.kora.common.AopProxy;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.database.jdbc.JdbcConnectionFactory;
import ru.tinkoff.kora.database.jdbc.mapper.result.JdbcResultSetMapper;
import ru.tinkoff.kora.logging.common.arg.StructuredArgument;
import ru.tinkoff.kora.logging.common.arg.StructuredArgumentMapper;

@AopProxy
@Generated({"ru.tinkoff.kora.aop.annotation.processor.AopAnnotationProcessor", "ru.tinkoff.kora.logging.aspect.LogAspect"})
public final class $$AuthRepository_Impl__AopProxy extends $AuthRepository_Impl {
  private final ILoggerFactory iLoggerFactory1;

  private final StructuredArgumentMapper<String> structuredArgumentMapper1;

  private final StructuredArgumentMapper<User> structuredArgumentMapper2;

  private final StructuredArgumentMapper<Long> structuredArgumentMapper3;

  private final StructuredArgumentMapper<List<String>> structuredArgumentMapper4;

  private final Logger logger1;

  private final Logger logger2;

  public $$AuthRepository_Impl__AopProxy(JdbcConnectionFactory _connectionFactory,
      JdbcResultSetMapper<User> _result_mapper_1,
      JdbcResultSetMapper<List<String>> _result_mapper_2, ILoggerFactory iLoggerFactory1,
      @Nullable StructuredArgumentMapper<String> structuredArgumentMapper1,
      @Nullable StructuredArgumentMapper<User> structuredArgumentMapper2,
      @Nullable StructuredArgumentMapper<Long> structuredArgumentMapper3,
      @Nullable StructuredArgumentMapper<List<String>> structuredArgumentMapper4) {
    super(_connectionFactory, _result_mapper_1, _result_mapper_2);
    this.iLoggerFactory1 = iLoggerFactory1;
    this.structuredArgumentMapper1 = structuredArgumentMapper1;
    this.structuredArgumentMapper2 = structuredArgumentMapper2;
    this.structuredArgumentMapper3 = structuredArgumentMapper3;
    this.structuredArgumentMapper4 = structuredArgumentMapper4;
    this.logger1 = iLoggerFactory1.getLogger("ru.planet.auth.repository.$AuthRepository_Impl.findByLogin");
    this.logger2 = iLoggerFactory1.getLogger("ru.planet.auth.repository.$AuthRepository_Impl.findRoles");
  }

  private User _findByLogin_AopProxy_LogAspect(String login) {
    if (logger1.isDebugEnabled()) {
      var __dataIn = StructuredArgument.marker("data", gen ->  {
        gen.writeStartObject();
        if (this.structuredArgumentMapper1 != null) {
          gen.writeFieldName("login");
          this.structuredArgumentMapper1.write(gen, login);
        } else {
          gen.writeStringField("login", String.valueOf(login));
        }
        gen.writeEndObject();
      } );
      logger1.info(__dataIn, ">");
    } else {
      logger1.info(">");
    }

    try {
      var __result = super.findByLogin(login);
      if (logger1.isDebugEnabled()) {
        var __dataOut = StructuredArgument.marker("data", gen -> {
          gen.writeStartObject();
          if (this.structuredArgumentMapper2 != null) {
            gen.writeFieldName("out");
            this.structuredArgumentMapper2.write(gen, __result);
          } else {
            gen.writeStringField("out", String.valueOf(__result));
          }
          gen.writeEndObject();
        }
        );
        logger1.info(__dataOut, "<");
      } else {
        logger1.info("<");
      }
      return __result;
    } catch(Throwable __error) {
      if (logger1.isWarnEnabled()) {
        var __dataError = StructuredArgument.marker("data", gen -> {
          gen.writeStartObject();
          gen.writeStringField("errorType", __error.getClass().getCanonicalName());
          gen.writeStringField("errorMessage", __error.getMessage());
          gen.writeEndObject();
        } );

        if(logger1.isDebugEnabled()) {
          logger1.warn(__dataError, "<", __error);
        } else {
          logger1.warn(__dataError, "<");
        }
      }
      throw __error;
    }
  }

  @Override
  public User findByLogin(String login) {
    return this._findByLogin_AopProxy_LogAspect(login);
  }

  private List<String> _findRoles_AopProxy_LogAspect(Long id) {
    if (logger2.isDebugEnabled()) {
      var __dataIn = StructuredArgument.marker("data", gen ->  {
        gen.writeStartObject();
        if (this.structuredArgumentMapper3 != null) {
          gen.writeFieldName("id");
          this.structuredArgumentMapper3.write(gen, id);
        } else {
          gen.writeStringField("id", String.valueOf(id));
        }
        gen.writeEndObject();
      } );
      logger2.info(__dataIn, ">");
    } else {
      logger2.info(">");
    }

    try {
      var __result = super.findRoles(id);
      if (logger2.isDebugEnabled()) {
        var __dataOut = StructuredArgument.marker("data", gen -> {
          gen.writeStartObject();
          if (this.structuredArgumentMapper4 != null) {
            gen.writeFieldName("out");
            this.structuredArgumentMapper4.write(gen, __result);
          } else {
            gen.writeStringField("out", String.valueOf(__result));
          }
          gen.writeEndObject();
        }
        );
        logger2.info(__dataOut, "<");
      } else {
        logger2.info("<");
      }
      return __result;
    } catch(Throwable __error) {
      if (logger2.isWarnEnabled()) {
        var __dataError = StructuredArgument.marker("data", gen -> {
          gen.writeStartObject();
          gen.writeStringField("errorType", __error.getClass().getCanonicalName());
          gen.writeStringField("errorMessage", __error.getMessage());
          gen.writeEndObject();
        } );

        if(logger2.isDebugEnabled()) {
          logger2.warn(__dataError, "<", __error);
        } else {
          logger2.warn(__dataError, "<");
        }
      }
      throw __error;
    }
  }

  @Override
  public List<String> findRoles(Long id) {
    return this._findRoles_AopProxy_LogAspect(id);
  }
}
