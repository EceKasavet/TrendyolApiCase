package pages;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import spec.RequestSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserPage extends RequestSpec {

    public String getUser(){
        Response response=
                given()
                        .spec(super.getRequestSpecification())
                        .when()
                        .get("/v1/me")
                        .then()
                        .extract().response();
        response.prettyPrint();
        assertThat("Check status code 200", response.getStatusCode(), equalTo(200));
        assertThat(" Check id", response.getBody().jsonPath().getString("id"), not((equalTo(null))));
        return response.getBody().jsonPath().getString("id");
    }

    public void getUserProfile(String userId){

    Response response=
            given()
                    .spec(super.getRequestSpecification())
                    .when()
                    .get("/v1/users/{user_id}",userId)
                    .then()
                    .extract().response();
    assertThat("Check status code 200", response.getStatusCode(), equalTo(200));
}
}
