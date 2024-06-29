package br.com.softports.application.resources;

import br.com.softports.application.IntegrationTest;
import br.com.egoverne.iafis.application.resources.dto.ExecutarAcaoRequest;
import br.com.softports.core.api.inconsistencia.repository.InconsistenciaRepository;
import br.com.softports.core.internal.inconsistencia.expression.InconsistenciaExpressions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AcaoResourceIntegrationTest extends IntegrationTest {

    @Autowired
    private InconsistenciaRepository inconsistenciaRepository;

    @Test
    @Sql({"/db/scripts/base_padrao.sql"})
    @Transactional
    void deveGerarAcaoNasDuasOcorrencias() throws Exception {
        List<Long> ocorrenciaIds = List.of(1L, 2L);
        ExecutarAcaoRequest executarAcaoRequest =
                new ExecutarAcaoRequest(1L, ocorrenciaIds, null);
        String requestBody = new ObjectMapper().writeValueAsString(executarAcaoRequest);
        mvc.perform(post("/acao")
                        .content(requestBody)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        List<Inconsistencia> inconsistencias = inconsistenciaRepository.buscarTodos(InconsistenciaExpressions.inconsistenciaId(ocorrenciaIds));
        // TODO Débito técnico (IAFIS-32)
        // assertEquals(1, ocorrencias.get(0).getAcoes().size());
        // assertEquals(1, ocorrencias.get(1).getAcoes().size());
    }

    @Test
    @Sql({"/db/scripts/base_padrao.sql"})
    @Sql({"/db/scripts/ocorrencia_sem_email.sql"})
    @Transactional
    void deveGerarAcaoApenasParaUmaDasOcorrencias() throws Exception {
        List<Long> ocorrenciaIds = List.of(3L, 4L);
        ExecutarAcaoRequest executarAcaoRequest =
                new ExecutarAcaoRequest(1L, ocorrenciaIds, null);
        String requestBody = new ObjectMapper().writeValueAsString(executarAcaoRequest);
        mvc.perform(post("/acao")
                        .content(requestBody)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        List<Inconsistencia> inconsistencias = inconsistenciaRepository.buscarTodos(InconsistenciaExpressions.inconsistenciaId(ocorrenciaIds));
        // TODO Débito técnico (IAFIS-32)
        // assertEquals(1, ocorrencias.get(0).getAcoes().size());
        // assertEquals(0, ocorrencias.get(1).getAcoes().size());
    }

    @Test
    @Sql({"/db/scripts/base_padrao.sql"})
    @Transactional
    void deveRetornarTodosOsTiposDeAcao() throws Exception {
        mvc.perform(get("/acao/tipo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        // TODO Débito técnico (IAFIS-32)
    }
}