package com.avaand.app.dto;

import java.util.Date;
import java.util.UUID;

import com.avaand.app.model.Payload;

import lombok.Data;

@Data
public class EventUpDTO {
    private UUID deduplicationId;
    private Date time;
    private String deviceName;
    private String devEUI;
    private Payload payload;
}
