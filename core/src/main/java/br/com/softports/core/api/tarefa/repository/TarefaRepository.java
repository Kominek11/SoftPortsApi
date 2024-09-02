package br.com.softports.core.api.tarefa.repository;

import br.com.softports.core.api.common.repository.Repositorio;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.Tarefa;

import java.util.Optional;

public interface TarefaRepository extends Repositorio<Tarefa> {

    Optional<Long> findMaxPosicao(Long status);
}
