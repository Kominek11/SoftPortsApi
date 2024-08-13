package br.com.softports.application.resources.dto.projeto;

public record AtualizarProjetoRequest(
        Long id,
        String nome,
        Long organizacaoId
) { }
