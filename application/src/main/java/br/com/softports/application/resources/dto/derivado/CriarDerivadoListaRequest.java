package br.com.softports.application.resources.dto.derivado;

import br.com.softports.core.api.derivado.dto.DerivadoListaResponse;

import java.util.List;

public record CriarDerivadoListaRequest(
        List<DerivadoListaResponse> derivadoResponses
) { }
