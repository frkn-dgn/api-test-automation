package tests;

import base.BaseTest;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {

    @Test
    public void getUsersTest() {

        Response response =
                request
                        .when()
                        .get("/users");

        System.out.println(response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void userCountTest() {

        Response response =
                request
                        .when()
                        .get("/users");

        int userCount = response.jsonPath().getList("$").size();

        Assert.assertEquals(userCount, 10);
    }

    @Test
    public void createUserTest() {

        User user = new User("John", "QA Engineer");

        Response response =
                request
                        .body(user)
                        .when()
                        .post("/users");

        System.out.println(response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), "John");
    }
}