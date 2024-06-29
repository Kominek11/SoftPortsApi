package br.com.softports.core.internal.regra.usecase;

import br.com.softports.core.api.regra.dto.AtualizarSituacaoRegraResponse;
import br.com.softports.core.api.regra.repository.RegraRepository;
import br.com.softports.core.api.regra.usecase.AtualizarSituacaoRegra;
import br.com.softports.core.internal.common.entity.Regra;
import br.com.softports.core.internal.common.enumeration.RegraSituacao;
import br.com.softports.core.internal.common.exception.RecursoNaoEncontradoException;
import br.com.softports.core.internal.regra.expression.RegraExpressions;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AtualizarSituacaoRegraDefault implements AtualizarSituacaoRegra {

    private final RegraRepository regraRepository;

    @Override
    public AtualizarSituacaoRegraResponse executar(Long id, String situacao) {
        Regra regra = regraRepository.buscar(
                        RegraExpressions.id(id))
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Regra"));
        atualizarRegra(regra, situacao);
        regraRepository.salvar(regra);
        return criarAtualizarSituacaoRegraResponse(regra);
    }

    private AtualizarSituacaoRegraResponse criarAtualizarSituacaoRegraResponse(Regra regra) {
        return AtualizarSituacaoRegraResponse.builder()
                .id(regra.getId())
                .situacao(regra.getSituacao().toString())
                .build();
    }

    private void atualizarRegra(Regra regra, String situacao) {
        regra.setSituacao(RegraSituacao.valueOf(situacao.toUpperCase()));
        if (situacao.equals("INATIVA")) {
            regra.setDataInativacao(LocalDateTime.now());
        }
    }
}