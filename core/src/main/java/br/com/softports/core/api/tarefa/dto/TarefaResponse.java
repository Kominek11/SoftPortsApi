package br.com.softports.core.api.tarefa.dto;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.internal.common.entity.Classificacao;
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
        byte[][] screenshots,
        String caminho,
        Date dataFechamento,
        Date dataCriacao,
        Date dataEstimada,
        Long status,
        Boolean fechada,
        Long posicao,
        Long prioridade,
        ProjetoResponse projeto,
        String feedback,
        Set<UsuarioResponse> usuarios,
        List<ComentarioResponse> comentarios,
        Set<ClassificacaoResponse> classificacoes
) {}
