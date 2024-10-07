package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "solicitacao")
@NoArgsConstructor
@Setter
@Getter
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_id")
    @SequenceGenerator(name = "solicitacao_id", sequenceName = "solicitacao_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    String titulo;

    @Column
    String descricao;

    @Column
    String so;

    @Column
    byte[][] screenshots;

    @Column
    String caminho;

    @Column
    Date dataFechamento;

    @Column
    Date dataCriacao;

    @Column
    Date dataEstimada;

    @Column
    Long status;

    @Column
    Boolean fechada;

    @Column
    Long posicao;

    @Column
    Long prioridade;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    Projeto projeto;

    @Column
    String feedback;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_solicitacao",
            joinColumns = @JoinColumn(name = "solicitacao_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    Set<Usuario> usuarios;

    @OneToOne
    @JoinColumn(name = "classificacao_id")
    Classificacao classificacao;
}
