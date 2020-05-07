/**
 * 
 */
package io.proximax.proximity.rest.v1.resources;

import static io.proximax.dfms.utils.HttpUtils.encode;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.Buffer;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import io.proximax.dfms.DriveRepository;
import io.proximax.dfms.StorageApi;
import io.proximax.dfms.cid.Cid;
import io.proximax.dfms.cid.multibase.Multibase;
import io.proximax.dfms.http.MultipartRequestContent;
import io.proximax.dfms.http.dtos.CidDTO;
import io.proximax.dfms.http.repos.DriveHttp;
import io.proximax.dfms.model.drive.DriveContent;
import io.proximax.dfms.model.drive.content.BaseContent;
import io.proximax.dfms.model.drive.content.InputStreamContent;
import io.proximax.dfms.model.drive.content.RawInputStreamContent;
import io.proximax.proximity.v1.api.DriveApi;
import io.proximax.proximity.v1.model.DashboardDTO;
import io.reactivex.Observable;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * @author tono
 *
 */
@Path("/drive")
public class DriveResource extends DriveApi {

   @Context
   private HttpServletRequest httpRequest;
   
   /**
    * 
    */
   public DriveResource() {
      // bean constructor
   }

   @Override
   public Response driveAdd(@NotNull String dst, String cid, Boolean flush) {
      try {
         String contentType = httpRequest.getContentType();
         InputStream body = httpRequest.getInputStream();
         DriveContent content = new RawInputStreamContent(Optional.empty(), body, MediaType.get(contentType));
         StorageApi api = new StorageApi(new URL("http://localhost:6366"));
         DriveRepository drive = api.createDriveRepository();
         Cid resp = drive.add(Cid.decode(cid), dst, content).blockingFirst();
         CidDTO cidDto = new CidDTO(resp.encode(Multibase.BASE_58_BTC));
         return Response.ok(cidDto).build();
      } catch (IOException e) {
         throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR).build());
      }
   }
   
   @Override
   public Response driveCp(@NotNull String src, @NotNull String dst, String cid, Boolean flush) {
      // TODO Auto-generated method stub
      return super.driveCp(src, dst, cid, flush);
   }

   @Override
   public Response driveFlush(String cid) {
      // TODO Auto-generated method stub
      return super.driveFlush(cid);
   }

   @Override
   public Response driveGet(@NotNull String src, String cid, Boolean flush) {
      // TODO Auto-generated method stub
      return super.driveGet(src, cid, flush);
   }

   @Override
   public Response driveLs(@NotNull String src, String cid) {
      // TODO Auto-generated method stub
      return super.driveLs(src, cid);
   }

   @Override
   public Response driveMkdir(@NotNull String src, String cid, Boolean flush) {
      // TODO Auto-generated method stub
      return super.driveMkdir(src, cid, flush);
   }

   @Override
   public Response driveMv(@NotNull String src, @NotNull String dst, String cid, Boolean flush) {
      // TODO Auto-generated method stub
      return super.driveMv(src, dst, cid, flush);
   }

   @Override
   public Response driveRm(@NotNull String src, String cid, Boolean flush, Boolean local) {
      // TODO Auto-generated method stub
      return super.driveRm(src, cid, flush, local);
   }

   @Override
   public Response driveStat(@NotNull String src, String cid) {
      // TODO Auto-generated method stub
      return super.driveStat(src, cid);
   }

   @RequiresAuthentication
   @Override
   public Response dashboardGet() {
      DashboardDTO dashboard = new DashboardDTO();
      dashboard.setHello("hello world");
      return Response.ok(dashboard).build();
   }

   
}
