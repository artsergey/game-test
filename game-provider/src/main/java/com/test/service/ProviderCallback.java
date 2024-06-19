package com.test.service;

import com.test.dto.Response;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

@RegisterRestClient(configKey="extensions-api")
@Path("gs")
public interface ProviderCallback {

    @Path("init")
    @GET
    Response init(@RestQuery String token);
}
