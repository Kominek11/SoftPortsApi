package br.com.softports.application.resources;

import br.com.softports.application.resources.dto.organizacao.AtualizarOrganizacaoRequest;
import br.com.softports.application.resources.dto.organizacao.CriarOrganizacaoRequest;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.usecase.AtualizarOrganizacao;
import br.com.softports.core.api.organizacao.usecase.BuscarOrganizacoes;
import br.com.softports.core.api.organizacao.usecase.CriarOrganizacao;
import br.com.softports.core.api.organizacao.usecase.DeletarOrganizacao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("organizacao")
@RequiredArgsConstructor
public class OrganizacaoResource {

    private final BuscarOrganizacoes buscarOrganizacoes;
    private final CriarOrganizacao criarOrganizacao;
    private final AtualizarOrganizacao atualizarOrganizacao;
    private final DeletarOrganizacao deletarOrganizacao;

    @GetMapping
    Pagina<OrganizacaoResponse> buscarOrganizacoes(
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
            @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
            @RequestParam(required = false, defaultValue = "asc") String direcao) {
        return buscarOrganizacoes.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao);
    }

    @GetMapping("{id}")
    OrganizacaoResponse buscarOrganizacao(@PathVariable Long id) {
        return buscarOrganizacoes.executar(id);
    }

    @PostMapping
    OrganizacaoResponse criarOrganizacao(@RequestBody CriarOrganizacaoRequest criarOrganizacaoRequest) {
        return criarOrganizacao.executar(criarOrganizacaoRequest.nome());
    }

    @PutMapping()
    OrganizacaoResponse atualizarOrganizacao(@RequestBody AtualizarOrganizacaoRequest atualizarOrganizacaoRequest) {
        return atualizarOrganizacao.executar(atualizarOrganizacaoRequest.id(), atualizarOrganizacaoRequest.nome()
        );
    }

    @DeleteMapping("/{id}")
    public void deletarOrganizacao(@PathVariable Long id) {
        deletarOrganizacao.executar(id);
    }
}