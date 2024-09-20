package br.com.softports.core.internal.tarefa_aud.usecase;

import br.com.softports.core.api.tarefa_aud.dto.CustomRevisionEntityResponse;
import br.com.softports.core.api.tarefa_aud.dto.TarefaAudResponse;
import br.com.softports.core.api.tarefa_aud.usecase.CustomRevisionEntityToCustomRevisionEntityResponse;
import br.com.softports.core.api.tarefa_aud.usecase.TarefaAudToTarefaAudResponse;
import br.com.softports.core.internal.common.entity.audited.CustomRevisionEntity;
import br.com.softports.core.internal.common.entity.audited.TarefaAud;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomRevisionEntityToCustomRevisionEntityResponseDefault implements
        CustomRevisionEntityToCustomRevisionEntityResponse {

    @Override
    public CustomRevisionEntityResponse executar(CustomRevisionEntity customRevisionEntity) {
        return CustomRevisionEntityResponse.builder()
                .id(customRevisionEntity.getId())
                .revtstmp(customRevisionEntity.getRevtstmp())
                .keycloakId(customRevisionEntity.getKeycloakId())
                .build();
    }
}
