package ru.netology.web.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationDate {
    private final String login;
    private final String password;
    private final String status;
}
