package ru.planet.auth.repository;

import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import ru.planet.auth.dto.User;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.database.common.QueryContext;
import ru.tinkoff.kora.database.jdbc.JdbcConnectionFactory;
import ru.tinkoff.kora.database.jdbc.JdbcRepository;
import ru.tinkoff.kora.database.jdbc.mapper.result.JdbcResultSetMapper;
import ru.tinkoff.kora.logging.common.annotation.Log;

@Generated("ru.tinkoff.kora.database.annotation.processor.RepositoryAnnotationProcessor")
public class $AuthRepository_Impl implements AuthRepository, JdbcRepository {
  private static final QueryContext QUERY_CONTEXT_1 = new QueryContext(
        "SELECT id, username as login, password FROM users WHERE username = :login",
        "SELECT id, username as login, password FROM users WHERE username = ?",
        "AuthRepository.findByLogin"
      );

  private static final QueryContext QUERY_CONTEXT_2 = new QueryContext(
        "SELECT r.name FROM user_roles ur JOIN roles r on ur.role_id = r.id WHERE ur.user_id = :id",
        "SELECT r.name FROM user_roles ur JOIN roles r on ur.role_id = r.id WHERE ur.user_id = ?",
        "AuthRepository.findRoles"
      );

  private final JdbcConnectionFactory _connectionFactory;

  private final JdbcResultSetMapper<User> _result_mapper_1;

  private final JdbcResultSetMapper<List<String>> _result_mapper_2;

  public $AuthRepository_Impl(JdbcConnectionFactory _connectionFactory,
      JdbcResultSetMapper<User> _result_mapper_1,
      JdbcResultSetMapper<List<String>> _result_mapper_2) {
    this._connectionFactory = _connectionFactory;
    this._result_mapper_1 = _result_mapper_1;
    this._result_mapper_2 = _result_mapper_2;
  }

  @Override
  public final JdbcConnectionFactory getJdbcConnectionFactory() {
    return this._connectionFactory;
  }

  @Override
  @Log
  @Nullable
  public User findByLogin(String login) {
    var _ctxCurrent = ru.tinkoff.kora.common.Context.current();
    var _query = QUERY_CONTEXT_1;
    var _telemetry = this._connectionFactory.telemetry().createContext(_ctxCurrent, _query);
    var _conToUse = this._connectionFactory.currentConnection();
    Connection _conToClose;
    if (_conToUse == null) {
        _conToUse = this._connectionFactory.newConnection();
        _conToClose = _conToUse;
    } else {
        _conToClose = null;
    }
    try (_conToClose; var _stmt = _conToUse.prepareStatement(_query.sql())) {
      _stmt.setString(1, login);
      try (var _rs = _stmt.executeQuery()) {
        var _result = _result_mapper_1.apply(_rs);
        _telemetry.close(null);
        return _result;
      }

    } catch (java.sql.SQLException e) {
      _telemetry.close(e);
      throw new ru.tinkoff.kora.database.jdbc.RuntimeSqlException(e);
    } catch (Exception e) {
      _telemetry.close(e);
      throw e;
    }
  }

  @Override
  @Log
  public List<String> findRoles(Long id) {
    var _ctxCurrent = ru.tinkoff.kora.common.Context.current();
    var _query = QUERY_CONTEXT_2;
    var _telemetry = this._connectionFactory.telemetry().createContext(_ctxCurrent, _query);
    var _conToUse = this._connectionFactory.currentConnection();
    Connection _conToClose;
    if (_conToUse == null) {
        _conToUse = this._connectionFactory.newConnection();
        _conToClose = _conToUse;
    } else {
        _conToClose = null;
    }
    try (_conToClose; var _stmt = _conToUse.prepareStatement(_query.sql())) {
      _stmt.setLong(1, id);
      try (var _rs = _stmt.executeQuery()) {
        var _result = _result_mapper_2.apply(_rs);
        _telemetry.close(null);
        return Objects.requireNonNull(_result, "Result mapping is expected non-null, but was null");
      }

    } catch (java.sql.SQLException e) {
      _telemetry.close(e);
      throw new ru.tinkoff.kora.database.jdbc.RuntimeSqlException(e);
    } catch (Exception e) {
      _telemetry.close(e);
      throw e;
    }
  }
}
