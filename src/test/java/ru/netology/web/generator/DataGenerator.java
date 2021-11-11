package ru.netology.web.generator;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.netology.web.domain.RegistrationDate;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

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
        RegistrationDate activeUser = new RegistrationDate("James", "123456", "active");
        setUpUser(activeUser);
        return activeUser;
    }

    public RegistrationDate setBlockedUser(){
        RegistrationDate blockedUser = new RegistrationDate("John", "127856", "blocked");
        setUpUser(blockedUser);
        return blockedUser;
    }
}
