package br.com.softports.core.api.tarefa_aud.dto;

import lombok.Builder;
import java.util.UUID;

@Builder
public record CustomRevisionEntityResponse(
        int id,
        Long revtstmp,
        UUID keycloakId
) {}