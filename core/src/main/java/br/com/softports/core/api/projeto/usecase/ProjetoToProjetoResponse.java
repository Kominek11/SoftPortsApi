package br.com.softports.core.api.projeto.usecase;

import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.internal.common.entity.Projeto;

public interface ProjetoToProjetoResponse {

    ProjetoResponse executar(Projeto projeto);

}
