package br.com.softports.core.api.comentario.usecase;

import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.internal.common.entity.Comentario;

public interface ComentarioToComentarioResponse {

    ComentarioResponse executar(Comentario comentario);

}
