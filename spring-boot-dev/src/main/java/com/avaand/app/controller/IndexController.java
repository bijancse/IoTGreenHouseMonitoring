package com.avaand.app.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avaand.app.domain.Threshold;
import com.avaand.app.dto.EventUpDTO;
import com.avaand.app.model.SensorPayload;
import com.avaand.app.repository.ThresholdRepository;
import com.avaand.app.service.EventUpService;
import com.avaand.app.service.ThresholdService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final EventUpService eventUpService;
    private final ThresholdService thresholdService;
    private final ThresholdRepository thresholdRepository;

    @GetMapping("/")
    public String getRedAndGreenView(Model model) throws JsonProcessingException {
        Optional<EventUpDTO> eventUpDTO = eventUpService.collectRecentTop();
        Threshold threshold = thresholdService.findAll()
            .get(0);

        SensorPayload payload = eventUpDTO.get()
            .getPayload()
            .getPayload();

        model.addAttribute("payload", payload);
        model.addAttribute("threshold", threshold);

        return "index";
    }

    @GetMapping("/history")
    public String showHistory() {
        return "history";
    }

    @GetMapping("/threshold")
    public String showThresholdForm(@RequestParam(name = "nodeId", required = false) Integer nodeId, Model model) {
        Threshold threshold = new Threshold();
        if (nodeId != null) {
            threshold.setNodeID(nodeId);
        }
        if (thresholdRepository.findByNodeID(nodeId) != null) {
            model.addAttribute("threshold", thresholdRepository.findByNodeID(nodeId));
        } else {
            model.addAttribute("threshold", threshold);
        }
        return "thresholdForm";
    }

    @PostMapping("/saveThreshold")
    @Transactional
    public String saveThreshold(@ModelAttribute Threshold threshold) {
        if (threshold.getNodeID() != null) {
            Threshold nodeThreshold = thresholdRepository.findByNodeID(threshold.getNodeID());
            nodeThreshold.setNodeID(threshold.getNodeID());
            nodeThreshold.setTemp(threshold.getTemp());
            nodeThreshold.setType(threshold.getType());
            nodeThreshold.setM1(threshold.getM1());
            nodeThreshold.setM2(threshold.getM2());
            nodeThreshold.setM3(threshold.getM3());
            nodeThreshold.setM4(threshold.getM4());
            nodeThreshold.setM5(threshold.getM5());
            thresholdRepository.save(nodeThreshold);
        } else {
            thresholdService.save(threshold);
        }
        return "redirect:/"; // Redirect to the form after saving
    }
}
