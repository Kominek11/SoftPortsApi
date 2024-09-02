package br.com.softports.core.api.usuario.dto;

import lombok.Builder;

@Builder
public record CredencialUsuarioKeycloak(
        String type,
        Boolean temporary,
        String value) {


}
