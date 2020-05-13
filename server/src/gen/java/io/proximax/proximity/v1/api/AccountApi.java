package io.proximax.proximity.v1.api;

import io.proximax.proximity.v1.model.AccountInfoDTO;
import io.proximax.proximity.v1.model.AccountLoginDTO;
import io.proximax.proximity.v1.model.AccountRequestDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/account")
@Api(description = "the account API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-05-13T22:46:24.699+02:00[Europe/Prague]")
public class AccountApi {

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Get account information", notes = "retrieve account info for current session", response = AccountInfoDTO.class, authorizations = {
        @Authorization(value = "bearerTokenAuth")
    }, tags={ "Account",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = AccountInfoDTO.class)
    })
    public Response accountGet() {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/login")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Login user", notes = "Login user using his credentials", response = AccountInfoDTO.class, authorizations = {
        @Authorization(value = "bearerTokenAuth")
    }, tags={ "Account",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = AccountInfoDTO.class)
    })
    public Response accountLogin(@Valid AccountLoginDTO accountLoginDTO) {
        return Response.ok().entity("magic!").build();
    }

    @POST
    @Path("/register")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Register new account", notes = "Register new account based on provided information", response = AccountInfoDTO.class, authorizations = {
        @Authorization(value = "bearerTokenAuth")
    }, tags={ "Account",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = AccountInfoDTO.class)
    })
    public Response accountRegister(@Valid AccountRequestDTO accountRequestDTO) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/tokens")
    @Produces({ "application/json" })
    @ApiOperation(value = "Get account tokens", notes = "Developer API to retrieve valid tokens for all supported operations. Remove or very tightly secure this", response = AccountInfoDTO.class, authorizations = {
        @Authorization(value = "bearerTokenAuth")
    }, tags={ "Account",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = AccountInfoDTO.class)
    })
    public Response accountTokens() {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/validate")
    @Produces({ "application/json" })
    @ApiOperation(value = "Validate account", notes = "Validate account information", response = AccountInfoDTO.class, authorizations = {
        @Authorization(value = "bearerTokenAuth")
    }, tags={ "Account" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = AccountInfoDTO.class)
    })
    public Response accountValidate(@QueryParam("emailToken")   @ApiParam("Email validation token sent via e-mail")  String emailToken) {
        return Response.ok().entity("magic!").build();
    }
}
