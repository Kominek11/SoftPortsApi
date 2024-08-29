package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.classificacao.repository.ClassificacaoRepository;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.AtualizarProjeto;
import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.AtualizarTarefa;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.classificacao.expression.ClassificacaoExpressions;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class AtualizarTarefaDefault implements AtualizarTarefa {

    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClassificacaoRepository classificacaoRepository;

    @Override
    public TarefaResponse executar(Long id, String titulo,
                                   String descricao, String so,
                                   String caminho, Date dataFechamento,
                                   Date dataEstimada,
                                   Long status,
                                   Boolean fechada, Long posicao,
                                   Long projetoId, Long usuarioId,
                                   byte[][] screenshots, Set<Long> classificacoes,
                                   Long prioridade) {
        BooleanBuilder filtroTarefa = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtroTarefa).orElseThrow();
        BooleanBuilder filtroProjeto = new BooleanBuilder().and(ProjetoExpressions.id(projetoId));
        Projeto projeto = projetoRepository.buscar(filtroProjeto).orElseThrow();
        Set<Usuario> usuarios = new HashSet<>();
        BooleanBuilder filtroUsuario = new BooleanBuilder().and(UsuarioExpressions.id(usuarioId));
        Usuario usuario = usuarioRepository.buscar(filtroUsuario).orElseThrow();
        usuarios.add(usuario);
        Set<Classificacao> classificacoesSet = new HashSet<>();
        classificacoes.forEach(item -> {
            BooleanBuilder filtroClassificacao = new BooleanBuilder().and(ClassificacaoExpressions.id(item));
            Classificacao classificacao = classificacaoRepository.buscar(filtroClassificacao).orElseThrow();
            classificacoesSet.add(classificacao);
        });
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setSo(so);
        tarefa.setScreenshots(screenshots);
        tarefa.setCaminho(caminho);
        tarefa.setDataFechamento(dataFechamento);
        tarefa.setDataEstimada(dataEstimada);
        tarefa.setClassificacoes(classificacoesSet);
        tarefa.setStatus(status);
        tarefa.setFechada(fechada);
        tarefa.setPosicao(posicao);
        tarefa.setProjeto(projeto);
        tarefa.setUsuarios(usuarios);
        tarefa.setPrioridade(prioridade);
        tarefaRepository.salvar(tarefa);
        return gerarTarefaResponse(tarefa);
    }

    @Override
    public TarefaResponse executar(Long id, String titulo,
                                   String descricao, String so,
                                   String caminho, Date dataFechamento,
                                   Date dataEstimada,
                                   Long status,
                                   Boolean fechada, Long posicao,
                                   Long projetoId, List<Long> usuarioIds,
                                   byte[][] screenshots, Set<Long> classificacoes,
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
        Set<Classificacao> classificacoesSet = new HashSet<>();
        classificacoes.forEach(item -> {
            BooleanBuilder filtroClassificacao = new BooleanBuilder().and(ClassificacaoExpressions.id(item));
            Classificacao classificacao = classificacaoRepository.buscar(filtroClassificacao).orElseThrow();
            classificacoesSet.add(classificacao);
        });
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setSo(so);
        tarefa.setScreenshots(screenshots);
        tarefa.setCaminho(caminho);
        tarefa.setDataFechamento(dataFechamento);
        tarefa.setDataEstimada(dataEstimada);
        tarefa.setClassificacoes(classificacoesSet);
        tarefa.setStatus(status);
        tarefa.setFechada(fechada);
        tarefa.setPosicao(posicao);
        tarefa.setProjeto(projeto);
        tarefa.setUsuarios(usuarios);
        tarefa.setPrioridade(prioridade);
        tarefaRepository.salvar(tarefa);
        return gerarTarefaResponse(tarefa);
    }

    private TarefaResponse gerarTarefaResponse(Tarefa tarefa) {
        return TarefaResponse.builder()
                .id(tarefa.getId())
                .descricao(tarefa.getDescricao())
                .so(tarefa.getSo())
                .screenshots(tarefa.getScreenshots())
                .caminho(tarefa.getCaminho())
                .dataFechamento(tarefa.getDataFechamento())
                .dataCriacao(tarefa.getDataCriacao())
                .dataEstimada(tarefa.getDataEstimada())
                .status(tarefa.getStatus())
                .fechada(tarefa.getFechada())
                .posicao(tarefa.getPosicao())
                .projeto(gerarProjetoResponse(tarefa.getProjeto()))
                .usuarios(gerarUsuarioResponse(tarefa.getUsuarios()))
                .classificacoes(gerarClassificacaoResponseList(tarefa.getClassificacoes()))
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

    private Set<ClassificacaoResponse> gerarClassificacaoResponseList(Set<Classificacao> classificacoes) {
        Set<ClassificacaoResponse> classificacaoResponseList = new HashSet<>();
        classificacoes.forEach(item -> {
            classificacaoResponseList.add(new ClassificacaoResponse(
                    item.getId(),
                    item.getNome(),
                    gerarSubClassificacaoResponse(item.getSubClassificacao())
            ));
        });
        return classificacaoResponseList;
    }

    private SubClassificacaoResponse gerarSubClassificacaoResponse(SubClassificacao subClassificacao) {
        return new SubClassificacaoResponse(
                subClassificacao.getId(),
                subClassificacao.getNome()
        );
    }
}