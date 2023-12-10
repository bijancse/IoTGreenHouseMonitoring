package com.avaand.app.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.avaand.app.domain.Threshold;
import com.avaand.app.dto.EventUpDTO;
import com.avaand.app.model.SensorPayload;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@RequiredArgsConstructor
public class ScheduledServiceChecker {

    private final ThresholdService thresholdService;
    private final EventUpService eventUpService;
    private final EmailService emailService;

    @Scheduled(fixedRate = 9000)
    public void checkAndAlert() throws JsonProcessingException {
        List<EventUpDTO> eventList = eventUpService.collectRecent();

        List<Threshold> userThreshold = thresholdService.findAll();
        for (Threshold threshold : userThreshold) {
            int m1 = threshold.getM1();
            int m2 = threshold.getM2();
            int m3 = threshold.getM3();
            int m4 = threshold.getM4();
            int m5 = threshold.getM5();
            int temp = threshold.getTemp();
            int nodeID = threshold.getNodeID();

            for (EventUpDTO eventUpDTO : eventList) {
                SensorPayload payload = eventUpDTO.getPayload()
                    .getPayload();
                if (isValidTo(payload, threshold)) {
                    this.sendMail(nodeID);
                }
            }
        }

    }

    private boolean isValidTo(SensorPayload payload, Threshold threshold) {
        int nodeID = (int) payload.getNodeID();
        int nodeIDEventThreshold = threshold.getNodeID();
        if (nodeID == nodeIDEventThreshold) {
            return ((payload.getM1() < threshold.getM1()) || (payload.getM2() < threshold.getM2()) || (payload.getM3() < threshold.getM3()) || (payload.getM4() < threshold.getM4()) || payload.getM5() < threshold.getM5() || (payload.getTemp()
                < threshold.getTemp()));
        }
        return false;
    }

    private void sendMail(int nodeID) {
        log.info("Sending Email for NodeId " + nodeID);
        emailService.sendEmail("bijancse2023@gmail.com", "Alert!! Threshold Below Level. [NodeID]" + nodeID, "Please water your plant threshold is low for Node ID : " + nodeID);
    }

}
