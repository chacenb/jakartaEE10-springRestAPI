package com.demojpaapp.resource.ifaces;

import com.demojpaapp.entity.Employee;
import com.demojpaapp.utils.Response;
import jakarta.validation.Valid;


public interface IHelloResource {

    public Response root();

    public Response createEmployee(@Valid Employee employee) throws Exception;
}
