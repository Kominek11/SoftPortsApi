package br.com.softports.core.api.dashboard.dto;

import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record DashboardResponse(
        DadoDashboardResponse dados,
        List<DadoDashboardPorProjetoResponse> dadosPorProjeto,
        List<DadoDashboardPorAnoResponse> dadosPorAno
) {}
