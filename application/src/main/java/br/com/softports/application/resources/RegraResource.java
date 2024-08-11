//package br.com.softports.application.resources;
//
//import br.com.softports.core.api.common.dto.Pagina;
//import br.com.softports.core.api.regra.dto.RegraRequest;
//import br.com.softports.core.api.regra.dto.RegraResponse;
//import br.com.softports.core.api.regra.usecase.*;
//import br.com.softports.application.resources.dto.CriarRegraRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("regra")
//@RequiredArgsConstructor
//public class RegraResource {
//
//    private final BuscarRegras buscarRegras;
//    private final CriarRegra criarRegra;
//    private final AtualizarSituacaoRegra atualizarSituacaoRegra;
//    private final DeletarRegra deletarRegra;
//
//    @GetMapping
//    Pagina<RegraResponse> buscarRegras(@RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina,
//                                       @RequestParam(required = false, defaultValue = "1") Integer numeroPagina,
//                                       @RequestParam(required = false, defaultValue = "nome") String ordenadoPor,
//                                       @RequestParam(required = false, defaultValue = "asc") String direcao,
//                                       @RequestParam(required = false) RegraSituacao situacao) {
//        return buscarRegras.executar(tamanhoPagina, numeroPagina, ordenadoPor, direcao, situacao);
//    }
//
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
////    @PutMapping("situacao")
////    AtualizarSituacaoRegraResponse atualizarSituacaoRegra(@RequestBody AtualizarSituacaoRegraRequest atualizarSituacaoRegraRequest) {
////        return atualizarSituacaoRegra.executar(
////                atualizarSituacaoRegraRequest.id(),
////                atualizarSituacaoRegraRequest.situacao()
////        );
////    }
//
//    @DeleteMapping("/{regraId}")
//    public void excluirRegra(@PathVariable Long regraId) {
//        deletarRegra.executar(regraId);
//    }
//}