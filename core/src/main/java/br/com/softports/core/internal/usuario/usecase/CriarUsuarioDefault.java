package br.com.softports.core.internal.usuario.usecase;

import br.com.softports.core.api.usuario.dto.KeycloakRoleResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.enumerate.UsuarioRole;
import br.com.softports.core.api.usuario.usecase.BuscarUsuarioKeycloak;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.api.usuario.usecase.CriarUsuario;
import br.com.softports.core.api.usuario.usecase.CriarUsuarioKeycloak;
import br.com.softports.core.api.usuario.usecase.UsuarioToUsuarioResponse;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CriarUsuarioDefault implements CriarUsuario {

    private final CriarUsuarioKeycloak criarUsuarioKeycloak;
    private final BuscarUsuarioKeycloak buscarUsuarioKeycloak;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioToUsuarioResponse usuarioToUsuarioResponse;

    @Override
    public UsuarioResponse executar(String nome, String sobrenome, String email,
                                    Boolean emailVerified, String username, List<KeycloakRoleResponse> realmRoles,
                                    Map<String, Object> attributes, byte[] foto) {
        Optional<UUID> usuarioKeycloak = buscarUsuarioKeycloak.executar(email);
        if (usuarioKeycloak.isPresent())
            throw new RuntimeException("Usuário já existente");
        Usuario usuarioTemporario = criarUsuario(nome, email, realmRoles, foto);
        UUID uuidUsuarioKeycloak =  criarUsuarioKeycloak.executar(nome, sobrenome, email,
                emailVerified, username, realmRoles, attributes);
        Usuario usuario = atualizarIdKeycloakUsuario(usuarioTemporario.getId(), uuidUsuarioKeycloak)
                .orElseThrow(() -> new RuntimeException("Falha ao atualizar identificador do usuário"));
        return usuarioToUsuarioResponse.executar(usuario);
    }

    public Usuario criarUsuario(String nomeSobrenome, String email, List<KeycloakRoleResponse> realmRoles,
                                byte[] foto) {
        Usuario usuario = new Usuario();
        usuario.setNome(nomeSobrenome);
        usuario.setEmail(email);
        usuario.setRoles(realmRoles.stream()
                .map(KeycloakRoleResponse::name)
                .collect(Collectors.joining(", ")));
        usuario.setFoto(foto);
        return usuarioRepository.salvar(usuario);
    }

    public Optional<Usuario> atualizarIdKeycloakUsuario(Long idUsuario, UUID idKeycloak) {
        Optional<Usuario> optUsuario = usuarioRepository.buscar(UsuarioExpressions.id((idUsuario)));
        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();
            if (Objects.isNull(usuario.getKeycloakId())) {
                usuario.setKeycloakId(idKeycloak);
                usuarioRepository.salvar(usuario);
            }
        }
        return optUsuario;
    }
}