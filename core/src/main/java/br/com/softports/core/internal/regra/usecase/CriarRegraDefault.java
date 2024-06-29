package br.com.softports.core.internal.regra.usecase;

import br.com.softports.core.api.regra.dto.RegraRequest;
import br.com.softports.core.api.regra.repository.RegraRepository;
import br.com.softports.core.api.regra.usecase.CriarRegra;
import br.com.softports.core.internal.common.enumeration.RegraSituacao;
import br.com.softports.core.internal.common.entity.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CriarRegraDefault implements CriarRegra {


    private final RegraRepository regraRepository;

    @Override
    public RegraRequest executar(String nome,
                                 Long inconsistenciaTipoId,
                                 String emailNotificacao,
                                 Long usuarioId) {
        Regra regra = new Regra();
        regra.setNome(nome);
        regra.setEmailNotificacao(emailNotificacao);
        regra.setSituacao(RegraSituacao.ATIVA);
        regra.setDataInclusao(LocalDateTime.now());
        Regra novaRegra = regraRepository.salvar(regra);
        return RegraRequest.builder()
                .nome(novaRegra.getNome())
                .situacao(novaRegra.getSituacao().toString())
                .dataInclusao(novaRegra.getDataInclusao())
                .dataInativacao(novaRegra.getDataInativacao())
                .build();
    }
}