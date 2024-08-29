package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.classificacao.repository.ClassificacaoRepository;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.api.subclassificacao.repository.SubClassificacaoRepository;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.CriarTarefa;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.classificacao.expression.ClassificacaoExpressions;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.subclassificacao.expression.SubClassificacaoExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CriarTarefaDefault implements CriarTarefa {

    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClassificacaoRepository classificacaoRepository;
    private final SubClassificacaoRepository subClassificacaoRepository;

    @Override
    public TarefaResponse executar(String titulo, String descricao,
                                   String so, String caminho, Date dataEstimada,
                                   Long status,
                                   Long posicao, Long projetoId,
                                   Long usuarioId, byte[][] screenshots,
                                   Long classificacaoId,
                                   Long subclassificacaoId,
                                   Long prioridade) {
        BooleanBuilder filtroProjeto = new BooleanBuilder().and(ProjetoExpressions.id(projetoId));
        Projeto projeto = projetoRepository.buscar(filtroProjeto).orElseThrow();
        BooleanBuilder filtroUsuario = new BooleanBuilder().and(UsuarioExpressions.id(usuarioId));
        Usuario usuario = usuarioRepository.buscar(filtroUsuario).orElseThrow();
        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(usuario);
        BooleanBuilder filtroClassificacao = new BooleanBuilder().and(ClassificacaoExpressions.id(classificacaoId));
        Classificacao classificacao = classificacaoRepository.buscar(filtroClassificacao).orElseThrow();
        BooleanBuilder filtroSubClassificacao = new BooleanBuilder().and(SubClassificacaoExpressions.id(subclassificacaoId));
        SubClassificacao subClassificacao = subClassificacaoRepository.buscar(filtroSubClassificacao).orElseThrow();
        classificacao.setSubClassificacao(subClassificacao);
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setSo(so);
        tarefa.setScreenshots(screenshots);
        tarefa.setCaminho(caminho);
        tarefa.setDataEstimada(dataEstimada);
        tarefa.setDataCriacao(new Date());
        tarefa.setClassificacao(classificacao);
        tarefa.setStatus(status);
        tarefa.setFechada(false);
        tarefa.setPosicao(posicao);
        tarefa.setProjeto(projeto);
        tarefa.setUsuarios(usuarios);
        tarefa.setPrioridade(prioridade);
        tarefaRepository.salvar(tarefa);
        return gerarTarefaResponse(tarefa);
    }

    @Override
    public TarefaResponse executar(String titulo, String descricao,
                                   String so, String caminho, Date dataEstimada,
                                   Long status, Long posicao,
                                   Long projetoId, List<Long> usuarioIds, byte[][] screenshots,
                                   Long classificacaoId,
                                   Long subclassificacaoId,
                                   Long prioridade) {
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
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setSo(so);
        tarefa.setScreenshots(screenshots);
        tarefa.setCaminho(caminho);
        tarefa.setDataEstimada(dataEstimada);
        tarefa.setDataCriacao(new Date());
        tarefa.setClassificacao(classificacao);
        tarefa.setStatus(status);
        tarefa.setFechada(false);
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