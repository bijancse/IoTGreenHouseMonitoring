package com.avaand.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "sensor_user_threshold")
@ToString
public class Threshold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "m1")
    private int m1;
    @Column(name = "m2")
    private int m2;
    @Column(name = "m3")
    private int m3;
    @Column(name = "m4")
    private int m4;
    @Column(name = "m5")
    private int m5;
    @Column(name = "temp")
    private int temp;
    @Column(name = "type")
    private int type;
    @Column(name = "nodeID", unique = true)
    private Integer nodeID;
}
