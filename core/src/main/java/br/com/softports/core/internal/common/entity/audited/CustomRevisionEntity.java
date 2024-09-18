package br.com.softports.core.internal.common.entity.audited;

import jakarta.persistence.*;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@RevisionEntity(CustomRevisionListener.class)
@Table(name = "revinfo")
public class CustomRevisionEntity extends DefaultRevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revinfo_id_seq")
    @SequenceGenerator(name = "revinfo_id_seq", sequenceName = "revinfo_id_seq")
    Integer id;

    @Column(name = "revtstmp", nullable = false)
    private Long revtstmp;

    @Column(name = "keycloak_id", nullable = false)
    private UUID keycloakId;

    public Long getRevtstmp() {
        return revtstmp;
    }

    public void setRevtstmp(Long revtstmp) {
        this.revtstmp = revtstmp;
    }

    public UUID getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(UUID keycloakId) {
        this.keycloakId = keycloakId;
    }
}