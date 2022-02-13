package com.Rest;

import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.*;

public class UploadFile {

    @Test
    public void uploadFileMultipartFormData(){
        String attribute="{\"name\":\"My First Workspace\",\"parent\":{\"id\",\"12345\"}}";
        given()
                .baseUri("https://postman-echo.com")
                .multiPart("file",new File("My First Workspace.txt"))
                .multiPart("attributes",attribute,"application/json")
                .when()
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void downLoadFile() throws IOException {
        byte[] bytes=given()
                .baseUri("https://raw.githubusercontent.com")
                .log().all()
                .when()
                .get("/appium/appium/master/sample-code/apps/ApiDemos-debug.apk")
                .then()
                .log().all()
                .extract().asByteArray()
                ;

        OutputStream os=new FileOutputStream(new File("ApiDemos-debug.apk"));
        os.write(bytes);
        os.close();
    }

    @Test
    public void form_urlencoded(){
        given().
                baseUri("https://postman-echo.com")
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .formParam("key1","value1")
                .formParam("key 2","value 2")
                .log().all()
                .when()
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
