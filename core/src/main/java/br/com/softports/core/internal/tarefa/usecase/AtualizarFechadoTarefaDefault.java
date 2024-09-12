package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.AtualizarFechadoTarefa;
import br.com.softports.core.api.tarefa.usecase.AtualizarStatusTarefa;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class AtualizarFechadoTarefaDefault implements AtualizarFechadoTarefa {

    private final TarefaRepository tarefaRepository;

    @Override
    public TarefaResponse executar(Long id, Boolean fechado) {
        BooleanBuilder filtroTarefa = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtroTarefa).orElseThrow();
        tarefa.setFechada(fechado);
        tarefa.setDataFechamento(new Date());
        tarefaRepository.salvar(tarefa);
        return gerarTarefaResponse(tarefa);
    }

    private TarefaResponse gerarTarefaResponse(Tarefa tarefa) {
        return TarefaResponse.builder()
                .id(tarefa.getId())
                .descricao(tarefa.getDescricao())
                .so(tarefa.getSo())
                .screenshots(tarefa.getScreenshots())
                .caminho(tarefa.getCaminho())
                .dataFechamento(tarefa.getDataFechamento())
                .dataCriacao(tarefa.getDataCriacao())
                .fechada(tarefa.getFechada())
                .status(tarefa.getStatus())
                .projeto(gerarProjetoResponse(tarefa.getProjeto()))
                .usuarios(gerarUsuarioResponse(tarefa.getUsuarios()))
                .classificacao(gerarClassificacaoResponse(tarefa.getClassificacao()))
                .prioridade(tarefa.getPrioridade())
                .build();
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
                        item.getKeycloakId(),
                        item.getRoles() == null ? new ArrayList<>() :List.of(item.getRoles().split(","))
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