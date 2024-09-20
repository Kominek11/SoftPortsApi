package br.com.softports.core.internal.projeto.usecase;

import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.CriarOrganizacao;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.CriarProjeto;
import br.com.softports.core.api.projeto.usecase.ProjetoToProjetoResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.organizacao.expression.OrganizacaoExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class CriarProjetoDefault implements CriarProjeto {

    private final ProjetoRepository projetoRepository;
    private final OrganizacaoRepository organizacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProjetoToProjetoResponse projetoToProjetoResponse;

    @Override
    public ProjetoResponse executar(String nome, Long organizacaoId, List<Long> usuarios) {
        BooleanBuilder filtro = new BooleanBuilder().and(OrganizacaoExpressions.id(organizacaoId));
        Organizacao organizacao = organizacaoRepository.buscar(filtro).orElseThrow();
        Set<Usuario> usuariosSet = new HashSet<>();
        usuarios.forEach(item -> {
            BooleanBuilder filtroUsuario = new BooleanBuilder().and(UsuarioExpressions.id(item));
            Usuario usuario = usuarioRepository.buscar(filtroUsuario).orElseThrow();
            usuariosSet.add(usuario);
        });
        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        projeto.setOrganizacao(organizacao);
        projeto.setUsuarios(usuariosSet);
        projetoRepository.salvar(projeto);
        return projetoToProjetoResponse.executar(projeto);
    }
}