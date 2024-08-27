package br.com.softports.core.api.tarefa.dto;

import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import lombok.Builder;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
public record TarefaResponse(
        Long id,
        String titulo,
        String descricao,
        String so,
        Blob screenshots,
        String caminho,
        Date dataFechamento,
        Date dataCriacao,
        Date dataEstimada,
        Long prioridade,
        Long classificacao,
        Long status,
        Boolean fechada,
        Long posicao,
        ProjetoResponse projeto,
        String feedback,
        Set<UsuarioResponse> usuarios,
        List<ComentarioResponse> comentarios
) {}
