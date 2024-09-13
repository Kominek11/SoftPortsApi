package br.com.softports.core.internal.common.entity.audited;

import br.com.softports.core.api.properties.KeycloakLoggedUserData;
import br.com.softports.core.internal.properties.KeycloakLoggedUserDataDefault;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.RevisionListener;

import java.util.UUID;

@RequiredArgsConstructor
public class CustomRevisionListener implements RevisionListener {

    private final KeycloakLoggedUserData keycloakLoggedUserData;

    public CustomRevisionListener() {
        this.keycloakLoggedUserData = new KeycloakLoggedUserDataDefault();
    }

    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
        String keycloakId = keycloakLoggedUserData.getCurrentUserId();
        customRevisionEntity.setKeycloakId(UUID.fromString(keycloakId));
        customRevisionEntity.setRevtstmp(System.currentTimeMillis());
    }
}
