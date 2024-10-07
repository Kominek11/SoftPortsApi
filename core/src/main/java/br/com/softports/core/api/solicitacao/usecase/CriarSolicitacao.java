package br.com.softports.core.api.solicitacao.usecase;

import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;

import java.util.Date;
import java.util.List;

public interface CriarSolicitacao {

    SolicitacaoResponse executar(String titulo, String descricao,
                                 String so, String caminho, Date dataEstimada,
                                 Long status, Long projetoId,
                                 Long usuarioId, byte[][] screenshots,
                                 Long classificacaoId,
                                 Long subclassificacaoId,
                                 Long prioridade);

    SolicitacaoResponse executar(String titulo, String descricao,
                            String so, String caminho, Date dataEstimada,
                            Long status, Long projetoId,
                            List<Long> usuarioIds, byte[][] screenshots,
                            Long classificacaoId,
                            Long subclassificacaoId,
                            Long prioridade);
}
