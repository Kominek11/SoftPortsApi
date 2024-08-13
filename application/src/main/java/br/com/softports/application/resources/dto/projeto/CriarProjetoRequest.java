package br.com.softports.application.resources.dto.projeto;

public record CriarProjetoRequest(
        String nome,
        Long organizacaoId
) { }
