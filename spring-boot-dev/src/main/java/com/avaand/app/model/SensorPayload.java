package com.avaand.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SensorPayload {
    @JsonProperty("m1")
    private double m1;

    @JsonProperty("m2")
    private double m2;

    @JsonProperty("m3")
    private double m3;

    @JsonProperty("m4")
    private double m4;

    @JsonProperty("m5")
    private double m5;

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("type")
    private double type;

    @JsonProperty("nodeID")
    private double nodeID;
}