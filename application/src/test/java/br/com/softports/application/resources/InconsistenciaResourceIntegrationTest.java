package br.com.softports.application.resources;

import br.com.softports.application.IntegrationTest;
import br.com.softports.core.internal.common.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql({"/db/scripts/base_padrao.sql"})
class InconsistenciaResourceIntegrationTest extends IntegrationTest {

    @Test
    @Transactional
    void buscarOcorrenciasNaoPaginado() throws Exception {
        mvc.perform(get("/ocorrencia")
                        .param("processamentoId", "1")
                        .param("ocorrenciaTipoId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Transactional
    void buscarOcorrenciasPaginado() throws Exception {
        mvc.perform(get("/ocorrencia")
                        .param("processamentoId", "1")
                        .param("ocorrenciaTipoId", "1")
                        .param("tamanhoPagina", "10")
                        .param("numeroPagina", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    @Transactional
    void buscarOcorrenciasPaginadoFiltradoPorCnae() throws Exception {
        mvc.perform(get("/ocorrencia")
                        .param("processamentoId", "1")
                        .param("ocorrenciaTipoId", "1")
                        .param("filtroCodigoCnae", "5611201")
                        .param("tamanhoPagina", "10")
                        .param("numeroPagina", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Transactional
    void buscarUmaOcorrencia() throws Exception {
        mvc.perform(get("/ocorrencia/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Transactional
    void deveRetornarUmaOcorrenciaComCampos() throws Exception {
        mvc.perform(get("/ocorrencia/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.registroOrigem.socios").exists())
                .andExpect(jsonPath("$.registroOrigem.cnaeFiscalSecundaria").exists());
    }

    @Test
    @Transactional
    void deveRetornarOcorrenciaNaoEncontradoException() throws Exception {
        mvc.perform(get("/ocorrencia/999999"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RecursoNaoEncontradoException))
                .andExpect(result -> assertEquals("Ocorrencia não encontrado(a) com os parâmetros especificados",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @Transactional
    void deveRetornarRecursoNaoEncontradoException() throws Exception {
        mvc.perform(get("/ocorrencia")
                        .param("processamentoId", "100")
                        .param("ocorrenciaTipoId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RecursoNaoEncontradoException))
                .andExpect(result -> assertEquals("Processamento não encontrado(a) com os parâmetros especificados",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @Transactional
    void deveRetornarTiposOcorrencia() throws Exception {
        mvc.perform(get("/ocorrencia/tipo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}