package com.Rest;

import groovyjarjarantlr.LexerSharedInputState;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ResponseSpecificationExampleTwo {
    @BeforeClass
    public void beforeclass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
        .setBaseUri("https://2a172dde-cad7-40b3-a020-b2c5f1b50095.mock.pstmn.io")
                .addHeader("x-mock-match-request-body","true")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);

       requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder().
                expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);

        responseSpecification=responseSpecBuilder.build();


    }

    @Test
    public void validatePostRequestJsonArrayasList(){

        HashMap<String,String> obj5001=new HashMap<String,String>();
        HashMap<String,String> obj5002=new HashMap<String,String>();
        obj5001.put("id","5001");
        obj5001.put("type","None");
        obj5002.put("id","5002");
        obj5002.put("type","Glazed");

        List<HashMap<String,String>> jsonList=new ArrayList<HashMap<String,String>>() ;
        jsonList.add(obj5001);
        jsonList.add(obj5002);



        given().body(jsonList)
                .when().post("/post")
                .then()
                .log().all()
                .assertThat().body("msg",equalTo("Success"));
    }

}
