package br.com.softports.core.api.tarefa_aud.dto;

import lombok.Builder;
import org.hibernate.envers.RevisionType;

import java.util.Date;

@Builder
public record TarefaAudResponse(
        Long id,
        String titulo,
        String descricao,
        String so,
        byte[][] screenshots,
        String caminho,
        Date dataFechamento,
        Date dataCriacao,
        Date dataEstimada,
        Long status,
        Boolean fechada,
        Long posicao,
        Long prioridade,
        Long projetoId,
        String feedback,
        Long classificacaoId,
        Long revision,
        byte revtype,
        Boolean tituloModificado,
        Boolean descricaoModificado,
        Boolean soModificado,
        Boolean screenshotsModificado,
        Boolean caminhoModificado,
        Boolean dataFechamentoModificado,
        Boolean dataCriacaoModificado,
        Boolean dataEstimadaModificado,
        Boolean statusModificado,
        Boolean fechadaModificado,
        Boolean posicaoModificado,
        Boolean prioridadeModificado,
        Boolean projetoModificado,
        Boolean feedbackModificado,
        Boolean classificacaoIdModificado,
        Boolean usuariosModificado,
        Boolean classificacaoModificado,
        CustomRevisionEntityResponse customRevisionEntityResponse
) {}