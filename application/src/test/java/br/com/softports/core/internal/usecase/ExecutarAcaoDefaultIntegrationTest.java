package br.com.softports.core.internal.usecase;

import br.com.egoverne.iafis.application.IafisApplication;
import br.com.softports.application.IntegrationTest;
import br.com.softports.core.internal.acao.usecase.ExecutarAcaoDefault;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = IafisApplication.class)
@ActiveProfiles("test")
@Sql({"/db/scripts/base_padrao.sql"})
public class ExecutarAcaoDefaultIntegrationTest extends IntegrationTest {

    @Autowired
    private ExecutarAcaoDefault cut;

    @Test
    void deveExecutarAAcao() {
        //cut.executar(5L, 2L, List.of(4L));
    }
}
