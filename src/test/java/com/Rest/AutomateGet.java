package com.Rest;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.Collections;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AutomateGet {

    @Test
    public void GetCall(){
        given().baseUri("https://api.getpostman.com")
                .header("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b")
                .when().get("/workspaces")
                .then()
                .assertThat().statusCode(200).log().all();
    }

    @Test
    public void validateResponse(){
        given().baseUri("https://api.getpostman.com")
                .header("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b")
                .when().get("/workspaces")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("workspaces.name",hasItems("My Workspace", "PetStore", "Atlassian", "DummyApi", "Team Workspace", "Workspace1", "Automation Testing", "HerokuBoking", "OmPrakash", "Postman", "ReqBIn", "ParaBank", "FruitShop"),
                "workspaces.type",hasItems("personal", "team", "personal", "team", "team", "personal", "team", "team", "personal", "team", "team", "team", "personal"),
                        "workspaces[1].name",equalTo("PetStore"),
                        "workspaces[1].name",is(equalTo("PetStore")),
                        "workspaces.size()",equalTo(13),
                        "workspaces.name",hasItem("Atlassian"));


    }

    @Test
    public void extractResponse(){
        Response res=given().baseUri("https://api.getpostman.com")
                .header("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b")
                .when().get("/workspaces")
                .then().assertThat()
                        .statusCode(200)
                         .extract()
                          .response();


        System.out.println("response -:" +res.asString());
    }

    @Test
    public void extractSingleValue(){
        Response res=given().baseUri("https://api.getpostman.com")
                .header("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b")
                .when().get("/workspaces")
                .then().assertThat()
                        .statusCode(200)
                        .extract().response();

        JsonPath jp=new JsonPath(res.asString());
        System.out.println("Response workspacename is  :"+jp.getString("workspaces[2].name"));

        //System.out.println("Response workspacename is  :"+res.path("workspaces[2].name"));--Another way
    }

    @Test
    public void humcrestAssertonExtractedResponse(){
        String name=given().baseUri("https://api.getpostman.com")
                .header("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b")
                .when().get("/workspaces")
                .then().assertThat()
                .statusCode(200)
                .extract().response().path("workspaces[2].name");

        System.out.println("Workspace name is :"+name);

        assertThat(name,equalTo("Atlassian"));

        Assert.assertEquals(name,"Atlassian");
    }

    @Test
    public void validateResponselist() {
        given().baseUri("https://api.getpostman.com")
                .header("x-api-key", "PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b")
                .when().get("/workspaces")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("workspaces.name", contains("My Workspace", "PetStore", "Atlassian", "DummyApi", "Team Workspace", "Workspace1", "Automation Testing", "HerokuBoking", "OmPrakash", "Postman", "ReqBIn", "ParaBank", "FruitShop")
                ,"workspaces.name",is(not(emptyArray()))
                        ,"workspaces.name",hasSize(13)
                        //,"workspaces.name",everyItem(startsWith("My"))
                        ,"workspaces[0]",hasKey("id")
                        ,"workspaces[0]",hasValue("My Workspace")
                        ,"workspaces[0]",hasEntry("type","personal")
                        ,"workspaces[0]",not(equalTo(Collections.EMPTY_MAP))
                        ,"workspaces[0].name",allOf(startsWith("M"),containsString("Workspace"))
                );
    }

    @Test
    public void request_response_logging(){
     given().baseUri("https://api.getpostman.com")
             .header("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b")
             .config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key")))
             //.config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
             .log().all()
             .when().get("/workspaces")
             .then()
             .log().all()
             .assertThat()
             .statusCode(200);
    }

    @Test
    public void automateHeaders(){

        given().
                baseUri("https://2a172dde-cad7-40b3-a020-b2c5f1b50095.mock.pstmn.io")
                .header("headername","value1")
                .header("x-mock-match-request-headers","headername")
                .when().get("/get")
                .then().log().all()
                .assertThat().statusCode(200);

    }

    @Test
    public void automateHeaderObject(){
        Header header=new Header("headername","value1");
        Header header1=new Header("x-mock-match-request-headers","headername");

        Headers headers=new Headers(header,header1);

        given().
                baseUri("https://2a172dde-cad7-40b3-a020-b2c5f1b50095.mock.pstmn.io")
                .headers(headers)
                .when().get("/get")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void automateHeaderusingMap(){

        HashMap<String ,String> hm=new HashMap<String,String>();
        hm.put("headername","value1");
        hm.put("x-mock-match-request-headers","headername");
       // Header header=new Header("headername","value1");
        //Header header1=new Header("x-mock-match-request-headers","headername");

        //Headers headers=new Headers(header,header1);

        given().
                baseUri("https://2a172dde-cad7-40b3-a020-b2c5f1b50095.mock.pstmn.io")
                .headers(hm)
                .when().get("/get")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void validateresponseHeaderusingAlert(){
        HashMap<String ,String> hm=new HashMap<String,String>();
        hm.put("headername","value1");
        hm.put("x-mock-match-request-headers","headername");

        given().
                baseUri("https://2a172dde-cad7-40b3-a020-b2c5f1b50095.mock.pstmn.io")
                .headers(hm)
                .when().get("/get")
                .then().log().all()
                .assertThat().statusCode(200)
                .header("responseHeader","resValue1")
                .header("X-RateLimit-Limit","120");

    }

    @Test
    public void extractResponseHeader(){
        HashMap<String ,String> hm=new HashMap<String,String>();
        hm.put("headername","value1");
        hm.put("x-mock-match-request-headers","headername");

        Headers extractHeaders=given().
                baseUri("https://2a172dde-cad7-40b3-a020-b2c5f1b50095.mock.pstmn.io")
                .headers(hm)
                .when().get("/get")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().headers();

        for(Header header : extractHeaders){
            System.out.println  ("Headername : "+header.getName()+ " HeaderValue : "+header.getValue());
        }

//        System.out.println("Headers is : "+extractHeaders.get("responseHeader"));
//        System.out.println("Headers is : "+extractHeaders.get("responseHeader").getName());
//        System.out.println("Headers is : "+extractHeaders.get("responseHeader").getValue());
//        System.out.println("Headers is : "+extractHeaders.getValue("responseHeader"));

    }
}






















