package br.com.softports.core.api.solicitacao.dto;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import lombok.Builder;

import java.util.Date;
import java.util.Set;

@Builder
public record SolicitacaoResponse(
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
        ClassificacaoResponse classificacao
) {}
