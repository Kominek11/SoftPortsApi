package br.com.softports.application.resources.dto;

import java.util.List;

public record CriarRegraRequest(
        String nome,
        Long inconsistenciaTipoId,
        String emailNotificacao,
        Long usuarioId
) { }
