package com.demojpaapp.resource;

import com.demojpaapp.entity.Employee;
import com.demojpaapp.common.Response;
import com.demojpaapp.service.HelloService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/")
public class HelloResource {

    private static final Logger LOG = LogManager.getLogger(HelloResource.class);

    @Inject
    private HelloService hService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response root() {
        LOG.info("api resource > /");

        return new Response(hService.getAllEmployees());
    }

    @POST
    @Path("/createEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(@Valid Employee employee) throws Exception {
        LOG.info("api resource > createEmployee");
        Employee emp = null;

        try {
            emp = hService.createEmployee(employee);
        } catch (Exception e) {
            LOG.error("api resource > createEmployee");
            throw new Exception(e);
        }

        return (emp == null) ? new Response(null, "Erreur lors de la création de l'employé") : new Response(emp);
    }
}