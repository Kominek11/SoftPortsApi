package br.com.softports.core.internal.usuario.usecase;


import br.com.softports.core.api.common.service.HttpService;
import br.com.softports.core.api.properties.KeycloakProperties;
import br.com.softports.core.api.usuario.dto.*;
import br.com.softports.core.api.usuario.usecase.AtribuirRoleUsuarioKeycloak;
import br.com.softports.core.api.usuario.usecase.CriarUsuarioKeycloak;
import br.com.softports.core.api.usuario.usecase.ObterToken;
import br.com.softports.core.internal.common.dto.HttpRequest;
import br.com.softports.core.internal.common.exception.GenericException;
import br.com.softports.core.internal.common.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class AtribuirRoleUsuarioKeycloakDefault implements AtribuirRoleUsuarioKeycloak {

    private final KeycloakProperties keycloakProperties;
    private final ObterToken obterToken;
    private final HttpService httpService;

    @Override
    public void executar(UUID usuarioId, List<KeycloakRoleResponse> roles) {
        String bearerToken = obterToken.executar();
        String urlAtribuirRoleUsuario = keycloakProperties.urlEndpointAddUserRole()
                .replaceAll("usuarioIdKeycloak", usuarioId.toString());
        List<KeycloakRoleRequest> payload = gerarRequest(roles);
        try {
            HttpRequest<List<KeycloakRoleRequest>, KeycloakRoleResponse> request
                    = new HttpRequest<>(urlAtribuirRoleUsuario, payload, bearerToken);
            httpService.post(request, KeycloakRoleResponse.class);
        } catch (Exception e) {
            System.err.println("Error during HTTP request: " + e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    private List<KeycloakRoleRequest> gerarRequest(List<KeycloakRoleResponse> roles) {
        List<KeycloakRoleRequest> keycloakRoleRequestList = new ArrayList<>();
        roles.forEach(role -> {
            keycloakRoleRequestList.add(new KeycloakRoleRequest(role.id(), role.name()));
        });
        return keycloakRoleRequestList;
    }
}