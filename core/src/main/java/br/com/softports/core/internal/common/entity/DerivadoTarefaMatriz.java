package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "derivado_tarefa_matriz")
@NoArgsConstructor
@Setter
@Getter
public class DerivadoTarefaMatriz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "derivado_tarefa_matriz_id_seq")
    @SequenceGenerator(
            name = "derivado_tarefa_matriz_id_seq", sequenceName = "derivado_tarefa_matriz_id_seq", allocationSize = 1
    )
    Long id;

    @ManyToOne
    @JoinColumn(name = "derivado_id", nullable = false)
    Derivado derivado;

    @ManyToOne
    @JoinColumn(name = "tarefa_id", nullable = false)
    Tarefa tarefa;

    @Column
    Boolean valor;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    Projeto projeto;
}