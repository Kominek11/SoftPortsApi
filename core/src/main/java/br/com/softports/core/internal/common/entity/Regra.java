package br.com.softports.core.internal.common.entity;

import br.com.softports.core.internal.common.enumeration.RegraSituacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "regra")
@NoArgsConstructor
@Setter
@Getter
public class Regra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regra_id")
    @SequenceGenerator(name = "regra_id", sequenceName = "regra_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    RegraSituacao situacao;

    @Column(nullable = false)
    LocalDateTime dataInclusao;

    @Column
    String emailNotificacao;

    @Column
    LocalDateTime dataInativacao;
}
