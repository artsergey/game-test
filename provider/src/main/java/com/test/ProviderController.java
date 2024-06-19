package com.test;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/gs")
public class ProviderController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/init")
    public Response init(@RestQuery String token) {
        return new Response((token != null && token.startsWith("1")) ? 1 : 0);
    }
}
