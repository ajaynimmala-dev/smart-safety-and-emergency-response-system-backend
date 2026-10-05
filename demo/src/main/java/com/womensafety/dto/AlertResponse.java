package com.womensafety.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertResponse {

    private Long alertId;

    private Long userId;

    private String userName;

    private Double latitude;

    private Double longitude;

    private String alertType;

    private String status;

    private LocalDateTime createdAt;
}