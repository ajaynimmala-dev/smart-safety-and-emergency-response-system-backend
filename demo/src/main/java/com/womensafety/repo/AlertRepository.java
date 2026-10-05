package com.womensafety.repo;

import com.womensafety.model.Alert;
import com.womensafety.enumerations.AlertStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByStatus(AlertStatus status);

    List<Alert> findByUserId(Long userId);
}