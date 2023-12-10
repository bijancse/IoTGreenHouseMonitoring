package com.avaand.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avaand.app.domain.Threshold;

public interface ThresholdRepository extends JpaRepository<Threshold, Integer> {
    Threshold findByNodeID(int nodeID);
}
