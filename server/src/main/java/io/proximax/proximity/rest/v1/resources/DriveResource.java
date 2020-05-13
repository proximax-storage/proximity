/**
 * 
 */
package io.proximax.proximity.rest.v1.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import io.proximax.dfms.cid.Cid;
import io.proximax.dfms.cid.multibase.Multibase;
import io.proximax.dfms.http.dtos.CidDTO;
import io.proximax.dfms.model.drive.DriveContent;
import io.proximax.dfms.model.drive.DriveItem;
import io.proximax.dfms.model.drive.content.RawInputStreamContent;
import io.proximax.proximity.drive.AuthenticatedDrive;
import io.proximax.proximity.v1.api.DriveApi;
import io.proximax.proximity.v1.model.DashboardDTO;
import io.proximax.proximity.v1.model.StatDTO;
import io.proximax.proximity.v1.model.StatDTO.TypeEnum;

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
   private AuthenticatedDrive drive;
   
   /**
    * 
    */
   public DriveResource() {
      // bean constructor
   }  
   
   @RequiresAuthentication
   @Override
   public Response driveAdd(@NotNull String dst, String cid, Boolean flush) {
      logger.info("Adding content to {}:{}", cid, dst);
      Optional<Cid> contract = Optional.ofNullable(cid==null?null:Cid.decode(cid));
      try {
         // prepare to forward the call
         String contentType = httpRequest.getContentType();
         InputStream body = httpRequest.getInputStream();
         DriveContent content = new RawInputStreamContent(Optional.empty(), body, contentType);
         // forward the call
         Cid resp = drive.add(contract, dst, content).blockingFirst();
         // return cid as response
         CidDTO cidDto = new CidDTO(resp.encode(Multibase.BASE_58_BTC));
         return Response.ok(cidDto).build();
      } catch (IOException e) {
         logger.warn("Failed to process request", e);
         throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
      }
   }
   
   @RequiresAuthentication
   @Override
   public Response driveCp(@NotNull String src, @NotNull String dst, String cid, Boolean flush) {
      logger.info("Copying from {} to {}", src, dst);
      Optional<Cid> contract = Optional.ofNullable(cid==null?null:Cid.decode(cid));
      drive.copy(contract, src, dst).blockingAwait();
      return Response.ok().build();
   }

   @RequiresAuthentication
   @Override
   public Response driveFlush(String cid) {
      logger.info("Flushing");
      Optional<Cid> contract = Optional.ofNullable(cid==null?null:Cid.decode(cid));
      drive.flush(contract, "/").blockingAwait();
      return Response.ok().build();
   }

   @RequiresAuthentication
   @Override
   public Response driveGet(@NotNull String src, String cid, Boolean flush) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("not implemented yet");
   }

   @RequiresAuthentication
   @Override
   public Response driveLs(@NotNull String src, String cid) {
      logger.info("List for {}", src);
      Optional<Cid> contract = Optional.ofNullable(cid==null?null:Cid.decode(cid));
      List<DriveItem> items = drive.ls(contract, src).blockingFirst();
      List<StatDTO> stats = items.stream().map(DriveResource::mapToStatDTO).collect(Collectors.toList());
      return Response.ok(stats).build();
   }

   protected static StatDTO mapToStatDTO(DriveItem item) {
      StatDTO stat = new StatDTO();
      stat.setName(item.getName());
      stat.setSize((int)item.getSize());
      stat.setType(TypeEnum.fromValue(item.getType().getCode()));
      return stat;
   }
   
   @RequiresAuthentication
   @Override
   public Response driveMkdir(@NotNull String src, String cid, Boolean flush) {
      logger.info("Mkdir {}", src);
      Optional<Cid> contract = Optional.ofNullable(cid==null?null:Cid.decode(cid));
      drive.makeDir(contract, src).blockingAwait();
      return Response.ok().build();
   }

   @RequiresAuthentication
   @Override
   public Response driveMv(@NotNull String src, @NotNull String dst, String cid, Boolean flush) {
      logger.info("Moving from {} to {}", src, dst);
      Optional<Cid> contract = Optional.ofNullable(cid==null?null:Cid.decode(cid));
      drive.move(contract, src, dst).blockingAwait();
      return Response.ok().build();
   }

   @RequiresAuthentication
   @Override
   public Response driveRm(@NotNull String src, String cid, Boolean flush, Boolean local) {
      logger.info("Remove {}", src);
      Optional<Cid> contract = Optional.ofNullable(cid==null?null:Cid.decode(cid));
      drive.remove(contract, src).blockingAwait();
      return Response.ok().build();
   }

   @RequiresAuthentication
   @Override
   public Response driveStat(@NotNull String src, String cid) {
      logger.info("Stat for {}", src);
      Optional<Cid> contract = Optional.ofNullable(cid==null?null:Cid.decode(cid));
      DriveItem item = drive.stat(contract, src).blockingFirst();
      StatDTO stat = new StatDTO();
      stat.setName(item.getName());
      stat.setSize((int)item.getSize());
      stat.setType(TypeEnum.fromValue(item.getType().getCode()));
      return Response.ok(stat).build();
   }

   @RequiresAuthentication
   @Override
   public Response dashboardGet() {
      DashboardDTO dashboard = new DashboardDTO();
      dashboard.setHello("hello world");
      return Response.ok(dashboard).build();
   }

   
}
