package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.AtualizarStatusTarefa;
import br.com.softports.core.api.tarefa.usecase.IncluirComentarioTarefa;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.comentario.expression.ComentarioExpressions;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
public class IncluirComentarioTarefaDefault implements IncluirComentarioTarefa {

    private final TarefaRepository tarefaRepository;
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public TarefaResponse executar(Long id, String conteudo, Long usuarioId) {
        BooleanBuilder filtroTarefa = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtroTarefa).orElseThrow();
        BooleanBuilder filtroUsuario = new BooleanBuilder().and(UsuarioExpressions.id(usuarioId));
        Usuario usuario = usuarioRepository.buscar(filtroUsuario).orElseThrow();
        Comentario comentario = new Comentario();
        comentario.setConteudo(conteudo);
        comentario.setTarefa(tarefa);
        comentario.setDataCriacao(LocalDateTime.now());
        comentario.setUsuario(usuario);
        comentarioRepository.salvar(comentario);
        return gerarTarefaResponse(tarefa);
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
                .dataFechamento(tarefa.getDataFechamento())
                .dataCriacao(tarefa.getDataCriacao())
                .status(tarefa.getStatus())
                .projeto(gerarProjetoResponse(tarefa.getProjeto()))
                .usuarios(gerarUsuarioResponse(tarefa.getUsuarios()))
                .comentarios(gerarComentarioResponseList(comentarios))
                .classificacao(gerarClassificacaoResponse(tarefa.getClassificacao()))
                .prioridade(tarefa.getPrioridade())
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
                        item.getKeycloakId(),
                        item.getRoles() == null ? new ArrayList<>() :List.of(item.getRoles().split(","))
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
                    item.getConteudo(),
                    item.getDataCriacao(),
                    item.getUsuario().getNome()
            ));
        });
        return comentarioResponseList;
    }

    private ClassificacaoResponse gerarClassificacaoResponse(Classificacao classificacao) {
        return new ClassificacaoResponse(
                classificacao.getId(),
                gerarSubClassificacaoResponse(classificacao.getSubClassificacao()).id()
        );
    }

    private SubClassificacaoResponse gerarSubClassificacaoResponse(SubClassificacao subClassificacao) {
        return new SubClassificacaoResponse(
                subClassificacao.getId(),
                subClassificacao.getNome()
        );
    }

}