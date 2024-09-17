package br.com.softports.core.internal.common.entity.audited;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RevisionType;

import java.util.Date;

@Entity
@Table(name = "tarefa_aud")
@NoArgsConstructor
@Setter
@Getter
public class TarefaAud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private String so;

    @Column
    private byte[][] screenshots;

    @Column
    private String caminho;

    @Column(name = "data_fechamento")
    private Date dataFechamento;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_estimada")
    private Date dataEstimada;

    @Column
    private Long status;

    @Column
    private Boolean fechada;

    @Column
    private Long posicao;

    @Column
    private Long prioridade;

    @Column(name = "projeto_id")
    private Long projetoId;

    @Column
    private String feedback;

    @Column(name = "classificacao_id")
    private Long classificacaoId;

    @Column(name = "rev")
    private Long revision;

    @Column(name = "revtype")
    private RevisionType revisionType;

    @Column(name = "titulo_mod", columnDefinition = "boolean default false")
    private Boolean titulo_Mod;

    @Column(name = "descricao_mod", columnDefinition = "boolean default false")
    private Boolean descricao_Mod;

    @Column(name = "so_mod", columnDefinition = "boolean default false")
    private Boolean so_Mod;

    @Column(name = "screenshots_mod", columnDefinition = "boolean default false")
    private Boolean screenshots_Mod;

    @Column(name = "caminho_mod", columnDefinition = "boolean default false")
    private Boolean caminho_Mod;

    @Column()
    private Boolean dataFechamento_Mod;

    @Column()
    private Boolean dataCriacao_Mod;

    @Column()
    private Boolean dataEstimada_Mod;

    @Column(name = "status_mod", columnDefinition = "boolean default false")
    private Boolean status_Mod;

    @Column(name = "fechada_mod", columnDefinition = "boolean default false")
    private Boolean fechada_Mod;

    @Column(name = "posicao_mod", columnDefinition = "boolean default false")
    private Boolean posicao_Mod;

    @Column(name = "prioridade_mod", columnDefinition = "boolean default false")
    private Boolean prioridade_Mod;

    @Column(name = "projeto_mod", columnDefinition = "boolean default false")
    private Boolean projeto_Mod;

    @Column(name = "feedback_mod", columnDefinition = "boolean default false")
    private Boolean feedback_Mod;

    @Column()
    private Boolean classificacaoId_Mod;

    @Column(name = "usuarios_mod", columnDefinition = "boolean default false")
    private Boolean usuarios_Mod;

    @Column(name = "classificacao_mod", columnDefinition = "boolean default false")
    private Boolean classificacao_Mod;

    @ManyToOne
    @JoinColumn(name = "rev", insertable = false, updatable = false)
    private CustomRevisionEntity customRevisionEntity;
}