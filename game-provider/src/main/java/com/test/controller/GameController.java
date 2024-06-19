package com.test.controller;

import com.test.dto.Response;
import com.test.entity.Game;
import com.test.service.ProviderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/game")
@Slf4j
public class GameController {

    @Inject
    ProviderService providerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/init")
    public Response init(@RestQuery String token, @RestQuery long provider, @RestQuery long game, @RestQuery String signature) {
       log.debug("init token={}", token);

       return providerService.init(token, provider, game, signature);
    }
}
