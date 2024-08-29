package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "classificacao")
@NoArgsConstructor
@Setter
@Getter
public class Classificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classificacao_id")
    @SequenceGenerator(name = "classificacao_id", sequenceName = "classificacao_id_seq", allocationSize = 1)
    Long id;

    @Column
    String nome;

    @OneToOne
    @JoinColumn(name = "subclassificacao_id")
    SubClassificacao subClassificacao;
}
