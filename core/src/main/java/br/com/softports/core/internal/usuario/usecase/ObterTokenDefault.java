package br.com.softports.core.internal.usuario.usecase;

import br.com.softports.core.api.common.service.HttpService;
import br.com.softports.core.api.properties.KeycloakProperties;
import br.com.softports.core.api.usuario.dto.AutenticacaoTokenResponse;
import br.com.softports.core.api.usuario.usecase.ObterToken;
import br.com.softports.core.internal.common.dto.HttpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
public class ObterTokenDefault implements ObterToken {

    private final KeycloakProperties keycloakProperties;
    private final HttpService httpService;
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";

    @Override
    public String executar() {
        return gerarToken();
    }

    private String gerarToken() {

        String grantType = keycloakProperties.grantType();
        String clientId = keycloakProperties.clientId();
        String clientSecret = keycloakProperties.clientSecret();
        String url = keycloakProperties.urlTokenClientSecret();

        Map<String, String> payload = gerarRequest(grantType, clientId, clientSecret);

        try {
            HttpRequest<Map<String, String>, AutenticacaoTokenResponse> request
                    = new HttpRequest<>(url, payload);
            request =  httpService.postUrlEncoded(request, AutenticacaoTokenResponse.class);
            AutenticacaoTokenResponse response = request.getResponse();
            return Objects.requireNonNull(response.accessToken(), "Token vazio");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token no serviço de autenticação");
        }
    }

    private Map<String, String> gerarRequest(String grantType, String clientId, String clientSecret) {
        Map<String, String> mapRequest = new HashMap<>();
        mapRequest.put(GRANT_TYPE, grantType);
        mapRequest.put(CLIENT_ID, clientId);
        mapRequest.put(CLIENT_SECRET, clientSecret);

        return mapRequest;
    }
}
