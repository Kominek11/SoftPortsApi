package br.com.softports.application.configuration;

import br.com.softports.core.api.common.usecase.expression.*;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.AtualizarOrganizacao;
import br.com.softports.core.api.organizacao.usecase.BuscarOrganizacoes;
import br.com.softports.core.api.organizacao.usecase.CriarOrganizacao;
import br.com.softports.core.api.organizacao.usecase.DeletarOrganizacao;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.projeto.usecase.AtualizarProjeto;
import br.com.softports.core.api.projeto.usecase.BuscarProjetos;
import br.com.softports.core.api.projeto.usecase.CriarProjeto;
import br.com.softports.core.api.regra.usecase.*;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.BuscarTarefas;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.api.usuario.usecase.BuscarUsuarios;
import br.com.softports.core.internal.common.usecase.expression.*;
import br.com.softports.core.internal.organizacao.usecase.AtualizarOrganizacaoDefault;
import br.com.softports.core.internal.organizacao.usecase.BuscarOrganizacoesDefault;
import br.com.softports.core.internal.organizacao.usecase.CriarOrganizacaoDefault;
import br.com.softports.core.internal.organizacao.usecase.DeletarOrganizacaoDefault;
import br.com.softports.core.internal.projeto.usecase.AtualizarProjetoDefault;
import br.com.softports.core.internal.projeto.usecase.BuscarProjetosDefault;
import br.com.softports.core.internal.projeto.usecase.CriarProjetoDefault;
import br.com.softports.core.internal.tarefa.usecase.BuscarTarefasDefault;
import br.com.softports.core.internal.usuario.usecase.BuscarUsuariosDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

@Configuration
public class UseCaseConfiguration {

    @Bean
    BuscarOrganizacoes buscarOrganizacoes(OrganizacaoRepository organizacaoRepository) {
        return new BuscarOrganizacoesDefault(organizacaoRepository);
    }

    @Bean
    CriarOrganizacao criarOrganizacao(OrganizacaoRepository organizacaoRepository) {
        return new CriarOrganizacaoDefault(organizacaoRepository);
    }

    @Bean
    AtualizarOrganizacao atualizarOrganizacao(OrganizacaoRepository organizacaoRepository) {
        return new AtualizarOrganizacaoDefault(organizacaoRepository);
    }

    @Bean
    DeletarOrganizacao deletarOrganizacao(OrganizacaoRepository organizacaoRepository) {
        return new DeletarOrganizacaoDefault(organizacaoRepository);
    }

    @Bean
    BuscarProjetos buscarProjetos(ProjetoRepository projetoRepository) {
        return new BuscarProjetosDefault(projetoRepository);
    }

    @Bean
    CriarProjeto criarProjeto(ProjetoRepository projetoRepository, OrganizacaoRepository organizacaoRepository) {
        return new CriarProjetoDefault(projetoRepository, organizacaoRepository);
    }

    @Bean
    AtualizarProjeto atualizarProjeto(ProjetoRepository projetoRepository, OrganizacaoRepository organizacaoRepository) {
        return new AtualizarProjetoDefault(projetoRepository, organizacaoRepository);
    }

    @Bean
    BuscarTarefas buscarTarefas(TarefaRepository tarefaRepository) {
        return new BuscarTarefasDefault(tarefaRepository);
    }

    @Bean
    BuscarUsuarios buscarUsuarios(UsuarioRepository usuarioRepository) {
        return new BuscarUsuariosDefault(usuarioRepository);
    }



//    @Bean
//    BuscarRegras buscarRegras(RegraRepository regraRepository) {
//        return new BuscarRegrasDefault(regraRepository);
//    }
//
//    @Bean
//    CriarRegra criarRegra(RegraRepository regraRepository) {
//        return new CriarRegraDefault(regraRepository);
//    }
//
//    @Bean
//    AtualizarSituacaoRegra atualizarSituacaoRegra(RegraRepository regraRepository) {
//        return new AtualizarSituacaoRegraDefault(regraRepository);
//    }
//
//    @Bean
//    DeletarRegra deletarRegra(RegraRepository regraRepository) {
//        return new DeletarRegraDefault(regraRepository);
//    }

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