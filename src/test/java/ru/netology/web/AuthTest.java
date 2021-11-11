package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.domain.RegistrationDate;
import ru.netology.web.generator.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
//import static com.codeborne.selenide.Selenide.open;

public class AuthTest {


    @BeforeEach
    void setUpPage() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTruePath() {
        DataGenerator user = new DataGenerator();
        RegistrationDate activeUser = user.setActiveUser();
        $("[data-test-id='login'] input").sendKeys(activeUser.getLogin());
        $("[data-test-id='password'] input").sendKeys(activeUser.getPassword());
        $("[data-test-id='action-login']").click();
        $(byText("Личный кабинет")).shouldBe(visible);
    }

    @Test
    void shouldUseBlockedUser() {
        DataGenerator user = new DataGenerator();
        RegistrationDate blockedUser = user.setBlockedUser();
        System.out.println("Block OK");
    }
}
