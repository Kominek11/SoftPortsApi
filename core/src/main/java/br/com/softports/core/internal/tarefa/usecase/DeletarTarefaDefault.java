package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.DeletarProjeto;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.DeletarTarefa;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletarTarefaDefault implements DeletarTarefa {

    private final TarefaRepository tarefaRepository;

    @Override
    public void executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtro).orElseThrow();
        tarefaRepository.apagar(tarefa);
    }
}