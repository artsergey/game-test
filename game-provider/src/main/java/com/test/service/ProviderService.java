package com.test.service;

import com.test.dto.Response;
import com.test.dto.Status;
import com.test.entity.Game;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.URI;

@ApplicationScoped
public class ProviderService {

    @Inject
    private EntityManager em;


    public Response init(String token, long provider, long game, String signature){

        if(!validateSignature(signature)){
            return new Response(Status.SIGNATURE_INVALID.getCode());
        }

        Game g =  findGameByIdAndProviderId(provider, game);

        if(g == null){
            return new Response(Status.GAME_NOT_AUTHIRIZED.getCode());
        }

        return  checkSession(token, g.getProvider().getCallback()) ?
                new Response(Status.OK.getCode()) : new Response(Status.SESSION_NOT_FOUND.getCode());
    }


    private boolean checkSession(String token, String url){

        URI uri = URI.create(url);

        ProviderCallback callback =
                RestClientBuilder.newBuilder().baseUri(uri).build(ProviderCallback.class);

       return callback.init(token).getStatus() == Status.OK.getCode();

    }

    private boolean validateSignature(String signature){
        //TODO check md5 or any other depending on client contract
        return true;
    }

    private Game findGameByIdAndProviderId(long providerId, long gameId){
        try {
            return (Game) em.createQuery("SELECT g FROM Game g WHERE g.gameId = ?1 and g.provider.id = ?2")
                    .setParameter(1, gameId).setParameter(2, providerId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

}
