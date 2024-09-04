package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaPosicaoResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.AtualizarPosicoesTarefa;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class AtualizarPosicoesTarefaDefault implements AtualizarPosicoesTarefa {

    private final TarefaRepository tarefaRepository;

    @Override
    public List<TarefaPosicaoResponse> executar(List<TarefaPosicaoResponse> tarefas) {
        List<Tarefa> tarefasList = new ArrayList<>();
        tarefas.forEach(item -> {
            BooleanBuilder filtro = new BooleanBuilder()
                    .and(TarefaExpressions.id(item.id()));
            Tarefa tarefa = tarefaRepository.buscar(filtro).orElseThrow();
            tarefa.setStatus(item.status());
            tarefa.setPosicao(item.posicao());
            tarefasList.add(tarefa);
        });
        tarefaRepository.salvarTodos(tarefasList);
        return gerarTarefaPosicaoResponse(tarefasList);
    }

    private List<TarefaPosicaoResponse> gerarTarefaPosicaoResponse(List<Tarefa> tarefas) {
        List<TarefaPosicaoResponse> tarefaPosicaoResponseList = new ArrayList<>();
        tarefas.forEach(item -> {
            tarefaPosicaoResponseList.add(
                    new TarefaPosicaoResponse(item.getId(), item.getStatus(), item.getPosicao()));
        });
        return tarefaPosicaoResponseList;
    }

    private ProjetoResponse gerarProjetoResponse(Projeto projeto) {
        return new ProjetoResponse(
                projeto.getId(),
                projeto.getNome(),
                gerarOrganizacaoResponse(projeto.getOrganizacao())
        );
    }

    private Set<UsuarioResponse> gerarUsuarioResponse(Set<Usuario> usuarios) {
        Set<UsuarioResponse> usuarioResponseSet = new HashSet<>();
        usuarios.forEach(item -> usuarioResponseSet.add(
                new UsuarioResponse(
                        item.getId(),
                        item.getNome(),
                        item.getEmail(),
                        item.getKeycloakId()
                )
        ));
        return usuarioResponseSet;
    }

    private OrganizacaoResponse gerarOrganizacaoResponse(Organizacao organizacao) {
        return new OrganizacaoResponse(
                organizacao.getId(),
                organizacao.getNome()
        );
    }

    private ClassificacaoResponse gerarClassificacaoResponse(Classificacao classificacao) {
        return new ClassificacaoResponse(
                classificacao.getId(),
                gerarSubClassificacaoResponse(classificacao.getSubClassificacao()).id()
        );
    }

    private SubClassificacaoResponse gerarSubClassificacaoResponse(SubClassificacao subClassificacao) {
        return new SubClassificacaoResponse(
                subClassificacao.getId(),
                subClassificacao.getNome()
        );
    }
}