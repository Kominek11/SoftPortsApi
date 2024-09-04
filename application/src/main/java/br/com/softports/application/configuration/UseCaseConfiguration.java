package br.com.softports.application.configuration;

import br.com.softports.core.api.classificacao.repository.ClassificacaoRepository;
import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.api.common.service.HttpService;
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
import br.com.softports.core.api.properties.KeycloakProperties;
import br.com.softports.core.api.subclassificacao.repository.SubClassificacaoRepository;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.*;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.api.usuario.usecase.*;
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
import br.com.softports.core.internal.usuario.usecase.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

@Configuration
public class UseCaseConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

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
    CriarProjeto criarProjeto(ProjetoRepository projetoRepository, OrganizacaoRepository organizacaoRepository,
                              UsuarioRepository usuarioRepository) {
        return new CriarProjetoDefault(projetoRepository, organizacaoRepository, usuarioRepository);
    }

    @Bean
    AtualizarProjeto atualizarProjeto(ProjetoRepository projetoRepository, OrganizacaoRepository organizacaoRepository,
                                      UsuarioRepository usuarioRepository) {
        return new AtualizarProjetoDefault(projetoRepository, organizacaoRepository, usuarioRepository);
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
                            UsuarioRepository usuarioRepository, ClassificacaoRepository classificacaoRepository,
                            SubClassificacaoRepository subClassificacaoRepository) {
        return new CriarTarefaDefault(tarefaRepository, projetoRepository,
                usuarioRepository, classificacaoRepository, subClassificacaoRepository);
    }

    @Bean
    AtualizarTarefa atualizarTarefa(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository,
                                    UsuarioRepository usuarioRepository, ClassificacaoRepository classificacaoRepository,
                                    SubClassificacaoRepository subClassificacaoRepository) {
        return new AtualizarTarefaDefault(tarefaRepository, projetoRepository,
                usuarioRepository, classificacaoRepository, subClassificacaoRepository);
    }

    @Bean
    AtualizarStatusTarefa atualizarStatusTarefa(TarefaRepository tarefaRepository) {
        return new AtualizarStatusTarefaDefault(tarefaRepository);
    }

    @Bean
    AtualizarFeedbackTarefa atualizarFeedbackTarefa(TarefaRepository tarefaRepository) {
        return new AtualizarFeedbackTarefaDefault(tarefaRepository);
    }

    @Bean
    AtualizarFechadoTarefa atualizarFechadoTarefa(TarefaRepository tarefaRepository) {
        return new AtualizarFechadoTarefaDefault(tarefaRepository);
    }

    @Bean
    AtualizarPosicoesTarefa atualizarPosicoesTarefa(TarefaRepository tarefaRepository) {
        return new AtualizarPosicoesTarefaDefault(tarefaRepository);
    }

    @Bean
    IncluirComentarioTarefa incluirComentarioTarefa(TarefaRepository tarefaRepository,
                                                    ComentarioRepository comentarioRepository,
                                                    UsuarioRepository usuarioRepository) {
        return new IncluirComentarioTarefaDefault(tarefaRepository, comentarioRepository, usuarioRepository);
    }

    @Bean
    DeletarTarefa deletarTarefa(TarefaRepository tarefaRepository, ComentarioRepository comentarioRepository) {
        return new DeletarTarefaDefault(tarefaRepository, comentarioRepository);
    }

    @Bean
    BuscarUsuarios buscarUsuarios(UsuarioRepository usuarioRepository) {
        return new BuscarUsuariosDefault(usuarioRepository);
    }

    @Bean
    CriarUsuario criarUsuario(CriarUsuarioKeycloak criarUsuarioKeycloak,
                              BuscarUsuarioKeycloak buscarUsuarioKeycloak,
                              UsuarioRepository usuarioRepository,
                              UsuarioToUsuarioResponse usuarioToUsuarioResponse) {
        return new CriarUsuarioDefault(criarUsuarioKeycloak, buscarUsuarioKeycloak,
                usuarioRepository, usuarioToUsuarioResponse);
    }

    @Bean
    CriarUsuarioKeycloak criarUsuarioKeycloak(KeycloakProperties keycloakProperties,
                                              ObterToken obterToken,
                                              HttpService httpService) {
        return new CriarUsuarioKeycloakDefault(keycloakProperties, obterToken, httpService);
    }

    @Bean
    DeletarUsuario deletarUsuario(DeletarUsuarioKeycloak deletarUsuarioKeycloak,
                                  UsuarioRepository usuarioRepository,
                                  TarefaRepository tarefaRepository) {
        return new DeletarUsuarioDefault(deletarUsuarioKeycloak, usuarioRepository, tarefaRepository);
    }

    @Bean
    DeletarUsuarioKeycloak deletarUsuarioKeycloak(KeycloakProperties keycloakProperties,
                                                  ObterToken obterToken,
                                                  HttpService httpService) {
        return new DeletarUsuarioKeycloakDefault(keycloakProperties, obterToken, httpService);
    }

    @Bean
    ObterToken obterToken(KeycloakProperties keycloakProperties,
                          HttpService httpService) {
        return new ObterTokenDefault(keycloakProperties, httpService);
    }

    @Bean
    UsuarioToUsuarioResponse usuarioToUsuarioResponse() {
        return new UsuarioToUsuarioResponseDefault();
    }

    @Bean
    BuscarUsuarioKeycloak buscarUsuarioKeycloak(KeycloakProperties keycloakProperties,
                                                ObterToken obterToken,
                                                HttpService httpService) {
        return new BuscarUsuarioKeycloakDefault(keycloakProperties, obterToken, httpService);
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