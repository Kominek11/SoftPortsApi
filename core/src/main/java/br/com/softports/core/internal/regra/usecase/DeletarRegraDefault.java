//package br.com.softports.core.internal.regra.usecase;
//
//import br.com.softports.core.api.regra.repository.RegraRepository;
//import br.com.softports.core.api.regra.usecase.DeletarRegra;
//import br.com.softports.core.internal.regra.expression.RegraExpressions;
//import com.querydsl.core.BooleanBuilder;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class DeletarRegraDefault implements DeletarRegra {
//
//    private final RegraRepository regraRepository;
//
//    @Override
//    public void executar(Long regraId) {
//        BooleanBuilder filtro = new BooleanBuilder().and(RegraExpressions.id(regraId));
//        regraRepository.apagarTodos(filtro);
//    }
//}