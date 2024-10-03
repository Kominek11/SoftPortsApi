package br.com.softports.core.internal.derivado.usecase;

import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.api.derivado.usecase.DerivadoToDerivadoResponse;
import br.com.softports.core.api.projeto.usecase.ProjetoToProjetoResponse;
import br.com.softports.core.internal.common.entity.Derivado;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DerivadoToDerivadoResponseDefault implements DerivadoToDerivadoResponse {

    private final ProjetoToProjetoResponse projetoToProjetoResponse;

    @Override
    public DerivadoResponse executar(Derivado derivado) {
        return new DerivadoResponse(
                derivado.getId(),
                derivado.getNome(),
                projetoToProjetoResponse.executar(derivado.getProjeto())
        );
    }
}
