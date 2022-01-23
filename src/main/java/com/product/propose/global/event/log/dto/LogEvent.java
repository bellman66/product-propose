package com.product.propose.global.event.log.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogEvent {

    private String message;

    public static LogEvent creatLogEvent(String message) {
        LogEvent logEvent = new LogEvent();
        logEvent.setMessage(message);
        return logEvent;
    }
}
