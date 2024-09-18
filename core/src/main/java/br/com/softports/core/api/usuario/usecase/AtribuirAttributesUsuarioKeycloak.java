package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.usuario.dto.KeycloakRoleResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AtribuirAttributesUsuarioKeycloak {
    void executar(UUID usuarioId, String username, String nome,
                  String sobrenome, String email, Map<String, Object> attributes);
}
