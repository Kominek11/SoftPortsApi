package br.com.softports.core.api.derivado.usecase;

import br.com.softports.core.api.derivado.dto.DerivadoListaResponse;
import br.com.softports.core.api.derivado.dto.DerivadoResponse;

import java.util.List;

public interface CriarDerivadoLista {
    List<DerivadoResponse> executar(List<DerivadoListaResponse> derivadoListResponseLista);
}
