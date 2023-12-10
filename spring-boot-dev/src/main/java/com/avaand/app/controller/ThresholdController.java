package com.avaand.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaand.app.domain.Threshold;
import com.avaand.app.service.EventUpService;
import com.avaand.app.service.ThresholdService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/threshold")
@RequiredArgsConstructor
public class ThresholdController {

    private final ThresholdService thresholdService;

    @GetMapping(value = "/save")
    public ResponseEntity<?> saveAndUpdate(@RequestBody Threshold threshold) {
        return ResponseEntity.ok(thresholdService.save(threshold));
    }

    public ResponseEntity<?> update(Threshold threshold) throws Exception {
        if (threshold.getId() == null) {
            throw new Exception("Threshold must send id to update");
        }
        return ResponseEntity.ok(thresholdService.update(threshold));
    }

    public ResponseEntity<?> delete(Threshold threshold) throws Exception {
        if (threshold.getId() == null) {
            throw new Exception("Threshold must send id to delete");
        }
        return ResponseEntity.ok(thresholdService.delete(threshold));
    }

    public ResponseEntity<?> find(Integer nodeId) {
        if (nodeId != null) {
            return ResponseEntity.ok(thresholdService.find(nodeId));
        }
        return null;
    }

    @GetMapping("/all")
    public List<Threshold> findAll() {
        return thresholdService.findAll();
    }

}
