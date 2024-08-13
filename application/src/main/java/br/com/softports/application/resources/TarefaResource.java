package br.com.softports.application.resources;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.usecase.BuscarProjetos;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.usecase.BuscarTarefas;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tarefa")
@RequiredArgsConstructor
public class TarefaResource {

    private final BuscarTarefas buscarTarefas;

    @GetMapping
    Pagina<TarefaResponse> buscarTarefas(
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
            @RequestParam(required = false, defaultValue = "asc") String direcao) {
        return buscarTarefas.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao);
    }

    @GetMapping("{id}")
    TarefaResponse buscarTarefa(@PathVariable Long id) {
        return buscarTarefas.executar(id);
    }
}