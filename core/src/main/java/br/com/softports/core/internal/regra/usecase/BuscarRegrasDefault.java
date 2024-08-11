//package br.com.softports.core.internal.regra.usecase;
//
//import br.com.softports.core.api.common.dto.Pagina;
//import br.com.softports.core.api.regra.dto.RegraResponse;
//import br.com.softports.core.api.regra.repository.RegraRepository;
//import br.com.softports.core.api.regra.usecase.BuscarRegras;
//import br.com.softports.core.internal.common.exception.RecursoNaoEncontradoException;
//import br.com.softports.core.internal.regra.expression.RegraExpressions;
//import com.querydsl.core.BooleanBuilder;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//public class BuscarRegrasDefault implements BuscarRegras {
//
//    private final RegraRepository regraRepository;
//
//    @Override
//    public Pagina<RegraResponse> executar(Integer tamanhoPagina, Integer numeroPagina, String ordenadoPor,
//                                          String direcao, RegraSituacao situacao) {
//            BooleanBuilder filtro = new BooleanBuilder()
//                    .and(RegraExpressions.situacao(situacao));
//            List<RegraResponse> regras = regraRepository
//                    .buscarTodos(filtro,
//                            tamanhoPagina,
//                            numeroPagina,
//                            ordenadoPor,
//                            direcao)
//                    .stream()
//                    .map(this::gerarInfoRegra)
//                    .toList();
//        return paginar(tamanhoPagina, numeroPagina, regras, filtro);
//    }
//
//    @Override
//    public RegraResponse executar(Long regraId) {
//        BooleanBuilder filtro = new BooleanBuilder().and(RegraExpressions.id(regraId));
//        return regraRepository.buscar(filtro)
//                .map(this::gerarInfoRegra)
//                .orElseThrow(() -> new RecursoNaoEncontradoException("Regra"));
//    }
//
//    private Pagina<RegraResponse> paginar(Integer tamanhoPagina, Integer numeroPagina, List<RegraResponse> regras, BooleanBuilder filtro) {
//        Long regraQuantidade = regraRepository.contar(filtro);
//        int quantidadePaginas = (int) Math.ceil((double) regraQuantidade / tamanhoPagina);
//        return new Pagina<>(true, numeroPagina, quantidadePaginas, tamanhoPagina, regraQuantidade, regras);
//    }
//
//    private RegraResponse gerarInfoRegra(Regra regra) {
//        return new RegraResponse(
//                regra.getId(),
//                regra.getNome(),
//                regra.getSituacao().toString(),
//                regra.getEmailNotificacao(),
//                regra.getDataInclusao(),
//                regra.getDataInativacao()
//        );
//    }
//}