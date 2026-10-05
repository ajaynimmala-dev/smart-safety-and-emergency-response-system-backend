package com.womensafety.event;

import com.womensafety.dto.AlertResponse;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AlertCreatedEvent extends ApplicationEvent {

    private final AlertResponse alert;

    public AlertCreatedEvent(
            Object source,
            AlertResponse alert) {

        super(source);

        this.alert = alert;
    }
}