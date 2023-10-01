package com.example.demo.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@RequiredArgsConstructor
public enum ERole {
    USER(Set.of(
            EPersmission.CLIENT_READ,
            EPersmission.CLIENT_UPDATE,
            EPersmission.CLIENT_DELETE,
            EPersmission.CLIENT_CREATE

    )),
    ARTIST(Set.of(
            EPersmission.ARTIST_READ,
            EPersmission.ARTIST_UPDATE,
            EPersmission.ARTIST_DELETE,
            EPersmission.ARTIST_CREATE,
            EPersmission.CLIENT_READ,
            EPersmission.CLIENT_UPDATE,
            EPersmission.CLIENT_DELETE,
            EPersmission.CLIENT_CREATE

    )),
    MOD(Collections.EMPTY_SET),
    QA_DE(Collections.EMPTY_SET);

    private final Set<EPersmission> persmissions;

    public List<SimpleGrantedAuthority> grantedAuthorities() {

        var authorities = getPersmissions()
                .stream()
                .map(ePersmission -> new SimpleGrantedAuthority(ePersmission.getPermisison()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
