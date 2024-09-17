package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.dto.ClassificacaoResponse;
import br.com.softports.core.api.comentario.dto.ComentarioResponse;
import br.com.softports.core.api.comentario.repository.ComentarioRepository;
import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.organizacao.dto.OrganizacaoResponse;
import br.com.softports.core.api.projeto.dto.ProjetoResponse;
import br.com.softports.core.api.subclassificacao.dto.SubClassificacaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.repository.TarefaRepository;
import br.com.softports.core.api.tarefa.usecase.BuscarAuditoriaTarefa;
import br.com.softports.core.api.tarefa_aud.dto.CustomRevisionEntityResponse;
import br.com.softports.core.api.tarefa_aud.dto.TarefaAudResponse;
import br.com.softports.core.api.tarefa_aud.repository.TarefaAudRepository;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.comentario.expression.ComentarioExpressions;
import br.com.softports.core.internal.common.entity.*;
import br.com.softports.core.internal.common.entity.audited.CustomRevisionEntity;
import br.com.softports.core.internal.common.entity.audited.TarefaAud;
import br.com.softports.core.internal.tarefa.expression.TarefaExpressions;
import br.com.softports.core.internal.tarefa_aud.expression.TarefaAudExpressions;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class BuscarAuditoriaTarefaDefault implements BuscarAuditoriaTarefa {

    private final TarefaAudRepository tarefaAudRepository;

    @Override
    public Pagina<TarefaAudResponse> executar(Long id,
                                              Integer tamanhoPagina,
                                              Integer numeroPagina,
                                              String ordenadoPor,
                                              String direcao) {
        BooleanBuilder filtroTarefaAud = new BooleanBuilder().and(TarefaAudExpressions.id(id));
        List<TarefaAudResponse> tarefasAud = tarefaAudRepository
                .buscarTodos(
                        filtroTarefaAud,
                        tamanhoPagina,
                        numeroPagina,
                        ordenadoPor,
                        direcao)
                .stream()
                .map(this::gerarTarefaAudResponse)
                .toList();
        return paginar(tamanhoPagina, numeroPagina, tarefasAud, filtroTarefaAud);
    }

    private Pagina<TarefaAudResponse> paginar(Integer tamanhoPagina, Integer numeroPagina,
                                           List<TarefaAudResponse> tarefasAud, BooleanBuilder filtro) {
        Long tarefaAudQuantidade = tarefaAudRepository.contar(filtro);
        int quantidadePaginas = (int) Math.ceil((double) tarefaAudQuantidade / tamanhoPagina);
        return new Pagina<>(true, numeroPagina, quantidadePaginas,
                tamanhoPagina, tarefaAudQuantidade, tarefasAud);
    }

    private TarefaAudResponse gerarTarefaAudResponse(TarefaAud tarefaAud) {
        return TarefaAudResponse.builder()
                .id(tarefaAud.getId())
                .titulo(tarefaAud.getTitulo())
                .descricao(tarefaAud.getDescricao())
                .so(tarefaAud.getSo())
                .screenshots(tarefaAud.getScreenshots())
                .caminho(tarefaAud.getCaminho())
                .dataFechamento(tarefaAud.getDataFechamento())
                .dataCriacao(tarefaAud.getDataCriacao())
                .dataEstimada(tarefaAud.getDataEstimada())
                .status(tarefaAud.getStatus())
                .fechada(tarefaAud.getFechada())
                .posicao(tarefaAud.getPosicao())
                .prioridade(tarefaAud.getPrioridade())
                .projetoId(tarefaAud.getProjetoId())
                .feedback(tarefaAud.getFeedback())
                .classificacaoId(tarefaAud.getClassificacaoId())
                .revision(tarefaAud.getRevision())
                .revisionType(tarefaAud.getRevisionType().name())
                .tituloModificado(tarefaAud.getTitulo_Mod())
                .descricaoModificado(tarefaAud.getDescricao_Mod())
                .soModificado(tarefaAud.getSo_Mod())
                .screenshotsModificado(tarefaAud.getScreenshots_Mod())
                .caminhoModificado(tarefaAud.getCaminho_Mod())
                .dataFechamentoModificado(tarefaAud.getDataFechamento_Mod())
                .dataCriacaoModificado(tarefaAud.getDataCriacao_Mod())
                .dataEstimadaModificado(tarefaAud.getDataEstimada_Mod())
                .statusModificado(tarefaAud.getStatus_Mod())
                .fechadaModificado(tarefaAud.getFechada_Mod())
                .posicaoModificado(tarefaAud.getPosicao_Mod())
                .prioridadeModificado(tarefaAud.getPrioridade_Mod())
                .projetoModificado(tarefaAud.getProjeto_Mod())
                .feedbackModificado(tarefaAud.getFeedback_Mod())
                .classificacaoIdModificado(tarefaAud.getClassificacaoId_Mod())
                .usuariosModificado(tarefaAud.getUsuarios_Mod())
                .classificacaoModificado(tarefaAud.getClassificacao_Mod())
                .customRevisionEntityResponse(gerarCustomRevisionEntityResponse(tarefaAud.getCustomRevisionEntity()))
                .build();
    }

    private CustomRevisionEntityResponse gerarCustomRevisionEntityResponse(CustomRevisionEntity customRevisionEntity) {
        return CustomRevisionEntityResponse.builder()
                .id(customRevisionEntity.getId())
                .revtstmp(customRevisionEntity.getRevtstmp())
                .keycloakId(customRevisionEntity.getKeycloakId())
                .build();
    }

}