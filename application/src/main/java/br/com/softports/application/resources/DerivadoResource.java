package br.com.softports.application.resources;

import br.com.softports.application.resources.dto.derivado.CriarDerivadoListaRequest;
import br.com.softports.application.resources.dto.derivado.CriarDerivadoRequest;
import br.com.softports.application.resources.dto.derivado_tarefa_matriz.CriarDerivadoTarefaMatrizListaRequest;
import br.com.softports.application.resources.dto.derivado_tarefa_matriz.CriarDerivadoTarefaMatrizRequest;
import br.com.softports.application.resources.dto.projeto.AtualizarProjetoRequest;
import br.com.softports.application.resources.dto.projeto.CriarProjetoRequest;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.api.derivado.usecase.BuscarDerivados;
import br.com.softports.core.api.derivado.usecase.CriarDerivado;
import br.com.softports.core.api.derivado.usecase.CriarDerivadoLista;
import br.com.softports.core.api.derivado_tarefa_matriz.dto.DerivadoTarefaMatrizResponse;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.BuscarDerivadosTarefaMatriz;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.CriarDerivadoTarefaMatriz;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.CriarDerivadoTarefaMatrizLista;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.usecase.AtualizarProjeto;
import br.com.softports.core.api.projeto.usecase.BuscarProjetos;
import br.com.softports.core.api.projeto.usecase.CriarProjeto;
import br.com.softports.core.api.projeto.usecase.DeletarProjeto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("derivado")
@RequiredArgsConstructor
public class DerivadoResource {

    private final BuscarDerivados buscarDerivados;
    private final CriarDerivado criarDerivado;
    private final BuscarDerivadosTarefaMatriz buscarDerivadosTarefaMatriz;
    private final CriarDerivadoTarefaMatriz criarDerivadoTarefaMatriz;
    private final CriarDerivadoLista criarDerivadoLista;
    private final CriarDerivadoTarefaMatrizLista criarDerivadoTarefaMatrizLista;

    @GetMapping
    Pagina<DerivadoResponse> buscarDerivados(
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
            @RequestParam(required = false, defaultValue = "asc") String direcao,
            @RequestParam Long projetoId) {
        return buscarDerivados.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao, projetoId);
    }

    @PostMapping
    DerivadoResponse criarDerivado(@RequestBody CriarDerivadoRequest criarDerivadoRequest) {
        return criarDerivado.executar(criarDerivadoRequest.nome(), criarDerivadoRequest.projetoId());
    }

    @PostMapping("lista")
    List<DerivadoResponse> criarDerivadoLista(@RequestBody CriarDerivadoListaRequest criarDerivadoListaRequest) {
        return criarDerivadoLista.executar(criarDerivadoListaRequest.derivadoResponses());
    }

    @GetMapping("matriz")
    Pagina<DerivadoTarefaMatrizResponse> buscarDerivadosTarefaMatriz(
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
            @RequestParam(required = false, defaultValue = "asc") String direcao,
            @RequestParam Long projetoId) {
        return buscarDerivadosTarefaMatriz.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao, projetoId);
    }

    @PostMapping("matriz")
    DerivadoTarefaMatrizResponse criarDerivadoTarefaMatriz(@RequestBody CriarDerivadoTarefaMatrizRequest
                                                           criarDerivadoTarefaMatrizRequest) {
        return criarDerivadoTarefaMatriz.executar(criarDerivadoTarefaMatrizRequest.derivadoId(),
                                                  criarDerivadoTarefaMatrizRequest.tarefaId(),
                                                  criarDerivadoTarefaMatrizRequest.valor(),
                                                  criarDerivadoTarefaMatrizRequest.projetoId());
    }

    @PostMapping("matriz/lista")
    List<DerivadoTarefaMatrizResponse> criarDerivadoTarefaMatrizLista(
            @RequestBody CriarDerivadoTarefaMatrizListaRequest criarDerivadoTarefaMatrizRequest
    ) {
        return criarDerivadoTarefaMatrizLista.executar(
                criarDerivadoTarefaMatrizRequest.derivadoTarefaMatrizListaResponses()
        );
    }
}