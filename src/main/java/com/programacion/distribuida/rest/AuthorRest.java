package com.programacion.distribuida.rest;

import com.programacion.distribuida.db.Authors;
import com.programacion.distribuida.servicios.AuthorRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRest {
    @Operation(summary = "Busca un autor por su id")
    @GET
    @Path("/{id}")
    public Authors findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @Inject AuthorRepository repository;

    @Operation(summary = "Busca todos los autores")
    @GET
    public List<Authors> findAll() {
        return repository
                .findAll()
                .list();
    }

    @Operation(summary = "Ingresa un nuevo autor")
    @POST
    public void insert(Authors obj) {
        repository.persist(obj);
    }
    @Operation(summary = "Actualiza un autor por su id")
    @PUT
    @Path("/{id}")
    public void update(Authors obj, @PathParam("id") Long id) {

        var author = repository.findById(id);

        author.setFirtName(obj.getFirtName());
        author.setLastName(obj.getLastName());
    }

    @Operation(summary = "Elimina un autor por su id")
    @DELETE
    @Path("/{id}")
    public void delete( @PathParam("id") Long id ) {
        repository.deleteById(id);
    }
}
