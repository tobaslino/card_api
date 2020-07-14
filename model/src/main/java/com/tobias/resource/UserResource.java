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
import com.tobias.entity.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private Controller controller;

    @GET
    public List<Usuario> findAll() {
        return Usuario.listAll();
    }

    @POST
    @Transactional
    public Response create(Usuario usuario) {
        PanacheEntityBase.persist(usuario);
        return Response.ok(usuario).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, Usuario Usuario) {
        if (controller.isUserNameNotEmpty(Usuario)) {
            return Response.ok("Usuario was not found").type(MediaType.APPLICATION_JSON_TYPE).build();
        }

        Usuario UsuarioEntity = controller.update(id, Usuario);
        return Response.ok(UsuarioEntity).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Usuario UsuarioEntity = Usuario.findById(id);
        
        if (UsuarioEntity == null) {
            throw new WebApplicationException("Usuario with id " + id 
                + " does not exist.", Response.Status.NOT_FOUND);
        }

        UsuarioEntity.delete();
        return Response.status(204).build();
    }
}