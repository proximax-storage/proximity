/**
 * 
 */
package io.proximax.proximity.util;

import static io.proximax.dfms.utils.HttpUtils.encode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.google.gson.annotations.SerializedName;

import io.proximax.dfms.DriveRepository;
import io.proximax.dfms.ServiceNode;
import io.proximax.dfms.StorageApi;
import io.proximax.dfms.cid.Cid;
import io.proximax.dfms.http.HttpRepository;
import io.proximax.dfms.http.MultipartRequestContent;
import io.proximax.dfms.model.drive.DriveContent;
import io.proximax.dfms.model.drive.DriveItem;
import io.reactivex.Observable;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author tono
 *
 */
class AddFileTest {

   private static final String NAME = "/proximity-test-" + System.nanoTime();
   private static final Cid CID = Cid.decode("baegbeibondkkrhxfprzwrlgxxltavqhweh2ylhu4hgo5lxjxpqbpfsw2lu");
//                                    drive:*:baegbeibondkkrhxfprzwrlgxxltavqhweh2ylhu4hgo5lxjxpqbpfsw2lu
   @Test
   void testAddFile() throws IOException {
      TestRepo repo = new TestRepo();
      DriveContent content = DriveContent.from(Paths.get("schemas"));
      Cid cid = repo.add(CID, NAME, content).blockingFirst();
      assertNotNull(cid);
      // see if we can download the content from the drive
      StorageApi api = new StorageApi(new URL("http://localhost:6366"));
      DriveRepository drive = api.createDriveRepository();
      List<DriveItem> items = drive.ls(CID, NAME).blockingFirst();
      assertEquals(1, items.size());
      assertEquals("database.sql", items.get(0).getName());
   }

   /**
    * 
    * @author tono
    *
    */
   static class TestRepo extends HttpRepository<TestNode> {

      protected TestRepo() {
         super(new TestNode(), Optional.of("api/v1"), new OkHttpClient());
      }
      
      public Observable<Cid> add(Cid id, String path, DriveContent content) throws IOException {
         HttpUrl url = buildUrl("drive/add", id, path).build();
         String jwToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhdXRoZW50aWNhdGlvbiIsInN1YiI6InRvbm84QGxhbGEuaW8iLCJleHAiOjE1ODkzMDA5NTd9.6M0R3Zd6FjET5KVOyAbHS9FpjNyXvnFzWaFfoPUnDao";
         String authHeader = "Bearer " + jwToken;
         Request request = new Request.Builder().url(url).header("Authorization", authHeader).post(new MultipartRequestContent(content, MultipartRequestContent.createBoundary())).build();

         // make the request
         return makeRequest(request).map(TestRepo::log).map(this::mapStringOrError).map(TestRepo::log).map(str -> getGson().fromJson(str, CidDTO2.class))
               .map(CidDTO2::getId).map(Cid::decode);
      }

      private static String log(String message) {
         System.out.println("MAPPING -->> " + message);
         return message;
      }
      
      private static Response log(Response message) {
         System.out.println("RESP -->> " + message);
         return message;
      }
      
      protected Builder buildUrl(String command, Cid cid, String path) {
         HttpUrl.Builder builder = getApiUrl().newBuilder().addPathSegments(command);
         builder.addQueryParameter("cid", encode(cid));
         builder.addQueryParameter("dst", path);
         return builder;
      }
      
      /**
       * 
       * @author tono
       *
       */
      static class CidDTO2 {
         @SerializedName("id")
         private final String id;

         /**
          * @param id string representation
          */
         public CidDTO2(String id) {
            this.id = id;
         }

         /**
          * @return the id
          */
         public String getId() {
            return id;
         }
      }

   }
   
   /**
    * 
    * @author tono
    *
    */
   static class TestNode implements ServiceNode {

      @Override
      public URL getUrl() {
         try {
            return new URL("http://localhost:8080/server");
         } catch (MalformedURLException e) {
            throw new RuntimeException();
         }
      }
      
   }
}
