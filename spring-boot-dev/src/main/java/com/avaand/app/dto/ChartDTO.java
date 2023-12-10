package com.avaand.app.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class ChartDTO {
    private UUID deduplicationId;
    private Date time;
    private Integer light;
    private Integer moisture;
    private Integer temperature;
}
