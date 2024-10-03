package br.com.softports.core.internal.derivado.usecase;

import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.api.derivado.repository.DerivadoRepository;
import br.com.softports.core.api.derivado.usecase.CriarDerivado;
import br.com.softports.core.api.derivado.usecase.DerivadoToDerivadoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.internal.common.entity.Derivado;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CriarDerivadoDefault implements CriarDerivado {

    private final ProjetoRepository projetoRepository;
    private final DerivadoRepository derivadoRepository;
    private final DerivadoToDerivadoResponse derivadoToDerivadoResponse;

    @Override
    public DerivadoResponse executar(String nome, Long projetoId) {
        BooleanBuilder filtroProjeto = new BooleanBuilder()
                .and(ProjetoExpressions.id(projetoId));
        Projeto projeto = projetoRepository.buscar(filtroProjeto).orElseThrow();
        Derivado derivado = new Derivado();
        derivado.setNome(nome);
        derivado.setProjeto(projeto);
        derivadoRepository.salvar(derivado);
        return derivadoToDerivadoResponse.executar(derivado);
    }
}