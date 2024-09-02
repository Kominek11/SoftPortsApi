package br.com.softports.core.internal.usuario.usecase;

import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.usecase.UsuarioToUsuarioResponse;
import br.com.softports.core.internal.common.entity.Usuario;

public class UsuarioToUsuarioResponseDefault implements UsuarioToUsuarioResponse {

    @Override
    public UsuarioResponse executar(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .keycloakId(usuario.getKeycloakId())
                .build();
    }

}
