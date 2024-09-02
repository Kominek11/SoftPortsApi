package br.com.softports.application.resources.dto.projeto;

import java.util.List;

public record AtualizarProjetoRequest(
        Long id,
        String nome,
        Long organizacaoId,
        List<Long> usuarios
) { }
