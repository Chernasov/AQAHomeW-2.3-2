package ru.netology.web.generator;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.netology.web.domain.RegistrationDate;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private Faker faker = new Faker(new Locale("en"));

    static void setUpUser(RegistrationDate user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public RegistrationDate setActiveUser(){
        RegistrationDate activeUser = new RegistrationDate(faker.name().firstName(), faker.internet().password(), "active");
        setUpUser(activeUser);
        return activeUser;
    }

    public RegistrationDate setBlockedUser(){
        RegistrationDate blockedUser = new RegistrationDate(faker.name().firstName(), faker.internet().password(), "blocked");
        setUpUser(blockedUser);
        return blockedUser;
    }
}
