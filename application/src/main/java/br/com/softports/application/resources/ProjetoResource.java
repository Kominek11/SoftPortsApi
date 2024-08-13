package br.com.softports.application.resources;

import br.com.softports.application.resources.dto.projeto.AtualizarProjetoRequest;
import br.com.softports.application.resources.dto.projeto.CriarProjetoRequest;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.projeto.usecase.AtualizarProjeto;
import br.com.softports.core.api.projeto.usecase.BuscarProjetos;
import br.com.softports.core.api.projeto.usecase.CriarProjeto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projeto")
@RequiredArgsConstructor
public class ProjetoResource {

    private final BuscarProjetos buscarProjetos;
    private final CriarProjeto criarProjeto;
    private final AtualizarProjeto atualizarProjeto;

    @GetMapping
    Pagina<ProjetoResponse> buscarProjetos(
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
            @RequestParam(required = false, defaultValue = "asc") String direcao) {
        return buscarProjetos.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao);
    }

    @GetMapping("{id}")
    ProjetoResponse buscarProjeto(@PathVariable Long id) {
        return buscarProjetos.executar(id);
    }

    @PostMapping
    ProjetoResponse criarProjeto(@RequestBody CriarProjetoRequest criarProjetoRequest) {
        return criarProjeto.executar(criarProjetoRequest.nome(), criarProjetoRequest.organizacaoId());
    }

    @PutMapping()
    ProjetoResponse atualizarProjeto(@RequestBody AtualizarProjetoRequest atualizarProjetoRequest) {
        return atualizarProjeto.executar(
                atualizarProjetoRequest.id(),
                atualizarProjetoRequest.nome(),
                atualizarProjetoRequest.organizacaoId());
    }
}