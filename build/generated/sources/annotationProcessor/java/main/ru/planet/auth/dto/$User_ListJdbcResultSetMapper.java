package ru.planet.auth.dto;

import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ru.tinkoff.kora.common.annotation.Generated;
import ru.tinkoff.kora.database.jdbc.mapper.result.JdbcResultSetMapper;

@Generated("ru.tinkoff.kora.database.annotation.processor.jdbc.extension.JdbcTypesExtension")
public final class $User_ListJdbcResultSetMapper implements JdbcResultSetMapper<List<User>> {
  public $User_ListJdbcResultSetMapper() {
  }

  public final List<User> apply(ResultSet _rs) throws SQLException {
    if (!_rs.next()) {
      return List.of();
    }
    var _idColumn = _rs.findColumn("id");
    var _loginColumn = _rs.findColumn("login");
    var _passwordColumn = _rs.findColumn("password");
    var _result = new ArrayList<User>();
    do {
      Long id = _rs.getLong(_idColumn);
      if (_rs.wasNull()) {
        throw new NullPointerException("Result field id is not nullable but row id has null");
      }
      String login = _rs.getString(_loginColumn);
      if (_rs.wasNull()) {
        throw new NullPointerException("Result field login is not nullable but row login has null");
      }
      String password = _rs.getString(_passwordColumn);
      if (_rs.wasNull()) {
        throw new NullPointerException("Result field password is not nullable but row password has null");
      }
      var _row = new User(
            id,
            login,
            password
          );
      _result.add(_row);

    } while (_rs.next());
    return _result;
  }
}
