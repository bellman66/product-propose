package com.template.basespring.global.event.log;

import com.template.basespring.global.event.log.dto.ExceptionEvent;
import com.template.basespring.global.event.log.dto.LogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogEventListener {

    @Async
    @EventListener
    public void onExceptionEvent(ExceptionEvent event) {
        log.error(event.getExceptionString());
    }

    @Async
    @EventListener
    public void onLogEvent(LogEvent event) {
        log.info(event.getMessage());
    }
}
