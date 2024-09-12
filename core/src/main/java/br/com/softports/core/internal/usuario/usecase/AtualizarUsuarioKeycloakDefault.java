package br.com.softports.core.internal.usuario.usecase;


import br.com.softports.core.api.common.service.HttpService;
import br.com.softports.core.api.properties.KeycloakProperties;
import br.com.softports.core.api.usuario.dto.*;
import br.com.softports.core.api.usuario.usecase.AtualizarUsuarioKeycloak;
import br.com.softports.core.api.usuario.usecase.ObterToken;
import br.com.softports.core.internal.common.dto.HttpRequest;
import br.com.softports.core.internal.common.exception.GenericException;
import br.com.softports.core.internal.common.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class AtualizarUsuarioKeycloakDefault implements AtualizarUsuarioKeycloak {

    private final KeycloakProperties keycloakProperties;
    private final ObterToken obterToken;
    private final HttpService httpService;

    @Override
    public AtualizarUsuarioKeycloakResponse executar(UUID id, String nome, String email, String username) {
        String bearerToken = obterToken.executar();
        String urlAtualizarUsuario = keycloakProperties.urlEndPointUsers() + "/" + id;
        AtualizarUsuarioRequest payload = gerarRequest(nome, email, username);
        try {
            HttpRequest<AtualizarUsuarioRequest, AtualizarUsuarioKeycloakResponse> request
                    = new HttpRequest<>(urlAtualizarUsuario, payload, bearerToken);
            return httpService.put(request, AtualizarUsuarioKeycloakResponse.class).getResponse();

        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    private AtualizarUsuarioRequest gerarRequest(String nome, String email, String username) {
        return AtualizarUsuarioRequest.builder()
                .firstName(nome)
                .email(email)
                .username(username)
                .build();
    }
}