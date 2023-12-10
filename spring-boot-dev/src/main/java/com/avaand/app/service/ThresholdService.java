package com.avaand.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avaand.app.domain.Threshold;
import com.avaand.app.repository.ThresholdRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThresholdService {
    private final ThresholdRepository thresholdRepository;

    public List<Threshold> findAll() {
        return thresholdRepository.findAll();
    }

    public Threshold save(Threshold threshold) {
        return thresholdRepository.save(threshold);
    }

    public Threshold update(Threshold threshold) {
        return thresholdRepository.saveAndFlush(threshold);
    }

    public String delete(Threshold threshold) {
        thresholdRepository.deleteById(Math.toIntExact(threshold.getId()));
        return "deleted";
    }

    public Threshold find(Integer nodeId) {
        return thresholdRepository.findByNodeID(nodeId);
    }
}
