package br.com.softports.core.internal.usuario.usecase;


import br.com.softports.core.api.common.service.HttpService;
import br.com.softports.core.api.properties.KeycloakProperties;
import br.com.softports.core.api.usuario.dto.KeycloakAttributeRequest;
import br.com.softports.core.api.usuario.dto.KeycloakAttributeResponse;
import br.com.softports.core.api.usuario.dto.KeycloakRoleRequest;
import br.com.softports.core.api.usuario.dto.KeycloakRoleResponse;
import br.com.softports.core.api.usuario.usecase.AtribuirAttributesUsuarioKeycloak;
import br.com.softports.core.api.usuario.usecase.ObterToken;
import br.com.softports.core.internal.common.dto.HttpRequest;
import br.com.softports.core.internal.common.exception.GenericException;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class AtribuirAttributesUsuarioKeycloakDefault implements AtribuirAttributesUsuarioKeycloak {

    private final KeycloakProperties keycloakProperties;
    private final ObterToken obterToken;
    private final HttpService httpService;

    @Override
    public void executar(UUID usuarioId, String username,
                         String nome, String sobrenome,
                         String email, Map<String, Object> attributes) {
        String bearerToken = obterToken.executar();
        String urlAtribuirAttributeUsuario = keycloakProperties.urlEndPointUsers() + "/" + usuarioId;
        KeycloakAttributeRequest payload = gerarRequest(username, nome,
                sobrenome, email, attributes);
        try {
            HttpRequest<KeycloakAttributeRequest, KeycloakAttributeResponse> request
                    = new HttpRequest<>(urlAtribuirAttributeUsuario, payload, bearerToken);
            httpService.put(request, KeycloakAttributeResponse.class);
        } catch (Exception e) {
            System.err.println("Error during HTTP request: " + e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    private KeycloakAttributeRequest gerarRequest(String username, String nome,
                                                  String sobrenome, String email,
                                                  Map<String, Object> attributes) {
        return KeycloakAttributeRequest.builder()
                .username(username)
                .firstName(nome)
                .lastName(sobrenome)
                .email(email)
                .attributes(attributes)
                .build();
    }
}