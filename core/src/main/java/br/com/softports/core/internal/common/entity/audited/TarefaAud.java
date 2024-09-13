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

    @Enumerated(EnumType.STRING)
    @Column(name = "revtype")
    private RevisionType revisionType;

    @Column(name = "titulo_mod", columnDefinition = "boolean default false")
    private Boolean tituloMod;

    @Column(name = "descricao_mod", columnDefinition = "boolean default false")
    private Boolean descricaoMod;

    @Column(name = "so_mod", columnDefinition = "boolean default false")
    private Boolean soMod;

    @Column(name = "screenshots_mod", columnDefinition = "boolean default false")
    private Boolean screenshotsMod;

    @Column(name = "caminho_mod", columnDefinition = "boolean default false")
    private Boolean caminhoMod;

    @Column(name = "data_fechamento_mod", columnDefinition = "boolean default false")
    private Boolean data_fechamento_mod;

    @Column(name = "data_criacao_mod", columnDefinition = "boolean default false")
    private Boolean data_criacao_mod;

    @Column(name = "data_estimada_mod", columnDefinition = "boolean default false")
    private Boolean data_estimada_mod;

    @Column(name = "status_mod", columnDefinition = "boolean default false")
    private Boolean statusMod;

    @Column(name = "fechada_mod", columnDefinition = "boolean default false")
    private Boolean fechadaMod;

    @Column(name = "posicao_mod", columnDefinition = "boolean default false")
    private Boolean posicaoMod;

    @Column(name = "prioridade_mod", columnDefinition = "boolean default false")
    private Boolean prioridadeMod;

    @Column(name = "projeto_mod", columnDefinition = "boolean default false")
    private Boolean projetoMod;

    @Column(name = "feedback_mod", columnDefinition = "boolean default false")
    private Boolean feedbackMod;

    @Column(name = "classificacao_id_mod", columnDefinition = "boolean default false")
    private Boolean classificacaoIdMod;

    @Column(name = "usuarios_mod", columnDefinition = "boolean default false")
    private Boolean usuariosMod;

    @Column(name = "classificacao_mod", columnDefinition = "boolean default false")
    private Boolean classificacaoMod;

    @ManyToOne
    @JoinColumn(name = "rev", insertable = false, updatable = false)
    private CustomRevisionEntity customRevisionEntity;
}