package ru.planet.auth.dto;

import ru.tinkoff.kora.database.jdbc.EntityJdbc;

@EntityJdbc
public record User (Long id, String login, String password) {
}

