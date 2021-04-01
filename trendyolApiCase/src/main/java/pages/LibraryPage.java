package pages;

import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import spec.RequestSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class LibraryPage extends RequestSpec {


    public void getCurrentUsersSavedAlbums() {
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("limit", "5")
                        .queryParam("market", "TR")
                        .when()
                        .get("/v1/me/albums")
                        .then()
                        .extract().response();
        response.prettyPrint();
        assertThat("Check status code 200", response.getStatusCode(), equalTo(200));
        response.prettyPrint();
        //String name = response.getBody().jsonPath().getString("items.name");
        //System.out.println(name);
        //assertThat(name, Matchers.containsString("Bağzıları"));
        assertThat(response.getBody().asString(), Matchers.containsString("Bağzıları"));
    }

    public void getCurrentUsersSavedTracks() {
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("limit", "5")
                        .queryParam("market", "TR")
                        .when()
                        .get("/v1/me/tracks")
                        .then()
                        .extract().response();
        response.prettyPrint();
        assertThat("Check status code 200", response.getStatusCode(), equalTo(200));
        response.prettyPrint();
        //String name = response.getBody().jsonPath().getString("items.name");
        //System.out.println(name);
        //assertThat(name, Matchers.containsString("Bağzıları"));
        assertThat(response.getBody().asString(), Matchers.containsString("Bağzıları"));
       //String tracksId = ((RestAssuredResponseImpl) response).response().path("items.id");
       //return tracksId;
    }

    public void getUserSavedShows() {
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("limit", "5")
                        .queryParam("offset", "3")
                        .when()
                        .get("/v1/me/shows")
                        .then()
                        .extract().response();
        response.prettyPrint();
        assertThat("Check status code 200", response.getStatusCode(), equalTo(200));
        response.prettyPrint();
        assertThat("check total: ", response.getBody().jsonPath().getString("total"), ((equalTo("0"))));
    }

    public void getCurrentUserSavedEpisodes() {
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("market", "TR")
                        .queryParam("limit", "5")
                        .when()
                        .get("/v1/me/episodes")
                        .then()
                        .extract().response();
        response.prettyPrint();
        assertThat("Check status code 200", response.getStatusCode(), equalTo(200));
        response.prettyPrint();
        assertThat("check previous: ", response.getBody().jsonPath().getString("previous"), ((equalTo(null))));
        assertThat("check total: ", response.getBody().jsonPath().getString("total"), ((equalTo("0"))));
    }

}
