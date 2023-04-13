package co.partha.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import co.partha.models.User;
import co.partha.services.UserService;

@Path("/user")
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @POST
    public User saveUser(User user) {
        return userService.saveUserToDB(user);
    }

    @GET
    @Path("/{name}/{email}")
    public User getUserNameAndEmail(@PathParam("name") String name, @PathParam("email") String email) {
        return userService.getUserNameAndEmail(name, email);
    }  
    
    @GET
    @Path("/ph/{name}/{phone}")
    @Operation(summary = "Get user by name and phone", description = "Get user by name and phone")
    @APIResponse(responseCode = "200", description = "User found")
    @APIResponse(responseCode = "404", description = "User not found")
    @Parameter(name = "name", description = "User name", required = true)
    @Parameter(name = "phone", description = "User phone", required = true)
    public Response getUserNameAndPhone(@PathParam("name") String name, @PathParam("phone") String phone) {
         Optional<User> user = userService.getUserNameAndPhone(name, phone);
         if(user.isPresent()) {
             return Response.ok(user.get()).build();
            }else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }   
    }

     //Api to update user
    @POST
    @Path("/update")
    public User updateUser(User user) {
        return userService.updateUser(user);
    }


    
}
