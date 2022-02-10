package com.Rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ResposnseSpecificationExample {

    @BeforeClass
    public void beforeclass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.getpostman.com");
        requestSpecBuilder.addHeader("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b");
        requestSpecBuilder.log(LogDetail.ALL);

        RestAssured.requestSpecification=requestSpecBuilder.build();

        responseSpecification=RestAssured.expect()
                .statusCode(200).contentType(ContentType.JSON);


    }

    @Test
    public void validatePostrequestPayloadfromFile(){

        File file=new File("src/main/resources/CreateWorkSpacePayload.json");
       /* String payload="{\n" +
                "    \"workspace\":{\n" +
                "        \"name\":\"MyFirstWorkspace\",\n" +
                "        \"type\":\"personal\",\n" +
                "        \"description\":\"Rest Assured Created This\"\n" +
                "    }\n" +
                "}";*/

        given()
                .body(file)
                .when()
                .post("/workspaces")
                .then()
                .log().all()
                .assertThat().body("workspace.name",equalTo("MySecondWorkspace"));


    }

    @Test
    public void validate_PostRequestPayload_asMap(){
        //File file=new File("src/main/resources/CreateWorkSpacePayload.json");

        HashMap<String,Object> mainObject=new HashMap<String,Object>();

        HashMap<String,String> nestedObject=new HashMap<String,String>();
        nestedObject.put("name","MyThirdWorkspace");
        nestedObject.put("type","personal");
        nestedObject.put("description","Rest Assured Created This");

        mainObject.put("workspace",nestedObject);

        given()
                .body(mainObject)
                .when()
                .post("/workspaces")
                .then()
                .log().all()
                .assertThat().body("workspace.name",equalTo("MyThirdWorkspace"));
    }




}
