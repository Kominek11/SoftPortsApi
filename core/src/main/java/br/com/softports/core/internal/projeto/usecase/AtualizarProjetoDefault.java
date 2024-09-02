package br.com.softports.core.internal.projeto.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.AtualizarOrganizacao;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.AtualizarProjeto;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.organizacao.expression.OrganizacaoExpressions;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class AtualizarProjetoDefault implements AtualizarProjeto {

    private final ProjetoRepository projetoRepository;
    private final OrganizacaoRepository organizacaoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ProjetoResponse executar(Long id, String nome, Long organizacaoId, List<Long> usuarios) {
        BooleanBuilder filtroProjeto = new BooleanBuilder().and(ProjetoExpressions.id(id));
        Projeto projeto = projetoRepository.buscar(filtroProjeto).orElseThrow();
        BooleanBuilder filtroOrganizacao = new BooleanBuilder().and(OrganizacaoExpressions.id(organizacaoId));
        Organizacao organizacao = organizacaoRepository.buscar(filtroOrganizacao).orElseThrow();
        Set<Usuario> usuariosSet = new HashSet<>();
        usuarios.forEach(item -> {
            BooleanBuilder filtroUsuario = new BooleanBuilder().and(UsuarioExpressions.id(item));
            Usuario usuario = usuarioRepository.buscar(filtroUsuario).orElseThrow();
            usuariosSet.add(usuario);
        });
        projeto.setNome(nome);
        projeto.setOrganizacao(organizacao);
        projeto.setUsuarios(usuariosSet);
        projetoRepository.salvar(projeto);
        return gerarProjetoResponse(projeto);
    }

    private ProjetoResponse gerarProjetoResponse(Projeto projeto) {
        return new ProjetoResponse(
                projeto.getId(),
                projeto.getNome(),
                gerarOrganizacaoResponse((projeto.getOrganizacao()))
        );
    }

    private OrganizacaoResponse gerarOrganizacaoResponse(Organizacao organizacao) {
        return new OrganizacaoResponse(
                organizacao.getId(),
                organizacao.getNome()
        );
    }
}