package com.Rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class AutomatePost {

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder()
                .setBaseUri("https://api.getpostman.com")
                .addHeader("x-api-key","PMAK-619c85bfbb765b003b08bea9-a4e24546a56ebff51310b676cde6ecc36b")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();
        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder().
                expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

    }

    @Test
    public void validatePostRequest(){
        String payload="{\n" +
                "    \"workspace\":{\n" +
                "        \"name\":\"MyFirstWorkspace2\",\n" +
                "        \"type\":\"personal\",\n" +
                "        \"description\":\"Rest Assured Created This\"\n" +
                "    }\n" +
                "}";
        //Non-BDD Style
        Response response=with().body(payload)
                .post("/workspaces");
        assertThat(response.<String>path("workspace.name"), equalTo("MyFirstWorkspace2"));
        assertThat(response.<String>path("workspace.id"),matchesPattern("^[a-z 0-9-]{36}$"));

       /*BDD Style

        given().body(payload)
                .when().post("/workspaces")
                .then()
                  .assertThat().body("workspace.name", equalTo("MyFirstWorkspace"),
                                           "workspace.id",matchesPattern("^[a-z 0-9-]{36}$"));*/

    }

    @Test
    public void validate_putRequest(){
        //BDD Style
        String workspaceID="89c57e05-1d46-4d32-9ae4-70443e0abff5";
        String payload="{\n" +
                "    \"workspace\":{\n" +
                "        \"name\":\"NewWorkspaceName\",\n" +
                "        \"type\":\"personal\",\n" +
                "        \"description\":\"Rest Assured Created This\"\n" +
                "    }\n" +
                "}";
        given().body(payload)
                .when().put("/workspaces/89c57e05-1d46-4d32-9ae4-70443e0abff5")
                .then().log().all()
                .assertThat().body("workspace.name", equalTo("NewWorkspaceName"),
                "workspace.id",matchesPattern("^[a-z 0-9-]{36}$")
                ,"workspace.id",equalTo(workspaceID));
    }

    @Test
    public void validate_DeleteRequest(){
        String workspaceID="89c57e05-1d46-4d32-9ae4-70443e0abff5";
        given().
                pathParam("workspaceID","workspaceID")
                .when().
                delete("/workspaces/89c57e05-1d46-4d32-9ae4-70443e0abff5")
                .then().
                log().all()
                .assertThat().
                body(
                        "workspace.id",matchesPattern("^[a-z 0-9-]{36}$")
                        ,"workspace.id",equalTo(workspaceID));
    }


}
