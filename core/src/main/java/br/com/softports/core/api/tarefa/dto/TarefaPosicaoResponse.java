package br.com.softports.core.api.tarefa.dto;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
public record TarefaPosicaoResponse(
        Long id,
        Long status,
        Long posicao
) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long status() {
        return status;
    }

    @Override
    public Long posicao() {
        return posicao;
    }
}
