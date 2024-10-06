package br.com.softports.core.internal.derivado.usecase;

import br.com.softports.core.api.derivado.repository.DerivadoRepository;
import br.com.softports.core.api.derivado.usecase.DeletarDerivado;
import br.com.softports.core.api.organizacao.repository.OrganizacaoRepository;
import br.com.softports.core.api.organizacao.usecase.DeletarOrganizacao;
import br.com.softports.core.internal.common.entity.Derivado;
import br.com.softports.core.internal.common.entity.Organizacao;
import br.com.softports.core.internal.derivado.expression.DerivadoExpressions;
import br.com.softports.core.internal.organizacao.expression.OrganizacaoExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletarDerivadoDefault implements DeletarDerivado {

    private final DerivadoRepository derivadoRepository;

    @Override
    public void executar(Long id) {
        BooleanBuilder filtro = new BooleanBuilder().and(DerivadoExpressions.id(id));
        Derivado derivado = derivadoRepository.buscar(filtro).orElseThrow();
        derivadoRepository.apagar(derivado);
    }
}