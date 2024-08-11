package br.com.softports.application.resources;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.organizacao.usecase.BuscarOrganizacoes;
import br.com.softports.core.api.regra.dto.RegraRequest;
import br.com.softports.core.api.regra.dto.RegraResponse;
import br.com.softports.core.api.regra.usecase.*;
import br.com.softports.application.resources.dto.CriarRegraRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("organizacao")
@RequiredArgsConstructor
public class OrganizacaoResource {

    private final BuscarOrganizacoes buscarOrganizacoes;

    @GetMapping
    Pagina<OrganizacaoResponse> buscarOrganizacoes(@RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
                                             @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
                                             @RequestParam(required = false, defaultValue = "id") String ordenadoPor,
                                             @RequestParam(required = false, defaultValue = "asc") String direcao) {
        return buscarOrganizacoes.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao);
    }

//    @GetMapping("{regraId}")
//    RegraResponse buscarRegra(@PathVariable Long regraId) {
//        return buscarRegras.executar(regraId);
//    }
//
//    @PostMapping
//    RegraRequest criarRegra(@RequestBody CriarRegraRequest criarRegraRequest) {
//        return criarRegra.executar(criarRegraRequest.nome(),
//                criarRegraRequest.inconsistenciaTipoId(),
//                criarRegraRequest.emailNotificacao(),
//                criarRegraRequest.usuarioId());
//    }
//
//
//    @DeleteMapping("/{regraId}")
//    public void excluirRegra(@PathVariable Long regraId) {
//        deletarRegra.executar(regraId);
//    }
}