package br.com.softports.core.internal.usuario.usecase;

import br.com.softports.core.api.usuario.dto.KeycloakRoleResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.usecase.UsuarioToUsuarioResponse;
import br.com.softports.core.internal.common.entity.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioToUsuarioResponseDefault implements UsuarioToUsuarioResponse {

    @Override
    public UsuarioResponse executar(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .keycloakId(usuario.getKeycloakId())
                .roles(usuario.getRoles() == null ?
                        new ArrayList<>() :
                        List.of(usuario.getRoles().split(",")))
                .foto(usuario.getFoto())
                .build();
    }
}
