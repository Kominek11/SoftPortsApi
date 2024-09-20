package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.BuscarTarefas;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.comentario.expression.ComentarioExpressions;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
public class BuscarTarefasDefault implements BuscarTarefas {

    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarefaToTarefaResponse tarefaToTarefaResponse;

    @Override
    public Pagina<TarefaResponse> executar(Integer tamanhoPagina, Integer numeroPagina,
                                           String ordenadoPor, String direcao,
                                           Long projetoId, Boolean fechada,
                                           LocalDate dataInicio, LocalDate dataFim,
                                           String titulo, Set<Long> usuarios,
                                           List<Long> prioridades,
                                           List<Long> classificacao,
                                           List<Long> subClassificacao
                                           ) {
        Set<Usuario> usuariosSet = new HashSet<>();
        if (usuarios != null) {
            usuarios.forEach(item -> {
                BooleanBuilder filtro = new BooleanBuilder(UsuarioExpressions.id(item));
                Usuario usuario = usuarioRepository.buscar(filtro).orElseThrow();
                usuariosSet.add(usuario);
            });
        }
        BooleanBuilder filtro = new BooleanBuilder()
                .and(ProjetoExpressions.id(projetoId))
                .and(TarefaExpressions.fechada(fechada))
                .and(TarefaExpressions.entre(dataInicio, dataFim))
                .and(TarefaExpressions.titulo(titulo))
                .and(TarefaExpressions.usuarios(usuariosSet.isEmpty() ? null : usuariosSet))
                .and(TarefaExpressions.prioridade(prioridades))
                .and(TarefaExpressions.classificacao(classificacao))
                .and(TarefaExpressions.subClassificacao(subClassificacao));
            List<TarefaResponse> tarefas = tarefaRepository
                    .buscarTodos(filtro,
                            tamanhoPagina,
                            numeroPagina,
                            ordenadoPor,
                            direcao)
                    .stream()
                    .map(tarefaToTarefaResponse::executar)
                    .toList();
        return paginar(tamanhoPagina, numeroPagina, tarefas, filtro);
    }

    @Override
    public TarefaResponse executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtro).orElseThrow();
        return tarefaToTarefaResponse.executar(tarefa);
    }

    private Pagina<TarefaResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                                List<TarefaResponse> tarefas, BooleanBuilder filtro) {
        Long tarefaQuantidade = tarefaRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) tarefaQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas, tamanhoPagina, tarefaQuantidade, tarefas);
    }

}