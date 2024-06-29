package br.com.softports.application.resources;

import br.com.softports.application.IntegrationTest;
import br.com.softports.core.internal.common.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Sql({"/db/scripts/base_padrao.sql"})
class AtualizacaoResourceIntegrationTest extends IntegrationTest {

    @Autowired
    private AtualizacaoResource atualizacaoResource;

    @Test
    @Transactional
    void deveRetornarListaDeVersoes() throws Exception {
        mvc.perform(get("/versao").param("processoId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(5)));
    }

    @Test
    @Transactional
    void deveRetornarRecursoNaoEncontradoException() throws Exception {
        mvc.perform(get("/versao").param("processoId", "200"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RecursoNaoEncontradoException))
                .andExpect(result -> assertEquals("Versão não encontrado(a) com os parâmetros especificados",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}