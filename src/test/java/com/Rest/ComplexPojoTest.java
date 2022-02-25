package com.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pojo.collection.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ComplexPojoTest {

    ResponseSpecification responseSpecification;
    private Object CollectionRoot;

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.getpostman.com")
                .addHeader("x-api-key", "PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void complex_Pojo_Create_Collection() throws JsonProcessingException {

        Header myheader=new Header("Content-Type","application/json","");
        List<Header> headerList=new ArrayList<Header>();
        headerList.add(myheader);

        Body body=new Body("raw","{\"data\": \"123\"}");

        RequestBaseRequest request=new RequestBaseRequest("echo.getpostman.com/post","POST",
                headerList,body,"This is a sample POST Request");

        RequestRootBaseRequest requestRoot=new RequestRootBaseRequest("Sample POST Request",request);
        List<RequestRootBaseRequest> requestRootList=new ArrayList<RequestRootBaseRequest>();
        requestRootList.add(requestRoot);

        FolderBaseRequest folder=new FolderBaseRequest("This is a folder",requestRootList);
        List<FolderBaseRequest> folderlist=new ArrayList<FolderBaseRequest>();
        folderlist.add(folder);

        Info info=new Info("Sample Collection","This is just a sample collection.",
                "https://schema.getpostman.com/json/collection/v2.0.0/collection.json",
                "ac03df1d-90f0-401d-aa57-39c395253c80");


        CollectionBaseRequest collection=new CollectionBaseRequest(info,folderlist);
        CollectionRootRequest collectionRoot=new CollectionRootRequest(collection);


        String collectionUid = given()
                .body(collectionRoot)
                .when()
                .post("/collections")
                .then()
                .spec(responseSpecification)
                 .extract().response().path("collection.uid");
                /*.extract()
                .response()
                .as(WorkspaceRoot.class);*/

        CollectionRootResponse deserializedCollectionRoot = given()
                .pathParam("collectionUid",collectionUid)
                .when()
                .get("/collections/{collectionUid}")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .as(CollectionRootResponse.class);

        /*ObjectMapper objectMapper=new ObjectMapper();
        String collectionrootstr=objectMapper.writeValueAsString(CollectionRoot);
        String desrializedcollectionrootstr=objectMapper.writeValueAsString(deserializedCollectionRoot);

        JSONAssert.assertEquals(collectionrootstr,desrializedcollectionrootstr,
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("collection.item[*].item[*].request.url", new ValueMatcher<Object>() {
                        public boolean equal(Object o1, Object o2){
                            return true;
                        }
                        })));*/

        List<String> urlRequestList = new ArrayList<>();
        List<String>  urlResponseList = new ArrayList<>();

        for(RequestRootBaseRequest requestRootBaseRequest : requestRootList){
            System.out.println("url from request payload "+requestRootBaseRequest.getRequest().getUrl());
           urlRequestList.add(requestRootBaseRequest.getRequest().getUrl());
        }

        List<FolderBaseResponse> folderresponselist=deserializedCollectionRoot.getCollection().getItem();

        for(FolderBaseResponse folderBaseResponse : folderresponselist){
            List<RequestRootBaseResponse> requestRootBaseResponselist=folderBaseResponse.getItem();

            for(RequestRootBaseResponse requestRootBaseResponse : requestRootBaseResponselist){
                URL url=requestRootBaseResponse.getRequest().getUrl();
                System.out.println("url from response :"+url.getRaw());
                urlResponseList.add(url.getRaw());
            }
        }

        assertThat(urlResponseList,containsInAnyOrder(urlRequestList.toArray()));

    }

    @Test
    public void simple_Pojo_Create_Collection() throws JsonProcessingException {

        /*Header myheader=new Header("Content-Type","application/json","");
        List<Header> headerList=new ArrayList<Header>();
        headerList.add(myheader);

        Body body=new Body("raw","{\"data\": \"123\"}");

        RequestBaseRequest request=new RequestBaseRequest("echo.getpostman.com/post","POST",
                headerList,body,"This is a sample POST Request");

        RequestRootBaseRequest requestRoot=new RequestRootBaseRequest("Sample POST Request",request);
        List<RequestRootBaseRequest> requestRootList=new ArrayList<RequestRootBaseRequest>();
        requestRootList.add(requestRoot);

        FolderBaseRequest folder=new FolderBaseRequest("This is a folder",requestRootList);*/
        List<FolderBaseRequest> folderlist=new ArrayList<FolderBaseRequest>();
        //folderlist.add(folder);

        Info info=new Info("Sample Collection2","This is just a sample collection.",
                "https://schema.getpostman.com/json/collection/v2.0.0/collection.json",
                "ac03df1d-90f0-401d-aa57-39c395253c80");


        CollectionBaseRequest collection=new CollectionBaseRequest(info,folderlist);
        CollectionRootRequest collectionRoot=new CollectionRootRequest(collection);


        String collectionUid = given()
                .body(collectionRoot)
                .when()
                .post("/collections")
                .then()
                .spec(responseSpecification)
                .extract().response().path("collection.uid");
                /*.extract()
                .response()
                .as(WorkspaceRoot.class);*/

        CollectionRootResponse deserializedCollectionRoot = given()
                .pathParam("collectionUid",collectionUid)
                .when()
                .get("/collections/{collectionUid}")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .as(CollectionRootResponse.class);

        ObjectMapper objectMapper=new ObjectMapper();
        String collectionrootstr=objectMapper.writeValueAsString(CollectionRoot);
        String desrializedcollectionrootstr=objectMapper.writeValueAsString(deserializedCollectionRoot);

        assertThat(objectMapper.readTree(collectionrootstr)
                   ,equalTo(objectMapper.readTree(desrializedcollectionrootstr)));

        /*JSONAssert.assertEquals(collectionrootstr,desrializedcollectionrootstr,
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("collection.item[*].item[*].request.url", new ValueMatcher<Object>() {
                        public boolean equal(Object o1, Object o2){
                            return true;
                        }
                        })));
*/
        /*List<String> urlRequestList = new ArrayList<>();
        List<String>  urlResponseList = new ArrayList<>();

        for(RequestRootBaseRequest requestRootBaseRequest : requestRootList){
            System.out.println("url from request payload "+requestRootBaseRequest.getRequest().getUrl());
            urlRequestList.add(requestRootBaseRequest.getRequest().getUrl());
        }

        List<FolderBaseResponse> folderresponselist=deserializedCollectionRoot.getCollection().getItem();

        for(FolderBaseResponse folderBaseResponse : folderresponselist){
            List<RequestRootBaseResponse> requestRootBaseResponselist=folderBaseResponse.getItem();

            for(RequestRootBaseResponse requestRootBaseResponse : requestRootBaseResponselist){
                URL url=requestRootBaseResponse.getRequest().getUrl();
                System.out.println("url from response :"+url.getRaw());
                urlResponseList.add(url.getRaw());
            }
        }

        assertThat(urlResponseList,containsInAnyOrder(urlRequestList.toArray()));*/

    }


}
