package io.proximax.proximity.v1.api;

import io.proximax.proximity.v1.model.DashboardDTO;
import io.proximax.proximity.v1.model.ErrorDTO;
import io.proximax.proximity.v1.model.InlineResponse200;
import io.proximax.proximity.v1.model.StatDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/drive")
@Api(description = "the drive API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-05-07T21:14:05.934+02:00[Europe/Prague]")
public class DriveApi {

    @GET
    @Path("/dashboard")
    @Produces({ "application/json" })
    @ApiOperation(value = "dashboard information", notes = "retrieve statistics for the account", response = DashboardDTO.class, tags={ "DriveApi",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = DashboardDTO.class)
    })
    public Response dashboardGet() {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/add")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Add file", notes = "Sends file or directory to remote node which adds it to the path of the contract ", response = InlineResponse200.class, tags={ "DriveApi",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = InlineResponse200.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    public Response driveAdd(@QueryParam("dst") @NotNull  @DefaultValue("/")  @ApiParam("The destination path.")  String dst,@QueryParam("cid")   @ApiParam("[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. May represent data or Drive. ")  String cid,@QueryParam("flush")  @DefaultValue("false")  @ApiParam("To immediately send data to replicators")  Boolean flush) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/cp")
    @Produces({ "application/json" })
    @ApiOperation(value = "Copy file", notes = "Copy copies file or directory from the givens source path to the given destination path It does not makes the full copy of the file or directory, it just copies the reference ", response = Void.class, tags={ "DriveApi",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    public Response driveCp(@QueryParam("src") @NotNull   @ApiParam("The source path of the file in Drive.")  String src,@QueryParam("dst") @NotNull  @DefaultValue("/")  @ApiParam("The destination path.")  String dst,@QueryParam("cid")   @ApiParam("[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. May represent data or Drive. ")  String cid,@QueryParam("flush")  @DefaultValue("false")  @ApiParam("To immediately send data to replicators")  Boolean flush) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/flush")
    @Produces({ "application/json" })
    @ApiOperation(value = "Flush drive", notes = "Flush pushes state of the local Drive to all replicators", response = Void.class, tags={ "DriveApi",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    public Response driveFlush(@QueryParam("cid")   @ApiParam("[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. May represent data or Drive. ")  String cid) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/get")
    @Produces({ "text/plain", "application/json" })
    @ApiOperation(value = "Get file", notes = "Sends file or directory to remote node which adds it to the path of the contract ", response = String.class, tags={ "DriveApi",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = String.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    public Response driveGet(@QueryParam("src") @NotNull   @ApiParam("The source path of the file in Drive.")  String src,@QueryParam("cid")   @ApiParam("[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. May represent data or Drive. ")  String cid,@QueryParam("flush")  @DefaultValue("false")  @ApiParam("To immediately send data to replicators")  Boolean flush) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/ls")
    @Produces({ "application/json" })
    @ApiOperation(value = "List files", notes = "Ls returns information about the files and directories under the given path", response = StatDTO.class, responseContainer = "List", tags={ "DriveApi",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = StatDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    public Response driveLs(@QueryParam("src") @NotNull   @ApiParam("The source path of the file in Drive.")  String src,@QueryParam("cid")   @ApiParam("[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. May represent data or Drive. ")  String cid) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/mkdir")
    @Produces({ "application/json" })
    @ApiOperation(value = "Make directory", notes = "MakeDir creates new directory on the given path", response = Void.class, tags={ "DriveApi",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    public Response driveMkdir(@QueryParam("src") @NotNull   @ApiParam("The source path of the file in Drive.")  String src,@QueryParam("cid")   @ApiParam("[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. May represent data or Drive. ")  String cid,@QueryParam("flush")  @DefaultValue("false")  @ApiParam("To immediately send data to replicators")  Boolean flush) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/mv")
    @Produces({ "application/json" })
    @ApiOperation(value = "Move file", notes = "Move moves file or directory from the givens source path to the given destination path Use also to rename file or directory ", response = Void.class, tags={ "DriveApi",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    public Response driveMv(@QueryParam("src") @NotNull   @ApiParam("The source path of the file in Drive.")  String src,@QueryParam("dst") @NotNull  @DefaultValue("/")  @ApiParam("The destination path.")  String dst,@QueryParam("cid")   @ApiParam("[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. May represent data or Drive. ")  String cid,@QueryParam("flush")  @DefaultValue("false")  @ApiParam("To immediately send data to replicators")  Boolean flush) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/rm")
    @Produces({ "application/json" })
    @ApiOperation(value = "Remove file", notes = "Remove removes the file or directory from the path", response = Void.class, tags={ "DriveApi",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    public Response driveRm(@QueryParam("src") @NotNull   @ApiParam("The source path of the file in Drive.")  String src,@QueryParam("cid")   @ApiParam("[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. May represent data or Drive. ")  String cid,@QueryParam("flush")  @DefaultValue("false")  @ApiParam("To immediately send data to replicators")  Boolean flush,@QueryParam("local")  @DefaultValue("false")  @ApiParam("Delete file from local disk only, but keep reference on it remotely")  Boolean local) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/stat")
    @Produces({ "application/json" })
    @ApiOperation(value = "File information", notes = "Stat returns information about the file or directory under the given path", response = StatDTO.class, tags={ "DriveApi" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = StatDTO.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorDTO.class)
    })
    public Response driveStat(@QueryParam("src") @NotNull   @ApiParam("The source path of the file in Drive.")  String src,@QueryParam("cid")   @ApiParam("[Cid](https://github.com/multiformats/cid) (version 1) - special content identifier. May represent data or Drive. ")  String cid) {
        return Response.ok().entity("magic!").build();
    }
}
