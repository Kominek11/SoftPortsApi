package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.BuscarProjetos;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.BuscarTarefas;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.internal.comentario.expression.ComentarioExpressions;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.organizacao.expression.OrganizacaoExpressions;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
public class BuscarTarefasDefault implements BuscarTarefas {

    private final TarefaRepository tarefaRepository;
    private final ComentarioRepository comentarioRepository;

    @Override
    public Pagina<TarefaResponse> executar(Integer tamanhoPagina, Integer numeroPagina,
                                           String ordenadoPor, String direcao,
                                           Long projetoId, Boolean fechada,
                                           LocalDate dataCriacao, LocalDate dataCorrecao) {
        BooleanBuilder filtro = new BooleanBuilder()
                .and(ProjetoExpressions.id(projetoId))
                .and(TarefaExpressions.fechada(fechada))
                .and(TarefaExpressions.entre(dataCriacao, dataCorrecao));
            List<TarefaResponse> tarefas = tarefaRepository
                    .buscarTodos(filtro,
                            tamanhoPagina,
                            numeroPagina,
                            ordenadoPor,
                            direcao)
                    .stream()
                    .map(this::gerarTarefaResponse)
                    .toList();
        return paginar(tamanhoPagina, numeroPagina, tarefas, filtro);
    }

    @Override
    public TarefaResponse executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtro).orElseThrow();
        return gerarTarefaResponse(tarefa);
    }

    private Pagina<TarefaResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                                List<TarefaResponse> tarefas, BooleanBuilder filtro) {
        Long tarefaQuantidade = tarefaRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) tarefaQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas, tamanhoPagina, tarefaQuantidade, tarefas);
    }

    private TarefaResponse gerarTarefaResponse(Tarefa tarefa) {
        BooleanBuilder filtroComentario = new BooleanBuilder().and(ComentarioExpressions.tarefaId(tarefa.getId()));
        List<Comentario> comentarios = comentarioRepository.buscarTodos(filtroComentario);
        return TarefaResponse.builder()
                .id(tarefa.getId())
                .descricao(tarefa.getDescricao())
                .so(tarefa.getSo())
                .screenshots(tarefa.getScreenshots())
                .caminho(tarefa.getCaminho())
                .dataCorrecao(tarefa.getDataCorrecao())
                .dataCriacao(tarefa.getDataCriacao())
                .prioridade(tarefa.getPrioridade())
                .classificacao(tarefa.getClassificacao())
                .status(tarefa.getStatus())
                .fechada(tarefa.getFechada())
                .posicao(tarefa.getPosicao())
                .projeto(gerarProjetoResponse(tarefa.getProjeto()))
                .usuarios(gerarUsuarioResponse(tarefa.getUsuarios()))
                .comentarios(gerarComentarioResponseList(comentarios))
                .build();
    }

    private ProjetoResponse gerarProjetoResponse(Projeto projeto) {
        return new ProjetoResponse(
                projeto.getId(),
                projeto.getNome(),
                gerarOrganizacaoResponse(projeto.getOrganizacao())
        );
    }

    private Set<UsuarioResponse> gerarUsuarioResponse(Set<Usuario> usuarios) {
        Set<UsuarioResponse> usuarioResponseSet = new HashSet<>();
        usuarios.forEach(item -> usuarioResponseSet.add(
                new UsuarioResponse(
                        item.getId(),
                        item.getNome(),
                        item.getEmail(),
                        item.getKeycloakId()
                )
        ));
        return usuarioResponseSet;
    }

    private OrganizacaoResponse gerarOrganizacaoResponse(Organizacao organizacao) {
        return new OrganizacaoResponse(
                organizacao.getId(),
                organizacao.getNome()
        );
    }

    private List<ComentarioResponse> gerarComentarioResponseList(List<Comentario> comentarios) {
        List<ComentarioResponse> comentarioResponseList = new ArrayList<>();
        comentarios.forEach(item -> {
            comentarioResponseList.add(new ComentarioResponse(
                    item.getId(),
                    item.getConteudo()
            ));
        });
        return comentarioResponseList;
    }
}