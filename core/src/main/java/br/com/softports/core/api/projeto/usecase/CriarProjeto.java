package br.com.softports.core.api.projeto.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;

public interface CriarProjeto {

    ProjetoResponse executar(String nome, Long organizacaoId);
}
