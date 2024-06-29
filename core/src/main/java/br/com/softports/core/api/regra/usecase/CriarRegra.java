package br.com.softports.core.api.regra.usecase;

import br.com.softports.core.api.regra.dto.RegraRequest;

import java.util.List;

public interface CriarRegra {

    RegraRequest executar(String nome,
                          Long inconsistenciaTipoId,
                          String emailNotificacao,
                          Long usuarioId);
    }
