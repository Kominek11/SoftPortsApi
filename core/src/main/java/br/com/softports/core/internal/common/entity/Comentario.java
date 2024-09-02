package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "comentario")
@NoArgsConstructor
@Setter
@Getter
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentario_id")
    @SequenceGenerator(name = "comentario_id", sequenceName = "comentario_id_seq", allocationSize = 1)
    Long id;

    @Column
    String conteudo;

    @ManyToOne
    @JoinColumn(name = "tarefa_id", nullable = false)
    Tarefa tarefa;

    @Column
    LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    Usuario usuario;
}
