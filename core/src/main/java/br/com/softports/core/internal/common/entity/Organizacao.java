package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "organizacao")
@NoArgsConstructor
@Setter
@Getter
public class Organizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizacao_id")
    @SequenceGenerator(name = "organizacao_id", sequenceName = "organizacao_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    String nome;
}
