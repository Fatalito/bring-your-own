package com.magidash.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardController {

    @Autowired
    private DashboardRepository dashboardRepository;

    @GetMapping("/dashboards")
    public List<Dashboard> Dashboards() {
        return dashboardRepository.findAll();
    }

}