package br.com.softports.core.api.tarefa_aud.usecase;

import br.com.softports.core.api.tarefa_aud.dto.CustomRevisionEntityResponse;
import br.com.softports.core.internal.common.entity.audited.CustomRevisionEntity;

public interface CustomRevisionEntityToCustomRevisionEntityResponse {

    CustomRevisionEntityResponse executar(CustomRevisionEntity customRevisionEntity);

}
