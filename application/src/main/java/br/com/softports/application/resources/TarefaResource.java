package br.com.softports.application.resources;

import br.com.softports.application.resources.dto.projeto.AtualizarProjetoRequest;
import br.com.softports.application.resources.dto.projeto.CriarProjetoRequest;
import br.com.softports.application.resources.dto.tarefa.AtualizarTarefaRequest;
import br.com.softports.application.resources.dto.tarefa.CriarTarefaRequest;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.usecase.AtualizarProjeto;
import br.com.softports.core.api.projeto.usecase.BuscarProjetos;
import br.com.softports.core.api.projeto.usecase.CriarProjeto;
import br.com.softports.core.api.projeto.usecase.DeletarProjeto;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("tarefa")
@RequiredArgsConstructor
public class TarefaResource {

    private final BuscarTarefas buscarTarefas;
    private final CriarTarefa criarTarefa;
    private final AtualizarTarefa atualizarTarefa;
    private final DeletarTarefa deletarTarefa;
    private final AtualizarStatusTarefa atualizarStatusTarefa;

    @GetMapping
    Pagina<TarefaResponse> buscarTarefas(
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
            @RequestParam(required = false, defaultValue = "asc") String direcao,
            @RequestParam(required = false) Long projetoId,
            @RequestParam(required = false) Boolean fechada,
            @RequestParam(required = false) Date dataCriacao,
            @RequestParam(required = false) Date dataCorrecao
            ) {
        return buscarTarefas.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao, projetoId,
                fechada, dataCriacao, dataCorrecao);
    }

    @GetMapping("{id}")
    TarefaResponse buscarTarefa(@PathVariable Long id) {
        return buscarTarefas.executar(id);
    }

    @PostMapping
    TarefaResponse criarTarefa(@RequestBody CriarTarefaRequest criarTarefaRequest) {
        return criarTarefa.executar(criarTarefaRequest.titulo(), criarTarefaRequest.descricao(),
                criarTarefaRequest.so(), criarTarefaRequest.caminho(), criarTarefaRequest.dataCorrecao(),
                criarTarefaRequest.dataCriacao(), criarTarefaRequest.prioridade(), criarTarefaRequest.classificacao(),
                criarTarefaRequest.status(), criarTarefaRequest.fechada(), criarTarefaRequest.projetoId(),
                criarTarefaRequest.usuarioIds(), criarTarefaRequest.screenshots());
    }

    @PutMapping()
    TarefaResponse atualizarTarefa(@RequestBody AtualizarTarefaRequest atualizarTarefaRequest) {
        return atualizarTarefa.executar(atualizarTarefaRequest.id(), atualizarTarefaRequest.titulo(),
                atualizarTarefaRequest.descricao(), atualizarTarefaRequest.so(), atualizarTarefaRequest.caminho(),
                atualizarTarefaRequest.dataCorrecao(), atualizarTarefaRequest.dataCriacao(),
                atualizarTarefaRequest.prioridade(), atualizarTarefaRequest.classificacao(),
                atualizarTarefaRequest.status(), atualizarTarefaRequest.projetoId(),
                atualizarTarefaRequest.usuarioIds(),
                atualizarTarefaRequest.screenshots());
    }

    @PutMapping("/status/{id}")
    TarefaResponse atualizarStatusTarefa (@PathVariable Long id, Long status) {
        return atualizarStatusTarefa.executar(id, status);
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        deletarTarefa.executar(id);
    }
}