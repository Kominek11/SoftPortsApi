package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.usecase.ClassificacaoToClassificacaoResponse;
import br.com.softports.core.api.projeto.usecase.ProjetoToProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.usecase.UsuarioToUsuarioResponse;
import br.com.softports.core.internal.common.entity.Tarefa;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TarefaToTarefaResponseDefault implements TarefaToTarefaResponse {

    private final ProjetoToProjetoResponse projetoToProjetoResponse;
    private final UsuarioToUsuarioResponse usuarioToUsuarioResponse;
    private final ClassificacaoToClassificacaoResponse classificacaoToClassificacaoResponse;

    @Override
    public TarefaResponse executar(Tarefa tarefa) {
        return TarefaResponse.builder()
                .id(tarefa.getId())
                .descricao(tarefa.getDescricao())
                .so(tarefa.getSo())
                .screenshots(tarefa.getScreenshots())
                .caminho(tarefa.getCaminho())
                .dataFechamento(tarefa.getDataFechamento())
                .dataCriacao(tarefa.getDataCriacao())
                .fechada(tarefa.getFechada())
                .status(tarefa.getStatus())
                .projeto(projetoToProjetoResponse.executar(tarefa.getProjeto()))
                .usuarios(tarefa.getUsuarios().stream()
                    .map(usuarioToUsuarioResponse::executar)
                    .collect(Collectors.toSet()))
                .classificacao(classificacaoToClassificacaoResponse
                        .executar(tarefa.getClassificacao()))
                .prioridade(tarefa.getPrioridade())
                .build();
    }
}
