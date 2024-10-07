package br.com.softports.core.internal.solicitacao.usecase;

import br.com.softports.core.api.classificacao.repository.ClassificacaoRepository;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;
import br.com.softports.core.api.solicitacao.repository.SolicitacaoRepository;
import br.com.softports.core.api.solicitacao.usecase.CriarSolicitacao;
import br.com.softports.core.api.solicitacao.usecase.SolicitacaoToSolicitacaoResponse;
import br.com.softports.core.api.subclassificacao.repository.SubClassificacaoRepository;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.classificacao.expression.ClassificacaoExpressions;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.subclassificacao.expression.SubClassificacaoExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class CriarSolicitacaoDefault implements CriarSolicitacao {

    private final SolicitacaoRepository solicitacaoRepository;
    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClassificacaoRepository classificacaoRepository;
    private final SubClassificacaoRepository subClassificacaoRepository;
    private final SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse;

    @Override
    public SolicitacaoResponse executar(String titulo, String descricao,
                                        String so, String caminho, Date dataEstimada,
                                        Long status, Long projetoId,
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
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setTitulo(titulo);
        solicitacao.setDescricao(descricao);
        solicitacao.setSo(so);
        solicitacao.setScreenshots(screenshots);
        solicitacao.setCaminho(caminho);
        solicitacao.setDataEstimada(dataEstimada);
        solicitacao.setDataCriacao(new Date());
        solicitacao.setClassificacao(classificacao);
        solicitacao.setStatus(status);
        solicitacao.setFechada(false);
        solicitacao.setPosicao(gerarPosicaoSolicitacao(solicitacao.getStatus()));
        solicitacao.setProjeto(projeto);
        solicitacao.setUsuarios(usuarios);
        solicitacao.setPrioridade(prioridade);
        solicitacaoRepository.salvar(solicitacao);
        return solicitacaoToSolicitacaoResponse.executar(solicitacao);
    }

    @Override
    public SolicitacaoResponse executar(String titulo, String descricao,
                                   String so, String caminho, Date dataEstimada,
                                   Long status,
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
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setTitulo(titulo);
        solicitacao.setDescricao(descricao);
        solicitacao.setSo(so);
        solicitacao.setScreenshots(screenshots);
        solicitacao.setCaminho(caminho);
        solicitacao.setDataEstimada(dataEstimada);
        solicitacao.setDataCriacao(new Date());
        solicitacao.setClassificacao(classificacao);
        solicitacao.setStatus(status);
        solicitacao.setFechada(false);
        solicitacao.setPosicao(gerarPosicaoSolicitacao(solicitacao.getStatus()));
        solicitacao.setProjeto(projeto);
        solicitacao.setUsuarios(usuarios);
        solicitacao.setPrioridade(prioridade);
        solicitacaoRepository.salvar(solicitacao);
        return solicitacaoToSolicitacaoResponse.executar(solicitacao);
    }

    private Long gerarPosicaoSolicitacao(Long status) {
        return solicitacaoRepository.findMaxPosicao(status).orElse(0L) + 1;
    }
}