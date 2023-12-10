package com.avaand.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaand.app.dto.EventUpDTO;
import com.avaand.app.service.EventUpService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventUpService eventUpService;

    @GetMapping("/all")
    public List<EventUpDTO> events() throws JsonProcessingException {
        return eventUpService.collectAll(0);
    }

    @GetMapping("/filtered")
    public List<EventUpDTO> filtered() throws JsonProcessingException {
        return eventUpService.collectRecent();
    }

    @GetMapping("/now")
    public Optional<EventUpDTO> findTop() throws JsonProcessingException {
        return eventUpService.collectRecentTop();
    }

    @GetMapping("/sensor/{sensorName}")
    public List<EventUpDTO> eventsBySensorName(@PathVariable("sensorName") String sensorName) throws JsonProcessingException {
        return eventUpService.collectBySensorName(sensorName);
    }

}
