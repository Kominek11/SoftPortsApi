package br.com.softports.core.api.dashboard.dto;

import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Builder
public record DadoDashboardResponse(
        Long valorIncidente,
        Long valorProblema,
        Long valorMudanca
) {}
