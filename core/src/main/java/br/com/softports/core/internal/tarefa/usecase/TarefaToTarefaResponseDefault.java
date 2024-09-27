package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.usecase.ClassificacaoToClassificacaoResponse;
import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.api.comentario.usecase.ComentarioToComentarioResponse;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.projeto.usecase.ProjetoToProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
import br.com.softports.core.api.tarefa_aud.dto.TarefaAudResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.usecase.UsuarioToUsuarioResponse;
import br.com.softports.core.internal.comentario.expression.ComentarioExpressions;
import br.com.softports.core.internal.common.entity.Tarefa;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TarefaToTarefaResponseDefault implements TarefaToTarefaResponse {

    private final ProjetoToProjetoResponse projetoToProjetoResponse;
    private final UsuarioToUsuarioResponse usuarioToUsuarioResponse;
    private final ClassificacaoToClassificacaoResponse classificacaoToClassificacaoResponse;
    private final ComentarioRepository comentarioRepository;
    private final ComentarioToComentarioResponse comentarioToComentarioResponse;

    @Override
    public TarefaResponse executar(Tarefa tarefa) {
        BooleanBuilder filtro = new BooleanBuilder().and(ComentarioExpressions.tarefaId(tarefa.getId()));
        List<ComentarioResponse> comentarios = comentarioRepository
                .buscarTodos(filtro, 10, 1)
                .stream().map(comentarioToComentarioResponse::executar).toList();
        return TarefaResponse.builder()
                .id(tarefa.getId())
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .so(tarefa.getSo())
                .screenshots(tarefa.getScreenshots())
                .caminho(tarefa.getCaminho())
                .dataFechamento(tarefa.getDataFechamento())
                .dataCriacao(tarefa.getDataCriacao())
                .dataEstimada(tarefa.getDataEstimada())
                .fechada(tarefa.getFechada())
                .status(tarefa.getStatus())
                .projeto(projetoToProjetoResponse.executar(tarefa.getProjeto()))
                .feedback(tarefa.getFeedback())
                .usuarios(tarefa.getUsuarios().stream()
                    .map(usuarioToUsuarioResponse::executar)
                    .collect(Collectors.toSet()))
                .classificacao(classificacaoToClassificacaoResponse
                        .executar(tarefa.getClassificacao()))
                .prioridade(tarefa.getPrioridade())
                .comentarios(paginar(10, 1 , comentarios, filtro))
                .build();
    }

    private Pagina<ComentarioResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                              List<ComentarioResponse> comentarios, BooleanBuilder filtro) {
        Long comentarioQuantidade = comentarioRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) comentarioQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas,
                tamanhoPagina, comentarioQuantidade, comentarios);
    }
}
