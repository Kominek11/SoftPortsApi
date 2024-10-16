package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.Set;

@Audited(withModifiedFlag = true)
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

    @ManyToOne
    @JoinColumn(name = "organizacao_id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    Organizacao organizacao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_projeto",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    Set<Usuario> usuarios;
}
