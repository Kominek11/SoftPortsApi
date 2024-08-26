package br.com.softports.core.api.usuario.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.tarefa.dto.TarefaResponse;
import br.com.softports.core.api.usuario.dto.UsuarioResponse;

public interface BuscarUsuarios {

    Pagina<UsuarioResponse> executar(Integer tamanhoPagina,
                                     Integer numeroPagina,
                                     String ordenadoPor,
                                     String direcao,
                                     Long projetoId);

    UsuarioResponse executar(Long id);
}
