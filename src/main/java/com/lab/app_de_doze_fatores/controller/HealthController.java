package com.lab.app_de_doze_fatores.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("health")
public class HealthController {

    private final JdbcTemplate jdbcTemplate;

    public HealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> generalMetrics() {

        String api_status = "UP";
        String db_status = "DOWN";

        try {
            jdbcTemplate.execute("SELECT 1");
            db_status = "UP";
        } catch (Exception e) {
        }
        long uptimeInMillis = ManagementFactory.getRuntimeMXBean().getUptime();

        Duration duration = Duration.ofMillis(uptimeInMillis);
        String formattedUptime = String.format("%d dias, %02d:%02d:%02d",
                duration.toDaysPart(),
                duration.toHoursPart(),
                duration.toMinutesPart(),
                duration.toSecondsPart());

        return ResponseEntity.ok(Map.of(
                "api_status", api_status,
                "uptime_ms", uptimeInMillis,
                "uptime_formatado", formattedUptime,
                "db_status", db_status
        ));
    }

}
