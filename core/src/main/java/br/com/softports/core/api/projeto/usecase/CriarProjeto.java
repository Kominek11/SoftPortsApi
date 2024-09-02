package br.com.softports.core.api.projeto.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;

import java.util.List;

public interface CriarProjeto {

    ProjetoResponse executar(String nome, Long organizacaoId, List<Long> usuarios);
}
