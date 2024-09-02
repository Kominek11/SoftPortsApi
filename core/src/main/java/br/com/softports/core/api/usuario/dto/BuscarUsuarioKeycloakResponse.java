package br.com.softports.core.api.usuario.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuscarUsuarioKeycloakResponse(
        @JsonProperty("id")
        String uuidExistente
) {
}
