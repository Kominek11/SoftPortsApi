package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projeto")
@NoArgsConstructor
@Setter
@Getter
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projeto_id")
    @SequenceGenerator(name = "projeto_id", sequenceName = "projeto_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    String nome;


}
