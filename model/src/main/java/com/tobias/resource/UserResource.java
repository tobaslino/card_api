package com.tobias.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tobias.controller.Controller;
import com.tobias.entity.User;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    
    @Inject
    private Controller controller;

    @GET
    public List<User> findAll() {
        return User.listAll();
    }

    @POST
    @Transactional
    public Response create(User user) {
        User.persist(user);
        return Response.ok(user).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, User user) {
        if (controller.isUserNameNotEmpty(user)) {
            return Response.ok("User was not found").type(MediaType.APPLICATION_JSON_TYPE).build();
        }

        User userEntity = controller.update(id, user);
        return Response.ok(userEntity).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        User userEntity = User.findById(id);
        
        if (userEntity == null) {
            throw new WebApplicationException("User with id " + id 
                + " does not exist.", Response.Status.NOT_FOUND);
        }

        userEntity.delete();
        return Response.status(204).build();
    }
}