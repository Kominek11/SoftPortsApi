package br.com.softports.core.internal.projeto.usecase;

import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.DeletarOrganizacao;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.DeletarProjeto;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeletarProjetoDefault implements DeletarProjeto {

    private final ProjetoRepository projetoRepository;
    private final TarefaRepository tarefaRepository;

    @Override
    public void executar(Long id) {
        BooleanBuilder filtroProjeto = new BooleanBuilder().and(ProjetoExpressions.id(id));
        Projeto projeto = projetoRepository.buscar(filtroProjeto).orElseThrow();
        BooleanBuilder filtroTarefas = new BooleanBuilder().and(TarefaExpressions.projetoId(id));
        List<Tarefa> tarefas = tarefaRepository.buscarTodos(filtroTarefas);
        tarefas.forEach(tarefaRepository::apagar);
        projetoRepository.apagar(projeto);
    }
}