package br.com.softports.application.resources.dto.derivado_tarefa_matriz;

public record CriarDerivadoTarefaMatrizRequest(
        Long derivadoId,
        Long tarefaId,
        Boolean valor,
        Long projetoId
) { }
