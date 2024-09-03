package br.com.softports.application.resources;

import br.com.softports.application.resources.dto.projeto.AtualizarProjetoRequest;
import br.com.softports.application.resources.dto.projeto.CriarProjetoRequest;
import br.com.softports.application.resources.dto.tarefa.AtualizarPosicoesTarefaRequest;
import br.com.softports.application.resources.dto.tarefa.AtualizarTarefaRequest;
import br.com.softports.application.resources.dto.tarefa.CriarTarefaRequest;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.usecase.AtualizarProjeto;
import br.com.softports.core.api.projeto.usecase.BuscarProjetos;
import br.com.softports.core.api.projeto.usecase.CriarProjeto;
import br.com.softports.core.api.projeto.usecase.DeletarProjeto;
import br.com.softports.core.api.tarefa.dto.TarefaPosicaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.usecase.*;
import br.com.softports.core.internal.common.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
    private final AtualizarFechadoTarefa atualizarFechadoTarefa;
    private final AtualizarPosicoesTarefa atualizarPosicoesTarefa;

    @GetMapping
    Pagina<TarefaResponse> buscarTarefas(
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
            @RequestParam(required = false, defaultValue = "asc") String direcao,
            @RequestParam(required = false) Long projetoId,
            @RequestParam(required = false) Boolean fechada,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Set<Long> usuarios,
            @RequestParam(required = false) List<Long> prioridades,
            @RequestParam(required = false) List<Long> classificacao,
            @RequestParam(required = false) List<Long> subClassificacao
            ) {
        return buscarTarefas.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao, projetoId,
                fechada, dataInicio, dataFim, titulo, usuarios, prioridades, classificacao, subClassificacao);
    }

    @GetMapping("{id}")
    TarefaResponse buscarTarefa(@PathVariable Long id) {
        return buscarTarefas.executar(id);
    }

    @PostMapping
    TarefaResponse criarTarefa(@RequestBody CriarTarefaRequest criarTarefaRequest) {
        return criarTarefa.executar(criarTarefaRequest.titulo(), criarTarefaRequest.descricao(),
                criarTarefaRequest.so(), criarTarefaRequest.caminho(),
                criarTarefaRequest.dataEstimada(),
                criarTarefaRequest.status(),
                criarTarefaRequest.projetoId(), criarTarefaRequest.usuarioIds(), criarTarefaRequest.screenshots(),
                criarTarefaRequest.classificacaoId(), criarTarefaRequest.subclassificacaoId(),
                criarTarefaRequest.prioridade());
    }

    @PutMapping()
    TarefaResponse atualizarTarefa(@RequestBody AtualizarTarefaRequest atualizarTarefaRequest) {
        return atualizarTarefa.executar(atualizarTarefaRequest.id(), atualizarTarefaRequest.titulo(),
                atualizarTarefaRequest.descricao(), atualizarTarefaRequest.so(), atualizarTarefaRequest.caminho(),
                atualizarTarefaRequest.dataFechamento(), atualizarTarefaRequest.dataEstimada(),
                atualizarTarefaRequest.status(), atualizarTarefaRequest.fechada(),
                atualizarTarefaRequest.posicao(),  atualizarTarefaRequest.projetoId(),
                atualizarTarefaRequest.usuarioIds(), atualizarTarefaRequest.screenshots(),
                atualizarTarefaRequest.classificacaoId(), atualizarTarefaRequest.subclassificacaoId(),
                atualizarTarefaRequest.prioridade());
    }

    @PutMapping("/status/{id}")
    TarefaResponse atualizarStatusTarefa(@PathVariable Long id, Long status) {
        return atualizarStatusTarefa.executar(id, status);
    }

    @PutMapping("/fechado/{id}")
    TarefaResponse atualizarFechadoTarefa(@PathVariable Long id, Boolean fechado) {
        return atualizarFechadoTarefa.executar(id, fechado);
    }

    @PutMapping("/feedback/{id}")
    TarefaResponse atualizarFeedbackTarefa(@PathVariable Long id, String feedback) {
        return atualizarFeedbackTarefa.executar(id, feedback);
    }

    @PutMapping("/comentario/{id}")
    TarefaResponse incluirComentarioTarefa(@PathVariable Long id, String conteudo, Long usuarioId) {
        return incluirComentarioTarefa.executar(id, conteudo, usuarioId);
    }

    @PutMapping("/posicoes")
    List<TarefaPosicaoResponse> atualizarPosicoesTarefa(@RequestBody AtualizarPosicoesTarefaRequest
                                                                     atualizarPosicoesTarefaRequest) {
        return atualizarPosicoesTarefa.executar(atualizarPosicoesTarefaRequest.tarefas());
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        deletarTarefa.executar(id);
    }
}