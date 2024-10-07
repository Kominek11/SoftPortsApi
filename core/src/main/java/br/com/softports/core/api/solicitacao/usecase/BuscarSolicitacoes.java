package br.com.softports.core.api.solicitacao.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BuscarSolicitacoes {

    Pagina<SolicitacaoResponse> executar(Integer tamanhoPagina,
                                         Integer numeroPagina,
                                         String ordenadoPor,
                                         String direcao,
                                         Long projetoId,
                                         Boolean fechada,
                                         LocalDate dataInicio,
                                         LocalDate dataFim,
                                         String titulo,
                                         Set<Long> usuarios,
                                         List<Long> prioridades,
                                         List<Long> classificacao,
                                         List<Long> subClassificacao);

    SolicitacaoResponse executar(Long id);
}
