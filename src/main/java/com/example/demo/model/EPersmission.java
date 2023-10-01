package com.example.demo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EPersmission {

    ARTIST_READ("artist:read"),
    ARTIST_UPDATE("artist:update"),
    ARTIST_CREATE("artist:create"),
    ARTIST_DELETE("artist:delete"),
    CLIENT_READ("client:read"),
    CLIENT_UPDATE("client:update"),
    CLIENT_CREATE("client:create"),
    CLIENT_DELETE("client:delete");


    private final String permisison;


}
