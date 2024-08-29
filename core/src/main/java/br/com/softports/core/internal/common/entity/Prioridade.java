package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "prioridade")
@NoArgsConstructor
@Setter
@Getter
public class Prioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prioridade_id")
    @SequenceGenerator(name = "prioridade_id", sequenceName = "prioridade_id_seq", allocationSize = 1)
    Long id;

    @Column
    String nome;

    @ManyToMany(mappedBy = "prioridades")
    Set<Tarefa> tarefas;
}
