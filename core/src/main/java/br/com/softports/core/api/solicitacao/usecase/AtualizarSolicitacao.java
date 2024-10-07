package br.com.softports.core.api.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;

import java.util.Date;
import java.util.List;

public interface AtualizarSolicitacao {

    SolicitacaoResponse executar(Long id, String titulo,
                                 String descricao, String so,
                                 String caminho,
                                 Date dataEstimada,
                                 Long status,
                                 Boolean fechada, Long posicao,
                                 Long projetoId, Long usuarioId,
                                 byte[][] screenshots, Long classificacaoId,
                                 Long subclassificacaoId,
                                 Long prioridade);

    SolicitacaoResponse executar(Long id, String titulo,
                            String descricao, String so,
                            String caminho,
                            Date dataEstimada,
                            Long status,
                            Boolean fechada, Long posicao,
                            Long projetoId, List<Long> usuarioIds,
                            byte[][] screenshots, Long classificacaoId,
                            Long subclassificacaoId,
                            Long prioridade);
}
