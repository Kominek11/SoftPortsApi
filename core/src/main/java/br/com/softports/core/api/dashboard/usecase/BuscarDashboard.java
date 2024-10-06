package br.com.softports.core.api.dashboard.usecase;

import br.com.softports.core.api.common.dto.Pagina;
import br.com.softports.core.api.dashboard.dto.DashboardResponse;
import br.com.softports.core.api.derivado.dto.DerivadoResponse;

import java.util.List;

public interface BuscarDashboard {

    DashboardResponse executar();
}