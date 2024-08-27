package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tarefa")
@NoArgsConstructor
@Setter
@Getter
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarefa_id")
    @SequenceGenerator(name = "tarefa_id", sequenceName = "tarefa_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    String titulo;

    @Column
    String descricao;

    @Column
    String so;

    @Column
    byte[] screenshots;

    @Column
    String caminho;

    @Column
    Date dataFechamento;

    @Column
    Date dataCriacao;

    @Column
    Date dataEstimada;

    @Column
    Long prioridade;

    @Column
    Long classificacao;

    @Column
    Long status;

    @Column
    Boolean fechada;

    @Column
    Long posicao;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    Projeto projeto;

    @Column
    String feedback;

    @ManyToMany
    @JoinTable(
            name = "usuario_tarefa",
            joinColumns = @JoinColumn(name = "tarefa_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    Set<Usuario> usuarios;
}
