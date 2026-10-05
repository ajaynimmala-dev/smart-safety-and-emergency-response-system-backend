package com.womensafety.listener;

import com.womensafety.event.AlertCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertCreatedListener {

    @EventListener
    public void handleAlertCreated(
            AlertCreatedEvent event) {

        System.out.println(
                "Emergency Alert Created: "
                        + event.getAlert().getAlertId()
        );

        System.out.println(
                "Location: "
                        + event.getAlert().getLatitude()
                        + ", "
                        + event.getAlert().getLongitude()
        );
    }
}