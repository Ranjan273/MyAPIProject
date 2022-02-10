package com.Rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class RequestPayloadComplexjson {

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
    public void validatePostRequestPayloadComplexjson(){

        List<Integer> idArrayList=new ArrayList<Integer>();
        idArrayList.add(5);
        idArrayList.add(9);

        HashMap<String,Object> batterHashmap2=new HashMap<String ,Object>();
        batterHashmap2.put("id",idArrayList);
        batterHashmap2.put("type","Chocolate");

        HashMap<String,Object> batterHashmap1=new HashMap<String,Object>();
        batterHashmap1.put("id","1001");
        batterHashmap1.put("type","Regular");

        List<HashMap<String,Object>> batterList=new ArrayList<HashMap<String,Object>>();
        batterList.add(batterHashmap1);
        batterList.add(batterHashmap2);

        HashMap<String,List<HashMap<String,Object>>> battersHashMap
                =new HashMap<String,List<HashMap<String,Object>>>();
        battersHashMap.put("batter",batterList);

        List<String> typeArrayList=new ArrayList<String>();
        typeArrayList.add("test1");
        typeArrayList.add("test2");

        HashMap<String,Object> toppingHashMap2=new HashMap<String,Object>();
        toppingHashMap2.put("id","5002");
        toppingHashMap2.put("type",typeArrayList);

        HashMap<String,Object> toppingHashMap1=new HashMap<String,Object>();
        toppingHashMap1.put("id","5001");
        toppingHashMap1.put("type","None");

        List<HashMap<String,Object>> toppingArrayList=new ArrayList<HashMap<String,Object>>();
        toppingArrayList.add(toppingHashMap1);
        toppingArrayList.add(toppingHashMap2);

        HashMap<String ,Object> mainHashMap=new HashMap<String,Object>();
        mainHashMap.put("id","0001");
        mainHashMap.put("type","donut");
        mainHashMap.put("name","Cake");
        mainHashMap.put("ppu",0.55);
        mainHashMap.put("batters",battersHashMap);
        mainHashMap.put("topping",toppingArrayList);

        given()
                .body(mainHashMap)
                .when()
                .post("/PostComplexjson")
                .then().spec(customresponsespecification)
                .assertThat().body("msg", equalTo("Success"));
    }


}
