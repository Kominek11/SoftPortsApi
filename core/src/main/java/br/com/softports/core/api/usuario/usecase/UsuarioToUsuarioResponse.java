package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.internal.common.entity.Usuario;

public interface UsuarioToUsuarioResponse {

    UsuarioResponse executar(Usuario usuario);

}
