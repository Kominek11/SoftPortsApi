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
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
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
    private final TarefaToTarefaResponse tarefaToTarefaResponse;

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
        return tarefaToTarefaResponse.executar(tarefa);
    }
}