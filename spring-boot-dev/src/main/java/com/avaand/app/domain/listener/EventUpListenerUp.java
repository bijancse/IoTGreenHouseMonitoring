package com.avaand.app.domain.listener;

import com.avaand.app.domain.EventUp;

import jakarta.persistence.PostPersist;

public class EventUpListenerUp {

    @PostPersist
    public void postUpdate(EventUp eventUp) {

    }

}
