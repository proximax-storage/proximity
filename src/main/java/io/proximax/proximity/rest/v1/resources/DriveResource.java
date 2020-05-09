/**
 * 
 */
package io.proximax.proximity.rest.v1.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proximax.dfms.DriveRepository;
import io.proximax.dfms.StorageApi;
import io.proximax.dfms.cid.Cid;
import io.proximax.dfms.cid.multibase.Multibase;
import io.proximax.dfms.http.dtos.CidDTO;
import io.proximax.dfms.model.drive.DriveContent;
import io.proximax.dfms.model.drive.content.RawInputStreamContent;
import io.proximax.proximity.v1.api.DriveApi;
import io.proximax.proximity.v1.model.DashboardDTO;

/**
 * @author tono
 *
 */
@Path("/drive")
public class DriveResource extends DriveApi {
   private static final Logger logger = LoggerFactory.getLogger(DriveResource.class);

   @Context
   private HttpServletRequest httpRequest;
   @Inject
   private StorageApi api;
   
   /**
    * 
    */
   public DriveResource() {
      // bean constructor
   }  

   @RequiresAuthentication
   @Override
   public Response driveAdd(@NotNull String dst, String cid, Boolean flush) {
      try {
         String contentType = httpRequest.getContentType();
         InputStream body = httpRequest.getInputStream();
         DriveContent content = new RawInputStreamContent(Optional.empty(), body, contentType);
         DriveRepository drive = api.createDriveRepository();
         Cid resp = drive.add(Cid.decode(cid), dst, content).blockingFirst();
         CidDTO cidDto = new CidDTO(resp.encode(Multibase.BASE_58_BTC));
         return Response.ok(cidDto).build();
      } catch (IOException e) {
         logger.warn("Failed to process request", e);
         throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
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
