package br.com.softports.core.internal.usuario.usecase;

import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.enumerate.UsuarioRole;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.api.usuario.usecase.*;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class DeletarUsuarioDefault implements DeletarUsuario {

    private final DeletarUsuarioKeycloak deletarUsuarioKeycloak;
    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;

    @Override
    @Transactional
    public void executar(UUID usuarioId) {
        BooleanBuilder filtroUsuario = new BooleanBuilder()
                .and(UsuarioExpressions.keycloakId(usuarioId));
        Usuario usuario = usuarioRepository.buscar(filtroUsuario).orElseThrow();
        deletarUsuarioKeycloak.executar(usuarioId);
        usuario.getTarefas().size();
        usuario.getSolicitacoes().size();
        usuario.getProjetos().size();
        usuario.getComentarios().size();
        usuarioRepository.apagar(usuario);
    }
}