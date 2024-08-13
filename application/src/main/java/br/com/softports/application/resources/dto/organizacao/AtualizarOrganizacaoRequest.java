package br.com.softports.application.resources.dto.organizacao;

public record AtualizarOrganizacaoRequest(
        Long id,
        String nome
) { }
