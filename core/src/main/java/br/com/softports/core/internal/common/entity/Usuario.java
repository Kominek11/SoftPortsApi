package br.com.softports.core.internal.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.sql.Blob;
import java.util.Set;
import java.util.UUID;

@Audited(withModifiedFlag = true)
@Entity
@Table(name = "usuario")
@NoArgsConstructor
@Setter
@Getter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id")
    @SequenceGenerator(name = "usuario_id", sequenceName = "usuario_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    String nome;

    @Column
    String email;

    @Column
    UUID keycloakId;

    @Column
    String roles;

    @Column
    byte[] foto;

    @ManyToMany(mappedBy = "usuarios")
    Set<Tarefa> tarefas;

    @ManyToMany(mappedBy = "usuarios")
    Set<Projeto> projetos;

    @OneToMany(mappedBy = "usuario")
    Set<Comentario> comentarios;
}
