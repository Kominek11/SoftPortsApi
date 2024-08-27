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
import br.com.softports.core.internal.common.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("tarefa")
@RequiredArgsConstructor
public class TarefaResource {

    private final BuscarTarefas buscarTarefas;
    private final CriarTarefa criarTarefa;
    private final AtualizarTarefa atualizarTarefa;
    private final DeletarTarefa deletarTarefa;
    private final AtualizarStatusTarefa atualizarStatusTarefa;
    private final IncluirComentarioTarefa incluirComentarioTarefa;
    private final AtualizarFeedbackTarefa atualizarFeedbackTarefa;

    @GetMapping
    Pagina<TarefaResponse> buscarTarefas(
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
            @RequestParam(required = false, defaultValue = "asc") String direcao,
            @RequestParam(required = false) Long projetoId,
            @RequestParam(required = false) Boolean fechada,
            @RequestParam(required = false) LocalDate dataCriacao,
            @RequestParam(required = false) LocalDate dataFechamento,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Set<Long> usuarios,
            @RequestParam(required = false) Long prioridade,
            @RequestParam(required = false) Long classificacao
            ) {
        return buscarTarefas.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao, projetoId,
                fechada, dataCriacao, dataFechamento, titulo, usuarios, prioridade, classificacao);
    }

    @GetMapping("{id}")
    TarefaResponse buscarTarefa(@PathVariable Long id) {
        return buscarTarefas.executar(id);
    }

    @PostMapping
    TarefaResponse criarTarefa(@RequestBody CriarTarefaRequest criarTarefaRequest) {
        return criarTarefa.executar(criarTarefaRequest.titulo(), criarTarefaRequest.descricao(),
                criarTarefaRequest.so(), criarTarefaRequest.caminho(),
                criarTarefaRequest.dataEstimada(), criarTarefaRequest.prioridade(), criarTarefaRequest.classificacao(),
                criarTarefaRequest.status(), criarTarefaRequest.fechada(), criarTarefaRequest.posicao(),
                criarTarefaRequest.projetoId(), criarTarefaRequest.usuarioIds(), criarTarefaRequest.screenshots());
    }

    @PutMapping()
    TarefaResponse atualizarTarefa(@RequestBody AtualizarTarefaRequest atualizarTarefaRequest) {
        return atualizarTarefa.executar(atualizarTarefaRequest.id(), atualizarTarefaRequest.titulo(),
                atualizarTarefaRequest.descricao(), atualizarTarefaRequest.so(), atualizarTarefaRequest.caminho(),
                atualizarTarefaRequest.dataFechamento(), atualizarTarefaRequest.dataEstimada(),
                atualizarTarefaRequest.prioridade(), atualizarTarefaRequest.classificacao(),
                atualizarTarefaRequest.status(), atualizarTarefaRequest.fechada(),
                atualizarTarefaRequest.posicao(),  atualizarTarefaRequest.projetoId(),
                atualizarTarefaRequest.usuarioIds(), atualizarTarefaRequest.screenshots());
    }

    @PutMapping("/status/{id}")
    TarefaResponse atualizarStatusTarefa(@PathVariable Long id, Long status) {
        return atualizarStatusTarefa.executar(id, status);
    }

    @PutMapping("/feedback/{id}")
    TarefaResponse atualizarFeedbackTarefa(@PathVariable Long id, String feedback) {
        return atualizarFeedbackTarefa.executar(id, feedback);
    }

    @PutMapping("/comentario/{id}")
    TarefaResponse incluirComentarioTarefa(@PathVariable Long id, String conteudo) {
        return incluirComentarioTarefa.executar(id, conteudo);
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        deletarTarefa.executar(id);
    }
}