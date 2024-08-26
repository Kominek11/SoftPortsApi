package br.com.softports.application.resources;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.usecase.BuscarTarefas;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.usecase.BuscarUsuarios;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioResource {

    private final BuscarUsuarios buscarUsuarios;

    @GetMapping
    Pagina<UsuarioResponse> buscarUsuarios(
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
            @RequestParam(required = false, defaultValue = "asc") String direcao,
            @RequestParam(required = false) Long projetoId) {
        return buscarUsuarios.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao, projetoId);
    }

    @GetMapping("{id}")
    UsuarioResponse buscarUsuario(@PathVariable Long id) {
        return buscarUsuarios.executar(id);
    }
}