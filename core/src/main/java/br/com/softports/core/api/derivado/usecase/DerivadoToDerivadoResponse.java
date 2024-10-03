package br.com.softports.core.api.derivado.usecase;

import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.internal.common.entity.Derivado;

public interface DerivadoToDerivadoResponse {

    DerivadoResponse executar(Derivado derivado);
}
