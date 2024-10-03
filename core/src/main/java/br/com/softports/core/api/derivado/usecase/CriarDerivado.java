package br.com.softports.core.api.derivado.usecase;

import br.com.softports.core.api.derivado.dto.DerivadoResponse;

public interface CriarDerivado {
    DerivadoResponse executar(String nome, Long projetoId);
}
