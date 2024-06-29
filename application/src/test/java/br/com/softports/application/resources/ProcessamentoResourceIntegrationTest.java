package br.com.softports.application.resources;

import br.com.softports.application.IntegrationTest;
import br.com.softports.core.internal.common.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql({"/db/scripts/base_padrao.sql"})
class ProcessamentoResourceIntegrationTest extends IntegrationTest {

    @Test
    @Transactional
    void deveRetornarProcessamentosPaginado() throws Exception {
        mvc.perform(get("/processamento")
                    .param("versaoId", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Transactional
    void deveRetornarRecursoNaoEncontradoException() throws Exception {
        mvc.perform(get("/processamento")
                        .param("versaoId", "200"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RecursoNaoEncontradoException))
                .andExpect(result -> assertEquals("Processamento não encontrado(a) com os parâmetros especificados",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @Transactional
    void deveRetornarUmProcessomento() throws Exception {
        mvc.perform(get("/processamento/240"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}