package br.com.softports.application.resources.dto.derivado;

public record CriarDerivadoRequest(
        String nome,
        Long projetoId
) { }
