package br.com.softports.application.configuration;

import br.com.softports.core.api.common.usecase.expression.*;
import br.com.softports.core.api.regra.repository.RegraRepository;
import br.com.softports.core.api.regra.usecase.*;
import br.com.softports.core.internal.common.usecase.expression.*;
import br.com.softports.core.internal.regra.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

@Configuration
public class UseCaseConfiguration {

    @Bean
    BuscarRegras buscarRegras(RegraRepository regraRepository) {
        return new BuscarRegrasDefault(regraRepository);
    }

    @Bean
    CriarRegra criarRegra(RegraRepository regraRepository) {
        return new CriarRegraDefault(regraRepository);
    }

    @Bean
    AtualizarSituacaoRegra atualizarSituacaoRegra(RegraRepository regraRepository) {
        return new AtualizarSituacaoRegraDefault(regraRepository);
    }

    @Bean
    DeletarRegra deletarRegra(RegraRepository regraRepository) {
        return new DeletarRegraDefault(regraRepository);
    }

    @Bean
    GerarExpression gerarExpression(GerarPredicadoInteger gerarPredicadoInteger,
                                    GerarPredicadoLong gerarPredicadoLong,
                                    GerarPredicadoDecimal gerarPredicadoDecimal,
                                    GerarPredicadoFloat gerarPredicadoFloat,
                                    GerarPredicadoBoolean gerarPredicadoBoolean,
                                    GerarPredicadoString gerarPredicadoString,
                                    GerarPredicadoDate gerarPredicadoDate,
                                    GerarPredicadoDateTime gerarPredicateDateTime) {
        return new GerarExpressionDefault(gerarPredicadoInteger,
                gerarPredicadoLong,
                gerarPredicadoDecimal,
                gerarPredicadoFloat,
                gerarPredicadoBoolean,
                gerarPredicadoString,
                gerarPredicadoDate,
                gerarPredicateDateTime);
    }

    @Bean
    GerarPredicadoBoolean gerarPredicadoBoolean() {
        return new GerarPredicadoBooleanDefault();
    }

    @Bean
    GerarPredicadoDate gerarPredicadoDate() {
        return new GerarPredicadoDateDefault();
    }

    @Bean
    GerarPredicadoDateTime gerarPredicadoDateTime() {
        return new GerarPredicadoDateTimeDefault();
    }

    @Bean
    GerarPredicadoDecimal gerarPredicadoDecimal() {
        return new GerarPredicadoDecimalDefault();
    }

    @Bean
    GerarPredicadoFloat gerarPredicadoFloat() {
        return new GerarPredicadoFloatDefault();
    }

    @Bean
    GerarPredicadoInteger gerarPredicadoInteger() {
        return new GerarPredicadoIntegerDefault();
    }

    @Bean
    GerarPredicadoLong gerarPredicadoLong() {
        return new GerarPredicadoLongDefault();
    }

    @Bean
    GerarPredicadoString gerarPredicadoString() {
        return new GerarPredicadoStringDefault();
    }
}