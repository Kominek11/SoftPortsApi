package br.com.softports.application.resources;

import br.com.softports.application.resources.dto.solicitacao.AtualizarPosicoesSolicitacaoRequest;
import br.com.softports.application.resources.dto.solicitacao.AtualizarSolicitacaoRequest;
import br.com.softports.application.resources.dto.solicitacao.CriarSolicitacaoRequest;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.solicitacao.dto.SolicitacaoPosicaoResponse;
import br.com.softports.core.api.solicitacao.dto.SolicitacaoResponse;
import br.com.softports.core.api.solicitacao.usecase.*;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("solicitacao")
@RequiredArgsConstructor
public class SolicitacaoResource {

    private final BuscarSolicitacoes buscarSolicitacoes;
    private final CriarSolicitacao criarSolicitacao;
    private final AtualizarSolicitacao atualizarSolicitacao;
    private final DeletarSolicitacao deletarSolicitacao;
    private final AtualizarStatusSolicitacao atualizarStatusSolicitacao;
    private final AtualizarFechadoSolicitacao atualizarFechadoSolicitacao;
    private final AtualizarFeedbackSolicitacao atualizarFeedbackSolicitacao;
    private final AtualizarPosicoesSolicitacao atualizarPosicoesSolicitacao;
    private final AprovarSolicitacao aprovarSolicitacao;

    @GetMapping
    Pagina<SolicitacaoResponse> buscarTarefas(
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
        return buscarSolicitacoes.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao, projetoId,
                fechada, dataInicio, dataFim, titulo, usuarios, prioridades, classificacao, subClassificacao);
    }

    @GetMapping("{id}")
    SolicitacaoResponse buscarTarefa(@PathVariable Long id) {
        return buscarSolicitacoes.executar(id);
    }

    @PostMapping
    SolicitacaoResponse criarTarefa(@RequestBody CriarSolicitacaoRequest criarSolicitacaoRequest) {
        return criarSolicitacao.executar(criarSolicitacaoRequest.titulo(), criarSolicitacaoRequest.descricao(),
                criarSolicitacaoRequest.so(), criarSolicitacaoRequest.caminho(),
                criarSolicitacaoRequest.dataEstimada(),
                criarSolicitacaoRequest.status(),
                criarSolicitacaoRequest.projetoId(), criarSolicitacaoRequest.usuarioIds(),
                criarSolicitacaoRequest.screenshots(),
                criarSolicitacaoRequest.classificacaoId(), criarSolicitacaoRequest.subclassificacaoId(),
                criarSolicitacaoRequest.prioridade());
    }

    @PutMapping()
    SolicitacaoResponse atualizarTarefa(@RequestBody AtualizarSolicitacaoRequest atualizarSolicitacaoRequest) {
        return atualizarSolicitacao.executar(atualizarSolicitacaoRequest.id(), atualizarSolicitacaoRequest.titulo(),
                atualizarSolicitacaoRequest.descricao(), atualizarSolicitacaoRequest.so(),
                atualizarSolicitacaoRequest.caminho(),
                atualizarSolicitacaoRequest.dataEstimada(),
                atualizarSolicitacaoRequest.status(), atualizarSolicitacaoRequest.fechada(),
                atualizarSolicitacaoRequest.posicao(),  atualizarSolicitacaoRequest.projetoId(),
                atualizarSolicitacaoRequest.usuarioIds(), atualizarSolicitacaoRequest.screenshots(),
                atualizarSolicitacaoRequest.classificacaoId(), atualizarSolicitacaoRequest.subclassificacaoId(),
                atualizarSolicitacaoRequest.prioridade());
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        deletarSolicitacao.executar(id);
    }

    @PutMapping("/status/{id}")
    SolicitacaoResponse atualizarStatusSolicitacao(@PathVariable Long id, Long status) {
        return atualizarStatusSolicitacao.executar(id, status);
    }

    @PutMapping("/fechado/{id}")
    SolicitacaoResponse atualizarFechadoSolicitacao(@PathVariable Long id, Boolean fechado) {
        return atualizarFechadoSolicitacao.executar(id, fechado);
    }

    @PutMapping("/feedback/{id}")
    SolicitacaoResponse atualizarFeedbackSolicitacao(@PathVariable Long id, String feedback) {
        return atualizarFeedbackSolicitacao.executar(id, feedback);
    }

    @PutMapping("/posicoes")
    List<SolicitacaoPosicaoResponse> atualizarPosicoesSolicitacao(@RequestBody AtualizarPosicoesSolicitacaoRequest
                                                                atualizarPosicoesSolicitacaoRequest) {
        return atualizarPosicoesSolicitacao.executar(atualizarPosicoesSolicitacaoRequest.solicitacoes());
    }

    @PutMapping("/aprovar/{solicitacaoId}")
    TarefaResponse aprovarSolicitacao(@PathVariable Long solicitacaoId) {
        return aprovarSolicitacao.executar(solicitacaoId);
    }
}