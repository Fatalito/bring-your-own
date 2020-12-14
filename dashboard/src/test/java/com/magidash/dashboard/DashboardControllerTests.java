package com.magidash.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class DashboardControllerTests {

    @LocalServerPort
    private int port;

    private String dashboardUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DashboardRepository dashboardRepository;

    @BeforeEach
    public void setUp() {
        dashboardUrl = "http://localhost:" + port + "/dashboards";
    }

    @Test
    public void dashboardsShouldReturnAnEmptyArray() throws Exception {
        final ResponseEntity<Dashboard[]> responseEntity = this.restTemplate.getForEntity(dashboardUrl, Dashboard[].class);

        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEmpty();
    }

    @Test
    public void dashboardsShouldReturnAnElementWithTitle() throws Exception {
        final String title = "test";
        dashboardRepository.save(Dashboard.builder().title(title).build());

        final ResponseEntity<Dashboard[]> responseEntity = this.restTemplate.getForEntity(dashboardUrl, Dashboard[].class);
        final Dashboard[] dashboards = responseEntity.getBody();

        assertThat(dashboards[0].getTitle()).isEqualTo(title);
    }
}