package com.Rest;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class VerifyFilter {

    @Test
    public void filterVerification(){

        given()
                .baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(LogDetail.BODY))
                .filter(new ResponseLoggingFilter(LogDetail.STATUS))
                //.log().all()
                .when()
                .get("/get")
                .then()
                //.log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void filterValdationUsingPrintstream() throws FileNotFoundException {

        PrintStream fileteroutputstream = new PrintStream(new File("RestAssured..log"));

        given()
                .baseUri("https://postman-echo.com")
                .filter(new RequestLoggingFilter(LogDetail.BODY, fileteroutputstream))
                .filter(new ResponseLoggingFilter(LogDetail.STATUS, fileteroutputstream))
                //.log().all()
                .when()
                .get("/get")
                .then()
                //.log().all()
                .assertThat()
                .statusCode(200);
    }
}
