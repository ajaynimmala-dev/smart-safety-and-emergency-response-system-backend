package com.womensafety.service;

import com.womensafety.dto.AlertRequest;
import com.womensafety.dto.AlertResponse;
import com.womensafety.enumerations.AlertStatus;
import com.womensafety.enumerations.AlertType;
import com.womensafety.model.Alert;
import com.womensafety.model.User;
import com.womensafety.repo.AlertRepository;
import com.womensafety.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertServiceImplementation implements AlertService {

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public AlertResponse createAlert(
            String email,
            AlertRequest request) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setLatitude(request.getLatitude());
        user.setLongitude(request.getLongitude());

        userRepository.save(user);

        Alert alert = Alert.builder()
                .user(user)
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .alertType(AlertType.EMERGENCY)
                .status(AlertStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        Alert savedAlert = alertRepository.save(alert);

        AlertResponse response = convertToResponse(savedAlert);

        messagingTemplate.convertAndSend(
                "/topic/alerts",
                response
        );

        return response;
    }

    @Override
    public List<AlertResponse> getActiveAlerts() {

        return alertRepository
                .findByStatus(AlertStatus.ACTIVE)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public void resolveAlert(Long alertId) {

        Alert alert = alertRepository
                .findById(alertId)
                .orElseThrow(() ->
                        new RuntimeException("Alert not found"));

        alert.setStatus(AlertStatus.RESOLVED);

        alertRepository.delete(alert);
    }

    @Override
    public AlertResponse updateLocation(Long alertId, double latitude, double longitude) {

            Alert alert = alertRepository
                    .findById(alertId)
                    .orElseThrow(() ->
                            new RuntimeException("Alert not found")
                    );

            if (!"ACTIVE".equals(alert.getStatus().toString())) {
                throw new RuntimeException(
                        "Alert is already resolved"
                );
            }

            alert.setLatitude(latitude);
            alert.setLongitude(longitude);

            Alert savedAlert =
                    alertRepository.save(alert);

            return convertToResponse(savedAlert);

    }

    private AlertResponse convertToResponse(Alert alert) {

        return AlertResponse.builder()
                .alertId(alert.getId())
                .userId(alert.getUser().getId())
                .userName(alert.getUser().getName())
                .latitude(alert.getLatitude())
                .longitude(alert.getLongitude())
                .alertType(alert.getAlertType().name())
                .status(alert.getStatus().name())
                .createdAt(alert.getCreatedAt())
                .build();
    }
}