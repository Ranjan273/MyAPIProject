package com.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class JacksonAPIjsonArray {

    ResponseSpecification customresponsespecification;

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

        customresponsespecification=responseSpecBuilder.build();
    }

    @Test
    public void validatePostRequestjsonArrayasList() throws JsonProcessingException {
        HashMap<String,String> obj5001=new HashMap<>();
        obj5001.put("id","5001");
        obj5001.put("type","None");

        HashMap<String,String> obj5002=new HashMap<>();
        obj5002.put("id","5002");
        obj5002.put("type","Glazed");

        List<HashMap<String,String>> jsonList=new ArrayList<>();
        jsonList.add(obj5001);
        jsonList.add(obj5002);

        ObjectMapper objectMapper=new ObjectMapper();
        String oojectmapperstr=objectMapper.writeValueAsString(jsonList);

        given()
                .body(jsonList)
                .when()
                .post("/post")
                .then()
                .spec(customresponsespecification)
                .assertThat()
                .body("msg",equalTo("Success"));
    }

    @Test
    public void serialize_json_array_using_jackson() throws JsonProcessingException {

        ObjectMapper objectMapper=new ObjectMapper();
        ArrayNode arrayNodeList=objectMapper.createArrayNode();

        ObjectNode obj5001node=objectMapper.createObjectNode();
        obj5001node.put("id","5001");
        obj5001node.put("type","None");

        ObjectNode obj5002node=objectMapper.createObjectNode();
        obj5002node.put("id","5002");
        obj5002node.put("type","Glazed");

        /*HashMap<String,String> obj5001=new HashMap<>();
        obj5001.put("id","5001");
        obj5001.put("type","None");

        HashMap<String,String> obj5002=new HashMap<>();
        obj5002.put("id","5002");
        obj5002.put("type","Glazed");*/

        //List<HashMap<String,String>> jsonList=new ArrayList<>();
        arrayNodeList.add(obj5001node);
        arrayNodeList.add(obj5002node);

        //ObjectMapper objectMapper=new ObjectMapper();
        String oojectmapperstr=objectMapper.writeValueAsString(arrayNodeList);

        given()
                .body(arrayNodeList)
                .when()
                .post("/post")
                .then()
                .spec(customresponsespecification)
                .assertThat()
                .body("msg",equalTo("Success"));

    }
}
