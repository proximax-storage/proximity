/**
 * 
 */
package io.proximax.proximity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.proximax.proximity.client.api.AccountApi;
import io.proximax.proximity.client.api.DriveApi;
import io.proximax.proximity.client.invoker.ApiClient;
import io.proximax.proximity.client.invoker.ApiException;
import io.proximax.proximity.client.invoker.auth.HttpBearerAuth;
import io.proximax.proximity.client.model.AccountInfoDTO;
import io.proximax.proximity.client.model.AccountLoginDTO;
import io.proximax.proximity.client.model.DashboardDTO;

/**
 * @author tono
 *
 */
public class SimpleTest {

   /**
    * 
    */
   public SimpleTest() {
      // TODO Auto-generated constructor stub
   }

   @Test
   void test() throws ApiException {
      ApiClient client = new ApiClient();
      client.setBasePath("http://localhost:8080/server/api/v1");
      // first login and configure bearer authentication
      {
         AccountApi apiInstance = new AccountApi(client);
         AccountLoginDTO accountLoginDTO = new AccountLoginDTO();
         accountLoginDTO.setEmail("tono8@lala.io");
         accountLoginDTO.setPassword("pass");
         AccountInfoDTO accInfo = apiInstance.accountLogin(accountLoginDTO);
         String token = accInfo.getToken();
         // configure api client to use the token
         HttpBearerAuth bearerTokenAuth = (HttpBearerAuth) client.getAuthentication("bearerTokenAuth");
         bearerTokenAuth.setBearerToken(token);

      }
      // now make request to drive
      DriveApi drive = new DriveApi(client);
      DashboardDTO dashboard = drive.dashboardGet();
      assertEquals("hello world", dashboard.getHello());
   }

}
