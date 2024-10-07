package br.com.softports.core.internal.solicitacao.usecase;

import br.com.softports.core.api.classificacao.usecase.ClassificacaoToClassificacaoResponse;
import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.api.comentario.usecase.ComentarioToComentarioResponse;
import br.com.softports.core.api.projeto.usecase.ProjetoToProjetoResponse;
import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;
import br.com.softports.core.api.solicitacao.usecase.SolicitacaoToSolicitacaoResponse;
import br.com.softports.core.api.usuario.usecase.UsuarioToUsuarioResponse;
import br.com.softports.core.internal.common.entity.Solicitacao;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SolicitacaoToSolicitacaoResponseDefault implements SolicitacaoToSolicitacaoResponse {

    private final ProjetoToProjetoResponse projetoToProjetoResponse;
    private final UsuarioToUsuarioResponse usuarioToUsuarioResponse;
    private final ClassificacaoToClassificacaoResponse classificacaoToClassificacaoResponse;

    @Override
    public SolicitacaoResponse executar(Solicitacao solicitacao) {
        return SolicitacaoResponse.builder()
                .id(solicitacao.getId())
                .titulo(solicitacao.getTitulo())
                .descricao(solicitacao.getDescricao())
                .so(solicitacao.getSo())
                .screenshots(solicitacao.getScreenshots())
                .caminho(solicitacao.getCaminho())
                .dataFechamento(solicitacao.getDataFechamento())
                .dataCriacao(solicitacao.getDataCriacao())
                .dataEstimada(solicitacao.getDataEstimada())
                .fechada(solicitacao.getFechada())
                .status(solicitacao.getStatus())
                .projeto(projetoToProjetoResponse.executar(solicitacao.getProjeto()))
                .feedback(solicitacao.getFeedback())
                .usuarios(solicitacao.getUsuarios().stream()
                    .map(usuarioToUsuarioResponse::executar)
                    .collect(Collectors.toSet()))
                .classificacao(classificacaoToClassificacaoResponse
                        .executar(solicitacao.getClassificacao()))
                .prioridade(solicitacao.getPrioridade())
                .build();
    }
}
