package com.avaand.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.avaand.app.domain.EventUp;
import com.avaand.app.dto.EventUpDTO;
import com.avaand.app.model.Payload;
import com.avaand.app.repository.EventUpRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventUpService {

    private final EventUpRepository eventUpRepository;

    public List<EventUpDTO> collectRecent() throws JsonProcessingException {
        // List<EventUp> events = eventUpRepository.findTop2000ByOrderByTimeDesc();
        List<EventUp> events = eventUpRepository.findAll();

        HashMap<Double, EventUp> uniqueSet = new HashMap();
        final ObjectMapper objectMapper = new ObjectMapper();
        for (EventUp event : events) {
            Payload sensorPayload = objectMapper.readValue(event.getObject(), Payload.class);
            if (sensorPayload.getPayload()
                .getNodeID() == 0) {
                continue;
            }
            if (uniqueSet.containsKey(sensorPayload.getPayload()
                .getNodeID())) {
                continue;
            }
            uniqueSet.put(sensorPayload.getPayload()
                .getNodeID(), event);
        }
        final List<EventUp> list = new ArrayList<>();
        for (Map.Entry<Double, EventUp> entry : uniqueSet.entrySet()) {
            list.add(entry.getValue());
        }
        System.out.println(list);
        return build(list);
    }

    public List<EventUpDTO> collectBySensorName(String sensorName) throws JsonProcessingException {
        List<EventUp> events = eventUpRepository.findAllByDeviceName(sensorName);
        return build(events);
    }

    private List<EventUpDTO> build(List<EventUp> events) throws JsonProcessingException {
        List<EventUpDTO> eventUpDTOS = new LinkedList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (EventUp event : events) {
            EventUpDTO upDTO = new EventUpDTO();
            upDTO.setDeduplicationId(event.getDeduplicationId());
            upDTO.setDeviceName(event.getDeviceName());
            upDTO.setDevEUI(event.getDevEUI());
            upDTO.setTime(event.getTime());
            Payload sensorPayload = objectMapper.readValue(event.getObject(), Payload.class);
            upDTO.setPayload(sensorPayload);
            eventUpDTOS.add(upDTO);
        }
        return eventUpDTOS;
    }

    public List<EventUpDTO> collectAll(int size) throws JsonProcessingException {
        List<EventUp> events = eventUpRepository.findAll();
        return build(events);
    }

    public Optional<EventUpDTO> collectRecentTop() throws JsonProcessingException {
        Optional<EventUp> nowEvent = eventUpRepository.findFirstByOrderByTimeDesc();
        ObjectMapper objectMapper = new ObjectMapper();
        EventUpDTO upDTO = new EventUpDTO();
        upDTO.setDeduplicationId(nowEvent.get()
            .getDeduplicationId());
        upDTO.setDeviceName(nowEvent.get()
            .getDeviceName());
        upDTO.setDevEUI(nowEvent.get()
            .getDevEUI());
        upDTO.setTime(nowEvent.get()
            .getTime());
        Payload sensorPayload = objectMapper.readValue(nowEvent.get()
            .getObject(), Payload.class);
        upDTO.setPayload(sensorPayload);
        return Optional.of(upDTO);
    }
}
