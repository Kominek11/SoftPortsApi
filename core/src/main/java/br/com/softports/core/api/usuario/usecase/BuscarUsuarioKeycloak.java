package br.com.softports.core.api.usuario.usecase;

import java.util.Optional;
import java.util.UUID;

public interface BuscarUsuarioKeycloak {

    Optional<UUID> executar(String email);

}
