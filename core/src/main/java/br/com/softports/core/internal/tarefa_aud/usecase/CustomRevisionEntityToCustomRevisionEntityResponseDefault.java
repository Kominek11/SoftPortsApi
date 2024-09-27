package br.com.softports.core.internal.tarefa_aud.usecase;

import br.com.softports.core.api.tarefa_aud.dto.CustomRevisionEntityResponse;
import br.com.softports.core.api.tarefa_aud.dto.TarefaAudResponse;
import br.com.softports.core.api.tarefa_aud.usecase.CustomRevisionEntityToCustomRevisionEntityResponse;
import br.com.softports.core.api.tarefa_aud.usecase.TarefaAudToTarefaAudResponse;
import br.com.softports.core.api.usuario.repository.UsuarioRepository;
import br.com.softports.core.internal.common.entity.Usuario;
import br.com.softports.core.internal.common.entity.audited.CustomRevisionEntity;
import br.com.softports.core.internal.common.entity.audited.TarefaAud;
import br.com.softports.core.internal.usuario.expression.UsuarioExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomRevisionEntityToCustomRevisionEntityResponseDefault implements
        CustomRevisionEntityToCustomRevisionEntityResponse {
    private final UsuarioRepository usuarioRepository;

    @Override
    public CustomRevisionEntityResponse executar(CustomRevisionEntity customRevisionEntity) {
        BooleanBuilder filtro = new BooleanBuilder()
                .and(UsuarioExpressions.keycloakId(customRevisionEntity.getKeycloakId()));
        Usuario usuario = usuarioRepository.buscar(filtro).orElseThrow();
        return CustomRevisionEntityResponse.builder()
                .id(customRevisionEntity.getId())
                .revtstmp(customRevisionEntity.getRevtstmp())
                .nome(usuario.getNome())
                .build();
    }
}
