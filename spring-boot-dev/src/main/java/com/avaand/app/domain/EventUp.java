package com.avaand.app.domain;

import java.util.Date;
import java.util.UUID;

import com.avaand.app.domain.listener.EventUpListenerUp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "event_up")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(value = EventUpListenerUp.class)
public class EventUp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "deduplication_id")
    private UUID deduplicationId;
    @Column(name = "time")
    private Date time;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "dev_eui")
    private String devEUI;
    @Column(name = "object")
    private String object;

}
