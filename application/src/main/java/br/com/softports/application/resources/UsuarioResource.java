package br.com.softports.application.resources;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.usecase.BuscarTarefas;
import br.com.softports.core.api.usuario.dto.AtualizarUsuarioRequest;
import br.com.softports.core.api.usuario.dto.CriarUsuarioRequest;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioResource {

    private final BuscarUsuarios buscarUsuarios;
    private final CriarUsuario criarUsuario;
    private final DeletarUsuario deletarUsuario;
    private final AtualizarUsuario atualizarUsuario;
    private final AtualizarFotoUsuario atualizarFotoUsuario;

    @PostMapping
    UsuarioResponse criarUsuario(@RequestBody CriarUsuarioRequest criarUsuarioRequest) {
        return criarUsuario.executar(criarUsuarioRequest.nome(),
                                     criarUsuarioRequest.sobrenome(),
                                     criarUsuarioRequest.email(),
                                     criarUsuarioRequest.emailVerified(),
                                     criarUsuarioRequest.username(),
                                     criarUsuarioRequest.realmRoles(),
                                     criarUsuarioRequest.attributes(),
                                     criarUsuarioRequest.foto());
    }

    @GetMapping
    Pagina<UsuarioResponse> buscarUsuarios(
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
            @RequestParam(required = false, defaultValue = "asc") String direcao,
            @RequestParam(required = false) Long projetoId,
            @RequestParam(required = false) String nomeUsuario) {
        return buscarUsuarios.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao, projetoId, nomeUsuario);
    }

    @GetMapping("{id}")
    UsuarioResponse buscarUsuario(@PathVariable Long id) {
        return buscarUsuarios.executar(id);
    }

    @PutMapping("{id}")
    UsuarioResponse atualizarUsuario(@PathVariable UUID id,
                                     @RequestBody AtualizarUsuarioRequest atualizarUsuarioRequest) {
        return atualizarUsuario.executar(id,
                                         atualizarUsuarioRequest.firstName(),
                                         atualizarUsuarioRequest.email(),
                                         atualizarUsuarioRequest.username(),
                                         atualizarUsuarioRequest.foto());
    }

    @PutMapping("foto/{id}")
    UsuarioResponse atualizarFotoUsuario(@PathVariable UUID id,
                                         @RequestBody byte[] foto) {
        return atualizarFotoUsuario.executar(id, foto);
    }

    @DeleteMapping("{id}")
    void deletarUsuario(@PathVariable UUID id) {
        deletarUsuario.executar(id);
    }
}