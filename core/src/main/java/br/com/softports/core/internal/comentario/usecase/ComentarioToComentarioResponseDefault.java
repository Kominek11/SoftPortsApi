package br.com.softports.core.internal.comentario.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.classificacao.usecase.ClassificacaoToClassificacaoResponse;
import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.comentario.usecase.ComentarioToComentarioResponse;
import br.com.softports.core.api.subclassificacao.usecase.SubClassificacaoToSubClassificacaoResponse;
import br.com.softports.core.internal.common.entity.Classificacao;
import br.com.softports.core.internal.common.entity.Comentario;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComentarioToComentarioResponseDefault implements ComentarioToComentarioResponse {

    @Override
    public ComentarioResponse executar(Comentario comentario) {
        return new ComentarioResponse(
                comentario.getId(),
                comentario.getConteudo(),
                comentario.getDataCriacao(),
                comentario.getUsuario().getNome()
        );
    }
}
