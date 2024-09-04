package br.com.softports.core.internal.usuario.usecase;


import br.com.softports.core.api.common.service.HttpService;
import br.com.softports.core.api.properties.KeycloakProperties;
import br.com.softports.core.api.usuario.dto.CredencialUsuarioKeycloak;
import br.com.softports.core.api.usuario.dto.CriarUsuarioKeycloakRequest;
import br.com.softports.core.api.usuario.dto.CriarUsuarioKeycloakResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.api.usuario.usecase.CriarUsuarioKeycloak;
import br.com.softports.core.api.usuario.usecase.DeletarUsuarioKeycloak;
import br.com.softports.core.api.usuario.usecase.ObterToken;
import br.com.softports.core.internal.common.dto.HttpRequest;
import br.com.softports.core.internal.common.exception.GenericException;
import br.com.softports.core.internal.common.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class DeletarUsuarioKeycloakDefault implements DeletarUsuarioKeycloak {

    private final KeycloakProperties keycloakProperties;
    private final ObterToken obterToken;
    private final HttpService httpService;

    @Override
    public void executar(UUID usuarioId) {
        String bearerToken = obterToken.executar();
        String url = keycloakProperties.urlEndPointUsers() + "/" + usuarioId;
        HttpRequest<Void, Void> request = new HttpRequest<>(url, null, bearerToken);
        try {
            httpService.delete(request, Void.class);
        } catch (Exception e) {
            throw new GenericException("Erro ao excluir usuário no Keycloak: " + e.getMessage());
        }
    }
}