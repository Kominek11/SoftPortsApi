package br.com.softports.application.configuration;

import br.com.softports.core.api.classificacao.repository.ClassificacaoRepository;
import br.com.softports.core.api.comentario.repository.ComentarioRepository;
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
import br.com.softports.core.api.projeto.usecase.DeletarProjeto;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.*;
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
import br.com.softports.core.internal.projeto.usecase.DeletarProjetoDefault;
import br.com.softports.core.internal.tarefa.usecase.*;
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
    DeletarProjeto deletarProjeto(ProjetoRepository projetoRepository, TarefaRepository tarefaRepository) {
        return new DeletarProjetoDefault(projetoRepository, tarefaRepository);
    }

    @Bean
    BuscarTarefas buscarTarefas(TarefaRepository tarefaRepository, ComentarioRepository comentarioRepository,
                                UsuarioRepository usuarioRepository) {
        return new BuscarTarefasDefault(tarefaRepository, comentarioRepository, usuarioRepository);
    }

    @Bean
    CriarTarefa criarTarefa(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository,
                            UsuarioRepository usuarioRepository, ClassificacaoRepository classificacaoRepository) {
        return new CriarTarefaDefault(tarefaRepository, projetoRepository,
                usuarioRepository, classificacaoRepository);
    }

    @Bean
    AtualizarTarefa atualizarTarefa(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository,
                                    UsuarioRepository usuarioRepository, ClassificacaoRepository classificacaoRepository) {
        return new AtualizarTarefaDefault(tarefaRepository, projetoRepository,
                usuarioRepository, classificacaoRepository);
    }

    @Bean
    AtualizarStatusTarefa atualizarStatusTarefa(TarefaRepository tarefaRepository) {
        return new AtualizarStatusTarefaDefault(tarefaRepository);
    }

    @Bean
    AtualizarFechadoTarefa atualizarFechadoTarefa(TarefaRepository tarefaRepository) {
        return new AtualizarFechadoTarefaDefault(tarefaRepository);
    }

    @Bean
    AtualizarFeedbackTarefa atualizarFeedbackTarefa(TarefaRepository tarefaRepository) {
        return new AtualizarFeedbackTarefaDefault(tarefaRepository);
    }

    @Bean
    IncluirComentarioTarefa incluirComentarioTarefa(TarefaRepository tarefaRepository,
                                                    ComentarioRepository comentarioRepository) {
        return new IncluirComentarioTarefaDefault(tarefaRepository, comentarioRepository);
    }

    @Bean
    DeletarTarefa deletarTarefa(TarefaRepository tarefaRepository) {
        return new DeletarTarefaDefault(tarefaRepository);
    }

    @Bean
    BuscarUsuarios buscarUsuarios(UsuarioRepository usuarioRepository) {
        return new BuscarUsuariosDefault(usuarioRepository);
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