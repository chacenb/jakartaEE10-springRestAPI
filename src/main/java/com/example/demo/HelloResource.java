package com.example.demo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {

    @Inject
    private HelloService hService;

    @GET
    @Produces("text/plain")
    public String hello() {
        hService.getAllEmployees();
        return "Hello, World!";
    }
}