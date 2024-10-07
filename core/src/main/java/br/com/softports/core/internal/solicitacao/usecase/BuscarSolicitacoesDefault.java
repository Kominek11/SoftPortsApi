package br.com.softports.core.internal.solicitacao.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;
import br.com.softports.core.api.solicitacao.repository.SolicitacaoRepository;
import br.com.softports.core.api.solicitacao.usecase.BuscarSolicitacoes;
import br.com.softports.core.api.solicitacao.usecase.SolicitacaoToSolicitacaoResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.common.entity.Solicitacao;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.solicitacao.expression.SolicitacaoExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class BuscarSolicitacoesDefault implements BuscarSolicitacoes {

    private final SolicitacaoRepository solicitacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse;

    @Override
    public Pagina<SolicitacaoResponse> executar(Integer tamanhoPagina, Integer numeroPagina,
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
                .and(SolicitacaoExpressions.fechada(fechada))
                .and(SolicitacaoExpressions.entre(dataInicio, dataFim))
                .and(SolicitacaoExpressions.titulo(titulo))
                .and(SolicitacaoExpressions.usuarios(usuariosSet.isEmpty() ? null : usuariosSet))
                .and(SolicitacaoExpressions.prioridade(prioridades))
                .and(SolicitacaoExpressions.classificacao(classificacao))
                .and(SolicitacaoExpressions.subClassificacao(subClassificacao));
            List<SolicitacaoResponse> solicitacoes = solicitacaoRepository
                    .buscarTodos(filtro,
                            tamanhoPagina,
                            numeroPagina,
                            ordenadoPor,
                            direcao)
                    .stream()
                    .map(solicitacaoToSolicitacaoResponse::executar)
                    .toList();
        return paginar(tamanhoPagina, numeroPagina, solicitacoes, filtro);
    }

    @Override
    public SolicitacaoResponse executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(SolicitacaoExpressions.id(id));
        Solicitacao solicitacao = solicitacaoRepository.buscar(filtro).orElseThrow();
        return solicitacaoToSolicitacaoResponse.executar(solicitacao);
    }

    private Pagina<SolicitacaoResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                                List<SolicitacaoResponse> solicitacoes, BooleanBuilder filtro) {
        Long solicitacaoQuantidade = solicitacaoRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) solicitacaoQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas,
                            tamanhoPagina, solicitacaoQuantidade, solicitacoes);
    }

}