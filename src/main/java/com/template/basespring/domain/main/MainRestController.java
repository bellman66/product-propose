package com.template.basespring.domain.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.basespring.global.api.RestApiController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Validated
@RestController(value = "mainRestController")
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainRestController extends RestApiController {

    public MainRestController(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    // =================================================================================================================

    @GetMapping(value = "/server-check")
    public ResponseEntity<String> checkServerStatus() {
        return createRestResponse(new HashMap<>(){{
            put("message", "SERVER OK");
        }});
    }
}
