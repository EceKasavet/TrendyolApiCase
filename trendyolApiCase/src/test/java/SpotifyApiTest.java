import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.List;

public class SpotifyApiTest {

   @Test
    public void ApiTest() throws IOException {
       SearchPage search= new SearchPage();
       String artistId= search.searchForItem();
       ArtistPage artist= new ArtistPage();
       artist.getAnArtistTopTracks(artistId);
       UserPage user= new UserPage();
       String userID= user.getUser();
       user.getUserProfile(userID);
       PlaylistPage playList= new PlaylistPage();
       String playListId=playList.createPlaylist(userID);
       playList.checkPlayListIsEmpty(playListId);
       List<Object> tracks=search.searchForAnItem();
       playList.addPlayListTracks(playListId,tracks);
      //playList.deletePlayLists(playListId,tracks.get(2));

   }
   @Test
    public void LibraryTest(){
       LibraryPage library=new LibraryPage();
       library.getUserSavedShows();
       library.getCurrentUsersSavedAlbums();
       library.getCurrentUsersSavedTracks();
       library.getCurrentUserSavedEpisodes();
   }


}
