package br.com.softports.core.internal.usuario.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.BuscarOrganizacoes;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.api.usuario.usecase.BuscarUsuarios;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BuscarUsuariosDefault implements BuscarUsuarios {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Pagina<UsuarioResponse> executar(Integer tamanhoPagina, Integer numeroPagina,
                                            String ordenadoPor, String direcao,
                                            Long projetoId, String nomeUsuario) {
        BooleanBuilder filtro = new BooleanBuilder()
                .and(UsuarioExpressions.projetoId(projetoId))
                .and(UsuarioExpressions.nome(nomeUsuario));
            List<UsuarioResponse> usuarios = usuarioRepository
                    .buscarTodos(filtro,
                            tamanhoPagina,
                            numeroPagina,
                            ordenadoPor,
                            direcao)
                    .stream()
                    .map(this::gerarUsuarioResponse)
                    .toList();
        return paginar(tamanhoPagina, numeroPagina, usuarios, filtro);
    }

    @Override
    public UsuarioResponse executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(UsuarioExpressions.id(id));
        Usuario usuario = usuarioRepository.buscar(filtro).orElseThrow();
        return gerarUsuarioResponse(usuario);
    }

    private Pagina<UsuarioResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                                List<UsuarioResponse> usuarios, BooleanBuilder filtro) {
        Long usuarioQuantidade = usuarioRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) usuarioQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas, tamanhoPagina, usuarioQuantidade, usuarios);
    }

    private UsuarioResponse gerarUsuarioResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getKeycloakId(),
                usuario.getRoles() == null ? new ArrayList<>() :List.of(usuario.getRoles().split(","))
        );
    }
}