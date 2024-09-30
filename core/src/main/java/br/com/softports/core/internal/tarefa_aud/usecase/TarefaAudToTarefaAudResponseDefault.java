package br.com.softports.core.internal.tarefa_aud.usecase;

import br.com.softports.core.api.classificacao.usecase.ClassificacaoToClassificacaoResponse;
import br.com.softports.core.api.projeto.usecase.ProjetoToProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
import br.com.softports.core.api.tarefa_aud.dto.TarefaAudResponse;
import br.com.softports.core.api.tarefa_aud.usecase.CustomRevisionEntityToCustomRevisionEntityResponse;
import br.com.softports.core.api.tarefa_aud.usecase.TarefaAudToTarefaAudResponse;
import br.com.softports.core.api.usuario.usecase.UsuarioToUsuarioResponse;
import br.com.softports.core.internal.common.entity.Tarefa;
import br.com.softports.core.internal.common.entity.audited.TarefaAud;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TarefaAudToTarefaAudResponseDefault implements TarefaAudToTarefaAudResponse {

    private final CustomRevisionEntityToCustomRevisionEntityResponse customRevisionEntityToCustomRevisionEntityResponse;

    @Override
    public TarefaAudResponse executar(TarefaAud tarefaAud) {
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
                .revtype(tarefaAud.getRevtype())
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
                .customRevisionEntityResponse(
                        customRevisionEntityToCustomRevisionEntityResponse
                                .executar(tarefaAud.getCustomRevisionEntity())
                )
                .build();
    }
}
