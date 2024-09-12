package br.com.softports.core.internal.usuario.usecase;


import br.com.softports.core.api.common.service.HttpService;
import br.com.softports.core.api.properties.KeycloakProperties;
import br.com.softports.core.api.usuario.dto.CredencialUsuarioKeycloak;
import br.com.softports.core.api.usuario.dto.CriarUsuarioKeycloakRequest;
import br.com.softports.core.api.usuario.dto.CriarUsuarioKeycloakResponse;
import br.com.softports.core.api.usuario.dto.KeycloakRoleResponse;
import br.com.softports.core.api.usuario.usecase.AtribuirRoleUsuarioKeycloak;
import br.com.softports.core.api.usuario.usecase.CriarUsuarioKeycloak;
import br.com.softports.core.api.usuario.usecase.ObterToken;
import br.com.softports.core.internal.common.dto.HttpRequest;
import br.com.softports.core.internal.common.exception.GenericException;
import br.com.softports.core.internal.common.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CriarUsuarioKeycloakDefault implements CriarUsuarioKeycloak {

    private final KeycloakProperties keycloakProperties;
    private final AtribuirRoleUsuarioKeycloak atribuirRoleUsuarioKeycloak;
    private final ObterToken obterToken;
    private final HttpService httpService;

    @Override
    public UUID executar(String nome, String sobrenome, String email,
                         Boolean emailVerified, String username, List<KeycloakRoleResponse> realmRoles) {
        String bearerToken = obterToken.executar();
        String urlCriarUsuario = keycloakProperties.urlEndPointUsers();
        CriarUsuarioKeycloakRequest payload = gerarRequest(nome, sobrenome, email,
                emailVerified, username);
        UUID usuarioId;
        try {
            HttpRequest<CriarUsuarioKeycloakRequest, CriarUsuarioKeycloakResponse> request
                    = new HttpRequest<>(urlCriarUsuario, payload, bearerToken);
            try {
                 usuarioId = ObjectUtils.getUUIDFromLocationHeader(httpService.post(request,
                        CriarUsuarioKeycloakResponse.class)
                        .getLocation());
            } catch (Exception e) {
                throw new GenericException(e.getMessage());
            }
            atribuirRoleUsuarioKeycloak.executar(usuarioId, realmRoles);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
        return usuarioId;
    }

    private CriarUsuarioKeycloakRequest gerarRequest(String nome, String sobrenome,
                                                     String email, Boolean emailVerified,
                                                     String username) {
        return CriarUsuarioKeycloakRequest.builder()
                .nome(nome)
                .sobrenome(sobrenome)
                .email(email)
                .emailVerified(emailVerified)
                .enabled(Boolean.TRUE.toString())
                .username(username)
                .credentials(List.of(
                        new CredencialUsuarioKeycloak("password",
                                true,
                                keycloakProperties.defaultPassword())))
                .build();
    }
}