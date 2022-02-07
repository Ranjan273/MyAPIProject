package com.Rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RequestSpecificationExample {
          // RequestSpecification requestSpecification;
    @BeforeClass
    public void beforeClass(){
        /* requestSpecification=
                 with().baseUri("https://api.getpostman.com")
                .header("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b")
                .log().all();*/

        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.getpostman.com");
        requestSpecBuilder.addHeader("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b");
        requestSpecBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

        responseSpecification=RestAssured.expect()
                .statusCode(200).contentType(ContentType.JSON);
    }

    @Test
    public void validate_statuscode(){

        Response response=get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void validate_responsebody(){
        Response response=get("/workspaces").then().log().all().extract().response();
                assertThat(response.statusCode(),is(equalTo(200)));
                assertThat(response.path("workspaces[1].name").toString(),equalTo("PetStore"));

    }

    @Test
    public  void queryTest(){
        QueryableRequestSpecification queryableRequestSpecification= SpecificationQuerier.query(RestAssured.requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());
    }
}