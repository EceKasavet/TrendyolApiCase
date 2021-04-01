package pages;

import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import spec.RequestSpec;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SearchPage extends RequestSpec {

    public List searchForAnItem() {//list

        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("q", "Teoman")
                        .queryParam("type", "artist")
                        .queryParam("limit", "3")
                        .when()
                        .get("/v1/search")
                        .then()
                        .extract().response();
         assertThat("Status code kontrolu 200 olmali", response.getStatusCode(), equalTo(200));
         response.prettyPrint();
         List<Object> artistId = ((RestAssuredResponseImpl) response).response().path("artists.items.id");
        //String Id=response.getBody().jsonPath().getString("artists.items.id");
         System.out.println(artistId);
        //assertThat(response.getBody().asString(), Matchers.containsString("Gönülçelen"));
//        System.out.println(a.get(0));
        return artistId;

    }


    public String searchForItem() {

        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("q", "Teoman")
                        .queryParam("type", "artist")
                        .queryParam("limit", "5")
                        .when()
                        .get("/v1/search")
                        .then()
                        .extract().response();
         assertThat("Check status code 200", response.getStatusCode(), equalTo(200));
         //  response.prettyPrint();
        String uriArea = ((RestAssuredResponseImpl) response).response().path("artist.items.id");
        // System.out.println(uriArea);
        return uriArea;

    }




}

