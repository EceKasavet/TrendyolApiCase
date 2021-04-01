package pages;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import spec.RequestSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ArtistPage extends RequestSpec {
    String artistId="40KlwpvpKEQtZTJgbml8lT";

    public void getAnArtist(String artistID) {
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .when()
                        .get("/v1/artists/{id}", artistId)
                        .then()
                        .extract().response();
        assertThat("Check status code 200", response.getStatusCode(), equalTo(200));
        response.prettyPrint();
    }

    public void getAnArtistTopTracks(String artistID) {
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("market","TR")
                        .when()
                        .get("/v1/artists/{id}/top-tracks", artistId)
                        .then()
                        .extract().response();
        assertThat("Check status code 200", response.getStatusCode(), equalTo(200));
        response.prettyPrint();
    }

}