package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.Set;

@Audited(withModifiedFlag = true)
@Entity
@Table(name = "subclassificacao")
@NoArgsConstructor
@Setter
@Getter
public class SubClassificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subclassificacao_id")
    @SequenceGenerator(name = "subclassificacao_id", sequenceName = "subclassificacao_id_seq", allocationSize = 1)
    Long id;

    @Column
    String nome;
}
