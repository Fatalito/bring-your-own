package com.magidash.dashboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DashboardRepositoryTests {

    @Autowired
    private DashboardRepository dashboardRepository;


    @Test
    public void shouldSaveDashboard() {
        final String title = "test";
        final Dashboard dashboard = dashboardRepository.save(Dashboard.builder().title(title).build());

        assertThat(dashboard.getTitle()).contains(title);
        assertThat(dashboard.getId()).isGreaterThan(0);
    }


    @Test
    public void shouldNotSaveDashboardWithNoTile() {
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            dashboardRepository.save(Dashboard.builder().build());
        });

        assertThat(exception.getMessage()).contains("not-null");
    }

}