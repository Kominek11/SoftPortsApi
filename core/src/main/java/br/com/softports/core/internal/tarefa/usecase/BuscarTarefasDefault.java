package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.BuscarProjetos;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.BuscarTarefas;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.organizacao.expression.OrganizacaoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class BuscarTarefasDefault implements BuscarTarefas {

    private final TarefaRepository tarefaRepository;

    @Override
    public Pagina<TarefaResponse> executar(Integer tamanhoPagina, Integer numeroPagina,
                                           String ordenadoPor, String direcao) {
            List<TarefaResponse> tarefas = tarefaRepository
                    .buscarTodos(null,
                            tamanhoPagina,
                            numeroPagina,
                            ordenadoPor,
                            direcao)
                    .stream()
                    .map(this::gerarTarefaResponse)
                    .toList();
        return paginar(tamanhoPagina, numeroPagina, tarefas, null);
    }

    @Override
    public TarefaResponse executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(TarefaExpressions.id(id));
        Tarefa tarefa = tarefaRepository.buscar(filtro).orElseThrow();
        return gerarTarefaResponse(tarefa);
    }

    private Pagina<TarefaResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                                List<TarefaResponse> tarefas, BooleanBuilder filtro) {
        Long tarefaQuantidade = tarefaRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) tarefaQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas, tamanhoPagina, tarefaQuantidade, tarefas);
    }

    private TarefaResponse gerarTarefaResponse(Tarefa tarefa) {
        return TarefaResponse.builder()
                .id(tarefa.getId())
                .descricao(tarefa.getDescricao())
                .so(tarefa.getSo())
                .caminho(tarefa.getCaminho())
                .dataCorrecao(tarefa.getDataCorrecao())
                .dataCriacao(tarefa.getDataCriacao())
                .prioridade(tarefa.getPrioridade())
                .classificacao(tarefa.getClassificacao())
                .status(tarefa.getStatus())
                .projeto(gerarProjetoResponse(tarefa.getProjeto()))
                .usuarios(gerarUsuarioResponse(tarefa.getUsuarios()))
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
}