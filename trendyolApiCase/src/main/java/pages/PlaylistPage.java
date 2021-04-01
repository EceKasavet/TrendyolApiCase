package pages;

import com.google.common.io.Resources;
import io.restassured.response.Response;
import org.json.JSONObject;
import spec.RequestSpec;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;



public class PlaylistPage extends RequestSpec {

    public String createPlaylist(String userId) throws IOException {//playlist id dönülür.

        URL file = Resources.getResource("playlist.json");
        String playListJson = Resources.toString(file, Charset.defaultCharset());
        JSONObject json = new JSONObject(playListJson);
        json.put("name","teoman");
        json.put("description", "deneme");
        json.put("public","true");
    Response response =
            given()
                    .spec(super.getRequestSpecification())
                    .body(json.toString())
                    .when()
                    .post("/v1/users/{user_id}/playlists", userId)
                    .then()
                    .extract().response();
            response.prettyPrint();
            assertThat("Check status code 201", response.getStatusCode(), equalTo(201));
            assertThat("check id", response.getBody().jsonPath().getString("id"), not((equalTo(null))));
            String playlistId= response.getBody().jsonPath().getString("id");
            System.out.println("Playlist ID : " +playlistId);
            return playlistId;
}

    public void checkPlayListIsEmpty(String playListId){
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .when()
                        .get("/v1/playlists/{playlist_id}/tracks", playListId)
                        .then()
                        .statusCode(200)
                        .extract().response();
        response.prettyPrint();
    }

    public void addPlayListTracks(String playListId, List<Object> tracks){
        for(Object track: tracks)
        {
            given()
                    .spec(super.getRequestSpecification())
                    .queryParam("uris",track)
                    .when()
                    .post("/playlists/{playlist_id}/tracks",playListId)
                    .then()
                    //.statusCode(201)
                    .extract().response();
        }}
    public void deletePlayLists(String playListId,Object deleteTracksID) throws IOException {
        URL file = Resources.getResource("deletePlaylist.json");
        String deletePlayListJson = Resources.toString(file, Charset.defaultCharset());
        JSONObject deletejson = new JSONObject(deletePlayListJson);
        deletejson.put("uri",deleteTracksID);
        Response response=
                given()
                        .spec(super.getRequestSpecification())
                        .body(deletejson.toString())
                        .when()
                        .delete("/v1/playlists/{playlist_id}/tracks",playListId)
                        .then()
                        .statusCode(200)
                        .extract().response();
        response.prettyPrint();


    }
}
