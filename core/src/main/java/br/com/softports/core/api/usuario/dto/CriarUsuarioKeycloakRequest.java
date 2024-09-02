package br.com.softports.core.api.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record CriarUsuarioKeycloakRequest(
        @JsonProperty("firstName")
        String nome,
        @JsonProperty("lastName")
        String sobrenome,
        String email,

        String enabled,

        String username,

        List<CredencialUsuarioKeycloak> credentials
) {
}
