package br.com.softports.core.api.tarefa.dto;

import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import lombok.Builder;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
public record TarefaResponse(
        Long id,
        String descricao,
        String so,
        String screenshots,
        String caminho,
        Date dataCorrecao,
        Date dataCriacao,
        Long prioridade,
        Long classificacao,
        Long status,
        Boolean fechada,
        Long posicao,
        ProjetoResponse projeto,
        Set<UsuarioResponse> usuarios,
        List<ComentarioResponse> comentarios
) {}
