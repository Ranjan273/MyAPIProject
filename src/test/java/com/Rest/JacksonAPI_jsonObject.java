package com.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.equalTo;

public class JacksonAPI_jsonObject {
    private static ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.getpostman.com");
        requestSpecBuilder.addHeader("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b");
        requestSpecBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

        responseSpecification=RestAssured.expect()
                .statusCode(200).contentType(ContentType.JSON);
    }

    @Test
    public void validatepostrequestpayloadasMap() throws JsonProcessingException {

        HashMap<String,Object> mainobject=new HashMap<>();

        HashMap<String,String> nestedobject=new HashMap<>();
        nestedobject.put("name","myworkplaceone");
        nestedobject.put("type","personal");
        nestedobject.put("description","Rest Assured Created This");

        mainobject.put("workspace",nestedobject);

        ObjectMapper objectMapper=new ObjectMapper();
        String mainObjectstr=objectMapper.writeValueAsString(mainobject);

        given()
                .body(mainObjectstr)
                .when()
                .post("/workspaces")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body("workspace.name",equalTo("myworkplaceone"));

    }

    @Test
    public void serialize_Map_to_Json_using_Jackson() throws JsonProcessingException {

        ObjectMapper objectMapper=new ObjectMapper();
        ObjectNode nestedobjectNode=objectMapper.createObjectNode();

        nestedobjectNode.put("name","myworkspacetwo");
        nestedobjectNode.put("type","personal");
        nestedobjectNode.put("description","Rest Assured Created This");

        ObjectNode mainobjectnode=objectMapper.createObjectNode();
        mainobjectnode.set("workspace",nestedobjectNode);

       /* HashMap<String,Object> mainobject=new HashMap<>();

        HashMap<String,String> nestedobject=new HashMap<>();
        nestedobject.put("name","myworkplaceone");
        nestedobject.put("type","personal");
        nestedobject.put("description","Rest Assured Created This");

        mainobject.put("workspace",nestedobject);

        ObjectMapper objectMapper=new ObjectMapper();*/
        String mainObjectstr=objectMapper.writeValueAsString(mainobjectnode);

        given()
                .body(mainObjectstr)
        .when()
                .post("/workspaces")
        .then()
                .spec(responseSpecification)
                .assertThat()
                .body("workspace.name",equalTo("myworkspacetwo"));

    }


}
