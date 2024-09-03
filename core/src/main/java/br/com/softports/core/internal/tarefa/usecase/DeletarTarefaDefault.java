package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.DeletarProjeto;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.DeletarTarefa;
import br.com.softports.core.internal.comentario.expression.ComentarioExpressions;
import br.com.softports.core.internal.common.entity.Comentario;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeletarTarefaDefault implements DeletarTarefa {

    private final TarefaRepository tarefaRepository;
    private final ComentarioRepository comentarioRepository;

    @Override
    public void executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtro).orElseThrow();
        BooleanBuilder filtroComentarios = new BooleanBuilder().and(ComentarioExpressions.tarefaId(id));
        List<Comentario> comentarios = comentarioRepository.buscarTodos(filtroComentarios);
        comentarios.forEach(comentarioRepository::apagar);
        tarefaRepository.apagar(tarefa);
    }
}