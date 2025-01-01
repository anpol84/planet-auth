package ru.planet.auth.repository;

import ru.tinkoff.kora.database.common.annotation.Query;
import ru.tinkoff.kora.database.common.annotation.Repository;
import ru.tinkoff.kora.database.jdbc.JdbcRepository;
import ru.planet.auth.dto.User;
import ru.tinkoff.kora.logging.common.annotation.Log;

import javax.annotation.Nullable;
import java.util.List;

@Repository
public interface AuthRepository extends JdbcRepository {

    @Log
    @Query("SELECT id, username as login, password FROM users WHERE username = :login")
    @Nullable
    User findByLogin(String login);

    @Log
    @Query("SELECT r.id FROM user_roles ur JOIN roles r on ur.role_id = r.id WHERE ur.user_id = :id")
    List<String> findRoles(Long id);
}
