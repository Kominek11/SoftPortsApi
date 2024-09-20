package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.classificacao.repository.ClassificacaoRepository;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.AtualizarProjeto;
import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.api.subclassificacao.repository.SubClassificacaoRepository;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.AtualizarTarefa;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.classificacao.expression.ClassificacaoExpressions;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.subclassificacao.expression.SubClassificacaoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.sql.Blob;
import java.util.*;

@RequiredArgsConstructor
public class AtualizarTarefaDefault implements AtualizarTarefa {

    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClassificacaoRepository classificacaoRepository;
    private final SubClassificacaoRepository subClassificacaoRepository;
    private final TarefaToTarefaResponse tarefaToTarefaResponse;

    @Override
    public TarefaResponse executar(Long id, String titulo,
                                   String descricao, String so,
                                   String caminho, Date dataFechamento,
                                   Date dataEstimada,
                                   Long status,
                                   Boolean fechada, Long posicao,
                                   Long projetoId, Long usuarioId,
                                   byte[][] screenshots, Long classificacaoId,
                                   Long subclassificacaoId,
                                   Long prioridade) {
        BooleanBuilder filtroTarefa = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtroTarefa).orElseThrow();
        BooleanBuilder filtroProjeto = new BooleanBuilder().and(ProjetoExpressions.id(projetoId));
        Projeto projeto = projetoRepository.buscar(filtroProjeto).orElseThrow();
        Set<Usuario> usuarios = new HashSet<>();
        BooleanBuilder filtroUsuario = new BooleanBuilder().and(UsuarioExpressions.id(usuarioId));
        Usuario usuario = usuarioRepository.buscar(filtroUsuario).orElseThrow();
        usuarios.add(usuario);
        BooleanBuilder filtroClassificacao = new BooleanBuilder().and(ClassificacaoExpressions.id(classificacaoId));
        Classificacao classificacao = classificacaoRepository.buscar(filtroClassificacao).orElseThrow();
        BooleanBuilder filtroSubClassificacao = new BooleanBuilder().and(SubClassificacaoExpressions.id(subclassificacaoId));
        SubClassificacao subClassificacao = subClassificacaoRepository.buscar(filtroSubClassificacao).orElseThrow();
        classificacao.setSubClassificacao(subClassificacao);
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setSo(so);
        tarefa.setScreenshots(screenshots);
        tarefa.setCaminho(caminho);
        tarefa.setDataFechamento(dataFechamento);
        tarefa.setDataEstimada(dataEstimada);
        tarefa.setClassificacao(classificacao);
        tarefa.setStatus(status);
        tarefa.setFechada(fechada);
        tarefa.setPosicao(posicao);
        tarefa.setProjeto(projeto);
        tarefa.setUsuarios(usuarios);
        tarefa.setPrioridade(prioridade);
        tarefaRepository.salvar(tarefa);
        return tarefaToTarefaResponse.executar(tarefa);
    }

    @Override
    public TarefaResponse executar(Long id, String titulo,
                                   String descricao, String so,
                                   String caminho, Date dataFechamento,
                                   Date dataEstimada,
                                   Long status,
                                   Boolean fechada, Long posicao,
                                   Long projetoId, List<Long> usuarioIds,
                                   byte[][] screenshots, Long classificacaoId,
                                   Long subclassificacaoId,
                                   Long prioridade) {
        BooleanBuilder filtroTarefa = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtroTarefa).orElseThrow();
        BooleanBuilder filtroProjeto = new BooleanBuilder().and(ProjetoExpressions.id(projetoId));
        Projeto projeto = projetoRepository.buscar(filtroProjeto).orElseThrow();
        Set<Usuario> usuarios = new HashSet<>();
        usuarioIds.forEach(item -> {
            BooleanBuilder filtroUsuario = new BooleanBuilder().and(UsuarioExpressions.id(item));
            Usuario usuario = usuarioRepository.buscar(filtroUsuario).orElseThrow();
            usuarios.add(usuario);
        });
        BooleanBuilder filtroClassificacao = new BooleanBuilder().and(ClassificacaoExpressions.id(classificacaoId));
        Classificacao classificacao = classificacaoRepository.buscar(filtroClassificacao).orElseThrow();
        BooleanBuilder filtroSubClassificacao = new BooleanBuilder().and(SubClassificacaoExpressions.id(subclassificacaoId));
        SubClassificacao subClassificacao = subClassificacaoRepository.buscar(filtroSubClassificacao).orElseThrow();
        classificacao.setSubClassificacao(subClassificacao);
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setSo(so);
        tarefa.setScreenshots(screenshots);
        tarefa.setCaminho(caminho);
        tarefa.setDataFechamento(dataFechamento);
        tarefa.setDataEstimada(dataEstimada);
        tarefa.setClassificacao(classificacao);
        tarefa.setStatus(status);
        tarefa.setFechada(fechada);
        tarefa.setPosicao(posicao);
        tarefa.setProjeto(projeto);
        tarefa.setUsuarios(usuarios);
        tarefa.setPrioridade(prioridade);
        tarefaRepository.salvar(tarefa);
        return tarefaToTarefaResponse.executar(tarefa);
    }
}