package com.womensafety.service;

import com.womensafety.dto.AlertRequest;
import com.womensafety.dto.AlertResponse;

import java.util.List;

public interface AlertService {

    AlertResponse createAlert(
            String email,
            AlertRequest request
    );

    List<AlertResponse> getActiveAlerts();

    void resolveAlert(Long alertId);

    AlertResponse updateLocation(Long alertId, double latitude, double longitude);
}