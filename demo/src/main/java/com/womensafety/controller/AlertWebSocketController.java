package com.womensafety.controller;

import com.womensafety.dto.AlertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AlertWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public void broadcastAlert(AlertResponse alert) {

        messagingTemplate.convertAndSend(
                "/topic/alerts",
                alert
        );
    }
}