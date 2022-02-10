package com.Rest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
public class RequestParameter {

    @Test
    public void singleParameter(){

        given()
                .baseUri("https://postman-echo.com")
                .param("Foo1","bar1")
                .log().all()
                .when()
                .get("/get")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void multipleQueryParameter(){

        HashMap<String ,String > queryparams=new HashMap<String,String>();
        queryparams.put("foo1","bar1");
        queryparams.put("foo2","bar2");

        given()
                .baseUri("https://postman-echo.com")
               // .param("Foo1","bar1")
               // .queryParam("Foo1","bar1")
               // .queryParam("Foo2","bar2")
                .queryParams(queryparams)
                .log().all()
                .when()
                .get("/get")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void multiValueQueryParam(){
        given()
                .baseUri("https://postman-echo.com")
                .param("Foo1","bar1,bar2;bar3")
                .log().all()
                .when()
                .get("/get")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void pathParam(){
        given()
                .baseUri("https://reqres.in")
                .pathParam("userid","2")
               // .pathParam("BookId","3")
                .log().all()
                .when()
                .get("/api/users/{userid}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void multiPartFormData(){
        given()
                .baseUri("https://postman-echo.com")
                .multiPart("foo1","bar1")
                .when()
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }


}
