package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.Set;

@Entity
@Table(name = "derivado")
@NoArgsConstructor
@Setter
@Getter
public class Derivado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "derivado_id")
    @SequenceGenerator(name = "derivado_id", sequenceName = "derivado_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    String nome;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    Projeto projeto;

}
