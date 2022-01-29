package com.Rest;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestOne {

    @Test
    public void test1(){

        given().baseUri("https://petstore.swagger.io/v2/store")
                .header("Accept","application/json")
                .log().all()
                .when().get("/inventory")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("pending", Matchers.is(Matchers.equalTo( 131 )),
                                            "available",Matchers.is(Matchers.equalTo( 467)));
    }


    @Test(enabled = false)
    public void ValidateResponse(){
        given().baseUri("https://petstore.swagger.io/v2/store")
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 7,\n" +
                        "  \"petId\": 8,\n" +
                        "  \"quantity\": 5,\n" +
                        "  \"shipDate\": \"2021-11-21T00:57:06.478Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .when().post("/order")
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
