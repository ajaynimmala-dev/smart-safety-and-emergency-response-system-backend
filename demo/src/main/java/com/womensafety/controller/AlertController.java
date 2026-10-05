package com.womensafety.controller;

import com.womensafety.dto.AlertRequest;
import com.womensafety.dto.AlertResponse;
import com.womensafety.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AlertController {

    private final AlertService alertService;

    @PostMapping
    public ResponseEntity<AlertResponse> createAlert(
            @RequestBody AlertRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                alertService.createAlert(email, request)
        );
    }

    @GetMapping("/active")
    public ResponseEntity<List<AlertResponse>> getActiveAlerts() {

        return ResponseEntity.ok(
                alertService.getActiveAlerts()
        );
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<String> resolveAlert(
            @PathVariable Long id) {

        alertService.resolveAlert(id);

        return ResponseEntity.ok(
                "Alert resolved successfully"
        );
    }

    @PutMapping("/{alertId}/location")
    public AlertResponse updateLocation(
            @PathVariable Long alertId,
            @RequestParam double latitude,
            @RequestParam double longitude
    ) {

        return alertService.updateLocation(
                alertId,
                latitude,
                longitude
        );
    }
}