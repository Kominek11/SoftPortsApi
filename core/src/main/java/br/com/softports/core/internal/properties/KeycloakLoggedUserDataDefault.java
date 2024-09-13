package br.com.softports.core.internal.properties;

import br.com.softports.core.api.properties.KeycloakLoggedUserData;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakLoggedUserDataDefault implements KeycloakLoggedUserData {

    @Override
    public String getCurrentUserId() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getSubject();
    }
}
