package br.com.softports.application.configuration;

import br.com.softports.core.api.classificacao.usecase.ClassificacaoToClassificacaoResponse;
import br.com.softports.core.api.comentario.usecase.ComentarioToComentarioResponse;
import br.com.softports.core.api.dashboard.usecase.BuscarDashboard;
import br.com.softports.core.api.dashboard.usecase.BuscarMetricas;
import br.com.softports.core.api.derivado.repository.DerivadoRepository;
import br.com.softports.core.api.derivado.usecase.*;
import br.com.softports.core.api.derivado_tarefa_matriz.repository.DerivadoTarefaMatrizRepository;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.BuscarDerivadosTarefaMatriz;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.CriarDerivadoTarefaMatriz;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.CriarDerivadoTarefaMatrizLista;
import br.com.softports.core.api.derivado_tarefa_matriz.usecase.DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse;
import br.com.softports.core.api.organizacao.usecase.*;
import br.com.softports.core.api.projeto.usecase.*;
import br.com.softports.core.api.solicitacao.repository.SolicitacaoRepository;
import br.com.softports.core.api.solicitacao.usecase.*;
import br.com.softports.core.api.subclassificacao.usecase.SubClassificacaoToSubClassificacaoResponse;
import br.com.softports.core.api.tarefa_aud.repository.TarefaAudRepository;
import br.com.softports.core.api.tarefa_aud.usecase.CustomRevisionEntityToCustomRevisionEntityResponse;
import br.com.softports.core.api.tarefa_aud.usecase.TarefaAudToTarefaAudResponse;
import br.com.softports.core.internal.classificacao.usecase.ClassificacaoToClassificacaoResponseDefault;
import br.com.softports.core.internal.comentario.usecase.ComentarioToComentarioResponseDefault;
import br.com.softports.core.internal.dashboard.usecase.BuscarDashboardDefault;
import br.com.softports.core.internal.dashboard.usecase.BuscarMetricasDefault;
import br.com.softports.core.internal.derivado.usecase.*;
import br.com.softports.core.internal.derivado_tarefa_matriz.usecase.BuscarDerivadosTarefaMatrizDefault;
import br.com.softports.core.internal.derivado_tarefa_matriz.usecase.CriarDerivadoTarefaMatrizDefault;
import br.com.softports.core.internal.derivado_tarefa_matriz.usecase.CriarDerivadoTarefaMatrizListaDefault;
import br.com.softports.core.internal.derivado_tarefa_matriz.usecase.DerivadoTarefaMatrizToDerivadoTarefaMatrizResponseDefault;
import br.com.softports.core.internal.organizacao.usecase.*;
import br.com.softports.core.internal.projeto.usecase.*;
import br.com.softports.core.internal.properties.KeycloakLoggedUserDataDefault;
import br.com.softports.core.api.classificacao.repository.ClassificacaoRepository;
import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.api.common.service.HttpService;
import br.com.softports.core.api.common.usecase.expression.*;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.api.properties.KeycloakLoggedUserData;
import br.com.softports.core.api.properties.KeycloakProperties;
import br.com.softports.core.api.subclassificacao.repository.SubClassificacaoRepository;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.*;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.api.usuario.usecase.*;
import br.com.softports.core.internal.common.entity.audited.CustomRevisionListener;
import br.com.softports.core.internal.common.usecase.expression.*;
import br.com.softports.core.internal.solicitacao.usecase.*;
import br.com.softports.core.internal.subclassificacao.usecase.SubClassificacaoToSubClassificacaoResponseDefault;
import br.com.softports.core.internal.tarefa.usecase.*;
import br.com.softports.core.internal.tarefa_aud.usecase.CustomRevisionEntityToCustomRevisionEntityResponseDefault;
import br.com.softports.core.internal.tarefa_aud.usecase.TarefaAudToTarefaAudResponseDefault;
import br.com.softports.core.internal.usuario.usecase.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    BuscarOrganizacoes buscarOrganizacoes(OrganizacaoRepository organizacaoRepository,
                                          OrganizacaoToOrganizacaoResponse organizacaoToOrganizacaoResponse) {
        return new BuscarOrganizacoesDefault(organizacaoRepository, organizacaoToOrganizacaoResponse);
    }

    @Bean
    CriarOrganizacao criarOrganizacao(OrganizacaoRepository organizacaoRepository,
                                      OrganizacaoToOrganizacaoResponse organizacaoToOrganizacaoResponse) {
        return new CriarOrganizacaoDefault(organizacaoRepository, organizacaoToOrganizacaoResponse);
    }

    @Bean
    AtualizarOrganizacao atualizarOrganizacao(OrganizacaoRepository organizacaoRepository,
                                              OrganizacaoToOrganizacaoResponse organizacaoToOrganizacaoResponse) {
        return new AtualizarOrganizacaoDefault(organizacaoRepository, organizacaoToOrganizacaoResponse);
    }

    @Bean
    DeletarOrganizacao deletarOrganizacao(OrganizacaoRepository organizacaoRepository) {
        return new DeletarOrganizacaoDefault(organizacaoRepository);
    }

    @Bean
    BuscarProjetos buscarProjetos(ProjetoRepository projetoRepository,
                                  ProjetoToProjetoResponse projetoToProjetoResponse) {
        return new BuscarProjetosDefault(projetoRepository, projetoToProjetoResponse);
    }

    @Bean
    CriarProjeto criarProjeto(ProjetoRepository projetoRepository, OrganizacaoRepository organizacaoRepository,
                              UsuarioRepository usuarioRepository, ProjetoToProjetoResponse projetoToProjetoResponse) {
        return new CriarProjetoDefault(projetoRepository, organizacaoRepository, usuarioRepository,
                projetoToProjetoResponse);
    }

    @Bean
    AtualizarProjeto atualizarProjeto(ProjetoRepository projetoRepository,
                                      OrganizacaoRepository organizacaoRepository,
                                      UsuarioRepository usuarioRepository,
                                      ProjetoToProjetoResponse projetoToProjetoResponse) {
        return new AtualizarProjetoDefault(projetoRepository, organizacaoRepository,
                usuarioRepository, projetoToProjetoResponse);
    }

    @Bean
    DeletarProjeto deletarProjeto(ProjetoRepository projetoRepository, TarefaRepository tarefaRepository) {
        return new DeletarProjetoDefault(projetoRepository, tarefaRepository);
    }

    @Bean
    BuscarTarefas buscarTarefas(TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository,
                                TarefaToTarefaResponse tarefaToTarefaResponse) {
        return new BuscarTarefasDefault(tarefaRepository, usuarioRepository, tarefaToTarefaResponse);
    }

    @Bean
    CriarTarefa criarTarefa(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository,
                            UsuarioRepository usuarioRepository, ClassificacaoRepository classificacaoRepository,
                            SubClassificacaoRepository subClassificacaoRepository,
                            TarefaToTarefaResponse tarefaToTarefaResponse) {
        return new CriarTarefaDefault(tarefaRepository, projetoRepository,
                usuarioRepository, classificacaoRepository, subClassificacaoRepository, tarefaToTarefaResponse);
    }

    @Bean
    AtualizarTarefa atualizarTarefa(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository,
                                    UsuarioRepository usuarioRepository, ClassificacaoRepository classificacaoRepository,
                                    SubClassificacaoRepository subClassificacaoRepository,
                                    TarefaToTarefaResponse tarefaToTarefaResponse) {
        return new AtualizarTarefaDefault(tarefaRepository, projetoRepository,
                usuarioRepository, classificacaoRepository, subClassificacaoRepository, tarefaToTarefaResponse);
    }

    @Bean
    AtualizarStatusTarefa atualizarStatusTarefa(TarefaRepository tarefaRepository,
                                                TarefaToTarefaResponse tarefaToTarefaResponse) {
        return new AtualizarStatusTarefaDefault(tarefaRepository, tarefaToTarefaResponse);
    }

    @Bean
    AtualizarFeedbackTarefa atualizarFeedbackTarefa(TarefaRepository tarefaRepository,
                                                    TarefaToTarefaResponse tarefaToTarefaResponse) {
        return new AtualizarFeedbackTarefaDefault(tarefaRepository, tarefaToTarefaResponse);
    }

    @Bean
    AtualizarFechadoTarefa atualizarFechadoTarefa(TarefaRepository tarefaRepository,
                                                  TarefaToTarefaResponse tarefaToTarefaResponse) {
        return new AtualizarFechadoTarefaDefault(tarefaRepository, tarefaToTarefaResponse);
    }

    @Bean
    AtualizarPosicoesTarefa atualizarPosicoesTarefa(TarefaRepository tarefaRepository,
                                                    TarefaToTarefaPosicaoResponse tarefaToTarefaPosicaoResponse) {
        return new AtualizarPosicoesTarefaDefault(tarefaRepository, tarefaToTarefaPosicaoResponse);
    }

    @Bean
    IncluirComentarioTarefa incluirComentarioTarefa(TarefaRepository tarefaRepository,
                                                    ComentarioRepository comentarioRepository,
                                                    UsuarioRepository usuarioRepository,
                                                    TarefaToTarefaResponse tarefaToTarefaResponse) {
        return new IncluirComentarioTarefaDefault(tarefaRepository, comentarioRepository,
                usuarioRepository, tarefaToTarefaResponse);
    }

    @Bean
    BuscarAuditoriaTarefa buscarAuditoriaTarefa(TarefaAudRepository tarefaAudRepository,
                                                TarefaAudToTarefaAudResponse tarefaAudToTarefaAudResponse) {
        return new BuscarAuditoriaTarefaDefault(tarefaAudRepository, tarefaAudToTarefaAudResponse);
    }

    @Bean
    DeletarTarefa deletarTarefa(TarefaRepository tarefaRepository, ComentarioRepository comentarioRepository) {
        return new DeletarTarefaDefault(tarefaRepository, comentarioRepository);
    }

    @Bean
    BuscarUsuarios buscarUsuarios(UsuarioRepository usuarioRepository,
                                  UsuarioToUsuarioResponse usuarioToUsuarioResponse) {
        return new BuscarUsuariosDefault(usuarioRepository, usuarioToUsuarioResponse);
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
                                              AtribuirRoleUsuarioKeycloak atribuirRoleUsuarioKeycloak,
                                              AtribuirAttributesUsuarioKeycloak atribuirAttributesUsuarioKeycloak,
                                              ObterToken obterToken,
                                              HttpService httpService) {
        return new CriarUsuarioKeycloakDefault(keycloakProperties, atribuirRoleUsuarioKeycloak,
                atribuirAttributesUsuarioKeycloak, obterToken, httpService);
    }

    @Bean
    AtribuirRoleUsuarioKeycloak atribuirRoleUsuarioKeycloak(KeycloakProperties keycloakProperties,
                                                            ObterToken obterToken,
                                                            HttpService httpService) {
        return new AtribuirRoleUsuarioKeycloakDefault(keycloakProperties, obterToken, httpService);
    }

    @Bean
    AtribuirAttributesUsuarioKeycloak atribuirAttributesUsuarioKeycloak(KeycloakProperties keycloakProperties,
                                                                      ObterToken obterToken,
                                                                      HttpService httpService) {
        return new AtribuirAttributesUsuarioKeycloakDefault(keycloakProperties, obterToken, httpService);
    }

    @Bean
    AtualizarUsuario atualizarUsuario(AtualizarUsuarioKeycloak atualizarUsuarioKeycloak,
                                      UsuarioRepository usuarioRepository,
                                      UsuarioToUsuarioResponse usuarioToUsuarioResponse) {
        return new AtualizarUsuarioDefault(atualizarUsuarioKeycloak, usuarioRepository, usuarioToUsuarioResponse);
    }

    @Bean
    AtualizarUsuarioKeycloak atualizarUsuarioKeycloak(KeycloakProperties keycloakProperties,
                                                      ObterToken obterToken,
                                                      HttpService httpService) {
        return new AtualizarUsuarioKeycloakDefault(keycloakProperties, obterToken, httpService);
    }

    @Bean
    AtualizarFotoUsuario atualizarFotoUsuario(UsuarioRepository usuarioRepository,
                                              UsuarioToUsuarioResponse usuarioToUsuarioResponse) {
        return new AtualizarFotoUsuarioDefault(usuarioRepository, usuarioToUsuarioResponse);
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

    @Bean
    KeycloakLoggedUserData keycloakLoggedUserData() {
        return new KeycloakLoggedUserDataDefault();
    }

    @Bean
    public CustomRevisionListener customRevisionListener(KeycloakLoggedUserData keycloakLoggedUserData) {
        return new CustomRevisionListener(keycloakLoggedUserData);
    }

    @Bean
    TarefaToTarefaResponse tarefaToTarefaResponse(
            ProjetoToProjetoResponse projetoToProjetoResponse,
            UsuarioToUsuarioResponse usuarioToUsuarioResponse,
            ClassificacaoToClassificacaoResponse classificacaoToClassificacaoResponse,
            ComentarioRepository comentarioRepository,
            ComentarioToComentarioResponse comentarioToComentarioResponse) {
        return new TarefaToTarefaResponseDefault(projetoToProjetoResponse, usuarioToUsuarioResponse,
                classificacaoToClassificacaoResponse, comentarioRepository,
                comentarioToComentarioResponse);
    }

    @Bean
    ComentarioToComentarioResponse comentarioToComentarioResponse() {
        return new ComentarioToComentarioResponseDefault();
    }

    @Bean
    TarefaAudToTarefaAudResponse tarefaAudToTarefaAudResponse(
            CustomRevisionEntityToCustomRevisionEntityResponse customRevisionEntityToCustomRevisionEntityResponse) {
        return new TarefaAudToTarefaAudResponseDefault(
                customRevisionEntityToCustomRevisionEntityResponse
        );
    }

    @Bean
    CustomRevisionEntityToCustomRevisionEntityResponse customRevisionEntityToCustomRevisionEntityResponse(
            UsuarioRepository usuarioRepository
    ) {
        return new CustomRevisionEntityToCustomRevisionEntityResponseDefault(
                usuarioRepository
        );
    }

    @Bean
    TarefaToTarefaPosicaoResponse tarefaToTarefaPosicaoResponse() {
        return new TarefaToTarefaPosicaoResponseDefault();
    }

    @Bean
    ProjetoToProjetoResponse projetoToProjetoResponse(
            OrganizacaoToOrganizacaoResponse organizacaoToOrganizacaoResponse) {
        return new ProjetoToProjetoResponseDefault(organizacaoToOrganizacaoResponse);
    }

    @Bean
    OrganizacaoToOrganizacaoResponse organizacaoToOrganizacaoResponse() {
        return new OrganizacaoToOrganizacaoResponseDefault();
    }

    @Bean
    UsuarioToUsuarioResponse usuarioToUsuarioResponse() {
        return new UsuarioToUsuarioResponseDefault();
    }

    @Bean
    ClassificacaoToClassificacaoResponse classificacaoToClassificacaoResponse(
            SubClassificacaoToSubClassificacaoResponse subClassificacaoToSubClassificacaoResponse) {
        return new ClassificacaoToClassificacaoResponseDefault(subClassificacaoToSubClassificacaoResponse);
    }

    @Bean
    SubClassificacaoToSubClassificacaoResponse subClassificacaoToSubClassificacaoResponse() {
        return new SubClassificacaoToSubClassificacaoResponseDefault();
    }

    @Bean
    CriarDerivado criarDerivado(ProjetoRepository projetoRepository, DerivadoRepository derivadoRepository,
                                DerivadoToDerivadoResponse derivadoToDerivadoResponse) {
        return new CriarDerivadoDefault(projetoRepository, derivadoRepository, derivadoToDerivadoResponse);
    }

    @Bean
    BuscarDerivados buscarDerivados(DerivadoRepository derivadoRepository,
                                    DerivadoToDerivadoResponse derivadoToDerivadoResponse) {
        return new BuscarDerivadosDefault(derivadoRepository, derivadoToDerivadoResponse);
    }

    @Bean
    DeletarDerivado deletarDerivado(DerivadoRepository derivadoRepository) {
        return new DeletarDerivadoDefault(derivadoRepository);
    }

    @Bean
    DerivadoToDerivadoResponse derivadoToDerivadoResponse(
            ProjetoToProjetoResponse projetoToProjetoResponse) {
        return new DerivadoToDerivadoResponseDefault(projetoToProjetoResponse);
    }

    @Bean
    BuscarDerivadosTarefaMatriz buscarDerivadosTarefaMatriz(DerivadoTarefaMatrizRepository
                                                            derivadoTarefaMatrizRepository,
                                                            DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse
                                                            derivadoTarefaMatrizToDerivadoTarefaMatrizResponse) {
        return new BuscarDerivadosTarefaMatrizDefault(derivadoTarefaMatrizRepository,
                derivadoTarefaMatrizToDerivadoTarefaMatrizResponse);
    }

    @Bean
    CriarDerivadoTarefaMatriz criarDerivadoTarefaMatriz(DerivadoRepository derivadoRepository,
                                                        TarefaRepository tarefaRepository,
                                                        ProjetoRepository projetoRepository,
                                                        DerivadoTarefaMatrizRepository derivadoTarefaMatrizRepository,
                                                        DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse
                                                        derivadoTarefaMatrizToDerivadoTarefaMatrizResponse) {
        return new CriarDerivadoTarefaMatrizDefault(derivadoRepository, tarefaRepository,
                projetoRepository, derivadoTarefaMatrizRepository,
                derivadoTarefaMatrizToDerivadoTarefaMatrizResponse);
    }

    @Bean
    DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse derivadoTarefaMatrizToDerivadoTarefaMatrizResponse(
            DerivadoToDerivadoResponse derivadoToDerivadoResponse,
            TarefaToTarefaResponse tarefaToTarefaResponse,
            ProjetoToProjetoResponse projetoToProjetoResponse) {
        return new DerivadoTarefaMatrizToDerivadoTarefaMatrizResponseDefault(derivadoToDerivadoResponse,
                tarefaToTarefaResponse, projetoToProjetoResponse);
    }

    @Bean
    CriarDerivadoLista criarDerivadoLista(ProjetoRepository projetoRepository,
                                          DerivadoRepository derivadoRepository,
                                          DerivadoToDerivadoResponse derivadoToDerivadoResponse) {
        return new CriarDerivadoListaDefault(projetoRepository, derivadoRepository, derivadoToDerivadoResponse);
    }

    @Bean
    CriarDerivadoTarefaMatrizLista criarDerivadoTarefaMatrizLista(DerivadoRepository derivadoRepository,
                                                                  TarefaRepository tarefaRepository,
                                                                  ProjetoRepository projetoRepository,
                                                                  DerivadoTarefaMatrizRepository derivadoTarefaMatrizRepository,
                                                                  DerivadoTarefaMatrizToDerivadoTarefaMatrizResponse
                                                                  derivadoTarefaMatrizToDerivadoTarefaMatrizResponse) {
        return new CriarDerivadoTarefaMatrizListaDefault(derivadoRepository, tarefaRepository,
                projetoRepository, derivadoTarefaMatrizRepository,
                derivadoTarefaMatrizToDerivadoTarefaMatrizResponse);
    }

    @Bean
    BuscarDashboard buscarDashboard(TarefaRepository tarefaRepository) {
        return new BuscarDashboardDefault(tarefaRepository);
    }

    @Bean
    BuscarMetricas buscarMetricas(TarefaRepository tarefaRepository) {
        return new BuscarMetricasDefault(tarefaRepository);
    }

    @Bean
    BuscarSolicitacoes buscarSolicitacoes(SolicitacaoRepository solicitacaoRepository, UsuarioRepository usuarioRepository,
                                          SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse) {
        return new BuscarSolicitacoesDefault(solicitacaoRepository, usuarioRepository, solicitacaoToSolicitacaoResponse);
    }

    @Bean
    SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse(
            ProjetoToProjetoResponse projetoToProjetoResponse,
            UsuarioToUsuarioResponse usuarioToUsuarioResponse,
            ClassificacaoToClassificacaoResponse classificacaoToClassificacaoResponse) {
        return new SolicitacaoToSolicitacaoResponseDefault(projetoToProjetoResponse, usuarioToUsuarioResponse,
                classificacaoToClassificacaoResponse);
    }

    @Bean
    SolicitacaoToSolicitacaoPosicaoResponse solicitacaoToSolicitacaoPosicaoResponse() {
        return new SolicitacaoToSolicitacaoPosicaoResponseDefault();
    }

    @Bean
    CriarSolicitacao criarSolicitacao(SolicitacaoRepository solicitacaoRepository, ProjetoRepository projetoRepository,
                                      UsuarioRepository usuarioRepository, ClassificacaoRepository classificacaoRepository,
                                      SubClassificacaoRepository subClassificacaoRepository,
                                      SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse) {
        return new CriarSolicitacaoDefault(solicitacaoRepository, projetoRepository,
                usuarioRepository, classificacaoRepository, subClassificacaoRepository,
                solicitacaoToSolicitacaoResponse);
    }

    @Bean
    AtualizarSolicitacao atualizarSolicitacao(SolicitacaoRepository solicitacaoRepository, ProjetoRepository projetoRepository,
                                              UsuarioRepository usuarioRepository, ClassificacaoRepository classificacaoRepository,
                                              SubClassificacaoRepository subClassificacaoRepository,
                                              SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse) {
        return new AtualizarSolicitacaoDefault(solicitacaoRepository, projetoRepository,
                usuarioRepository, classificacaoRepository, subClassificacaoRepository,
                solicitacaoToSolicitacaoResponse);
    }

    @Bean
    AtualizarStatusSolicitacao atualizarStatusSolicitacao(
            SolicitacaoRepository solicitacaoRepository,
            SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse
    ) {
        return new AtualizarStatusSolicitacaoDefault(solicitacaoRepository, solicitacaoToSolicitacaoResponse);
    }

    @Bean
    AtualizarFeedbackSolicitacao atualizarFeedbackSolicitacao(
            SolicitacaoRepository solicitacaoRepository,
            SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse
    ) {
        return new AtualizarFeedbackSolicitacaoDefault(solicitacaoRepository, solicitacaoToSolicitacaoResponse);
    }

    @Bean
    AtualizarFechadoSolicitacao atualizarFechadoSolicitacao(
            SolicitacaoRepository solicitacaoRepository,
            SolicitacaoToSolicitacaoResponse solicitacaoToSolicitacaoResponse
    ) {
        return new AtualizarFechadoSolicitacaoDefault(solicitacaoRepository, solicitacaoToSolicitacaoResponse);
    }

    @Bean
    AtualizarPosicoesSolicitacao atualizarPosicoesSolicitacao(
            SolicitacaoRepository solicitacaoRepository,
            SolicitacaoToSolicitacaoPosicaoResponse solicitacaoToSolicitacaoPosicaoResponse
    ) {
        return new AtualizarPosicoesSolicitacaoDefault(solicitacaoRepository, solicitacaoToSolicitacaoPosicaoResponse);
    }

    @Bean
    DeletarSolicitacao deletarSolicitacao(
            SolicitacaoRepository solicitacaoRepository
    ) {
        return new DeletarSolicitacaoDefault(solicitacaoRepository);
    }

    @Bean
    AprovarSolicitacao aprovarSolicitacao(
            SolicitacaoRepository solicitacaoRepository,
            TarefaRepository tarefaRepository,
            TarefaToTarefaResponse tarefaToTarefaResponse
    ) {
        return new AprovarSolicitacaoDefault(solicitacaoRepository, tarefaRepository, tarefaToTarefaResponse);
    }
}