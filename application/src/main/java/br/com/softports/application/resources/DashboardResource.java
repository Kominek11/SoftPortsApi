package br.com.softports.application.resources;

import br.com.softports.core.api.dashboard.dto.DashboardResponse;
import br.com.softports.core.api.dashboard.usecase.BuscarDashboard;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dashboard")
@RequiredArgsConstructor
public class DashboardResource {

    private final BuscarDashboard buscarDashboard;

    @GetMapping
    DashboardResponse buscarDashboard(){
        return buscarDashboard.executar();
    }
}
