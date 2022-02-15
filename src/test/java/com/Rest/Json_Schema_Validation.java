package com.Rest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Json_Schema_Validation {

    @Test
    public void validateJsonSchema(){

        given().baseUri("https://postman-echo.com")
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("echoget.json"));
    }
}
