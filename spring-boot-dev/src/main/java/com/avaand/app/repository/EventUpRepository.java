package com.avaand.app.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avaand.app.domain.EventUp;

public interface EventUpRepository extends JpaRepository<EventUp, UUID> {
    List<EventUp> findAllByDeviceName(String deviceName);
    Optional<EventUp> findFirstByOrderByTimeDesc();

    List<EventUp> findAllByOrderByTimeDesc();

    List<EventUp> findTop2000ByOrderByTimeDesc();
}
