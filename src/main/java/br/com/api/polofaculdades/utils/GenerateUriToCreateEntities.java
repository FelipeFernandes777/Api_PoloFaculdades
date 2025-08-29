package br.com.api.polofaculdades.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class GenerateUriToCreateEntities {
    public URI execute(String entityId){
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(entityId)
                .toUri();
    }
}
