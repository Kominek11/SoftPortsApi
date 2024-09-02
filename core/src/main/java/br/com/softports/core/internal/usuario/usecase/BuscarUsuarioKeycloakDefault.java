package br.com.softports.core.internal.usuario.usecase;

import br.com.softports.core.api.common.service.HttpService;
import br.com.softports.core.api.properties.KeycloakProperties;
import br.com.softports.core.api.usuario.usecase.BuscarUsuarioKeycloak;
import br.com.softports.core.api.usuario.dto.BuscarUsuarioKeycloakResponse;
import br.com.softports.core.api.usuario.usecase.ObterToken;
import br.com.softports.core.internal.common.dto.HttpRequest;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class BuscarUsuarioKeycloakDefault implements BuscarUsuarioKeycloak {

    private final KeycloakProperties keycloakProperties;
    private final ObterToken obterToken;
    private final HttpService httpService;

    @Override
    public Optional<UUID> executar(String email) {
        String bearerToken = obterToken.executar();
        String url = String.format(keycloakProperties.urlEndPointUsers()+"?email=%s", email);
        try {
            HttpRequest<Map<String, String>, BuscarUsuarioKeycloakResponse[]> request =
                    new HttpRequest<>(url, null, bearerToken);
            BuscarUsuarioKeycloakResponse[] response = httpService.get(request,
                    BuscarUsuarioKeycloakResponse[].class).getResponse();
            if(Arrays.asList(response).isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(UUID.fromString(
                    Arrays.stream(response)
                            .findFirst()
                            .get()
                            .uuidExistente()));
        } catch (Exception e) {
            throw e;
        }
    }
}
