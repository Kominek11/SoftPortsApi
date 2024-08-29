package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.prioridade.dto.PrioridadeResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.AtualizarStatusTarefa;
import br.com.softports.core.api.tarefa.usecase.AtualizarTarefa;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class AtualizarStatusTarefaDefault implements AtualizarStatusTarefa {

    private final TarefaRepository tarefaRepository;

    @Override
    public TarefaResponse executar(Long id, Long status) {
        BooleanBuilder filtroTarefa = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtroTarefa).orElseThrow();
        tarefa.setStatus(status);
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
                .status(tarefa.getStatus())
                .projeto(gerarProjetoResponse(tarefa.getProjeto()))
                .usuarios(gerarUsuarioResponse(tarefa.getUsuarios()))
                .classificacoes(gerarClassificacaoResponseList(tarefa.getClassificacoes()))
                .prioridades(gerarPrioridadeResponseList(tarefa.getPrioridades()))
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

    private Set<ClassificacaoResponse> gerarClassificacaoResponseList(Set<Classificacao> classificacoes) {
        Set<ClassificacaoResponse> classificacaoResponseList = new HashSet<>();
        classificacoes.forEach(item -> {
            classificacaoResponseList.add(new ClassificacaoResponse(
                    item.getId(),
                    item.getNome()
            ));
        });
        return classificacaoResponseList;
    }

    private Set<PrioridadeResponse> gerarPrioridadeResponseList(Set<Prioridade> prioridades) {
        Set<PrioridadeResponse> prioridadeoResponseList = new HashSet<>();
        prioridades.forEach(item -> {
            prioridadeoResponseList.add(new PrioridadeResponse(
                    item.getId(),
                    item.getNome()
            ));
        });
        return prioridadeoResponseList;
    }
}