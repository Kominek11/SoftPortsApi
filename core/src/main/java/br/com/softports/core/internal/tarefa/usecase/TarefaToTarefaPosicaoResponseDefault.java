package br.com.softports.core.internal.tarefa.usecase;

import br.com.softports.core.api.classificacao.usecase.ClassificacaoToClassificacaoResponse;
import br.com.softports.core.api.projeto.usecase.ProjetoToProjetoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaPosicaoResponse;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaPosicaoResponse;
import br.com.softports.core.api.tarefa.usecase.TarefaToTarefaResponse;
import br.com.softports.core.api.usuario.usecase.UsuarioToUsuarioResponse;
import br.com.softports.core.internal.common.entity.Tarefa;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TarefaToTarefaPosicaoResponseDefault implements TarefaToTarefaPosicaoResponse {

    @Override
    public TarefaPosicaoResponse executar(Tarefa tarefa) {
        return TarefaPosicaoResponse.builder()
                .id(tarefa.getId())
                .status(tarefa.getStatus())
                .posicao(tarefa.getPosicao())
                .build();
    }
}
