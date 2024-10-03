package br.com.softports.core.internal.derivado.usecase;

import br.com.softports.core.api.derivado.dto.DerivadoListaResponse;
import br.com.softports.core.api.derivado.dto.DerivadoResponse;
import br.com.softports.core.api.derivado.repository.DerivadoRepository;
import br.com.softports.core.api.derivado.usecase.CriarDerivadoLista;
import br.com.softports.core.api.derivado.usecase.DerivadoToDerivadoResponse;
import br.com.softports.core.api.projeto.repository.ProjetoRepository;
import br.com.softports.core.internal.common.entity.Derivado;
import br.com.softports.core.internal.common.entity.Projeto;
import br.com.softports.core.internal.projeto.expression.ProjetoExpressions;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CriarDerivadoListaDefault implements CriarDerivadoLista {

    private final ProjetoRepository projetoRepository;
    private final DerivadoRepository derivadoRepository;
    private final DerivadoToDerivadoResponse derivadoToDerivadoResponse;

    @Override
    public List<DerivadoResponse> executar(List<DerivadoListaResponse> derivadoListResponseLista) {
        List<Derivado> derivados = new ArrayList<>();
        derivadoListResponseLista.forEach(derivadoResponse -> {
            BooleanBuilder filtroProjeto = new BooleanBuilder()
                    .and(ProjetoExpressions.id(derivadoResponse.projetoId()));
            Projeto projeto = projetoRepository.buscar(filtroProjeto).orElseThrow();
            Derivado derivado = new Derivado();
            derivado.setNome(derivadoResponse.nome());
            derivado.setProjeto(projeto);
            derivados.add(derivado);
        });
        derivadoRepository.salvarTodos(derivados);
        return derivados.stream()
                .map(derivadoToDerivadoResponse::executar)
                .toList();
    }
}