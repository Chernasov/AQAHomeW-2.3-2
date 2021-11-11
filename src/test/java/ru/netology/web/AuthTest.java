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
        $("[data-test-id='login'] input").sendKeys(blockedUser.getLogin());
        $("[data-test-id='password'] input").sendKeys(blockedUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldBe(visible).shouldHave(text("Пользователь заблокирован"));
    }
}
