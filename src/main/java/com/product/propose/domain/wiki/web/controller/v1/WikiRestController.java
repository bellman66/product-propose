package com.product.propose.domain.wiki.web.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.service.WikiService;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;
import com.product.propose.global.api.RestApiController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;


@RestController(value = "wikiRestController")
@RequestMapping(value = "/api/v1/wiki", produces = MediaType.APPLICATION_JSON_VALUE)
public class WikiRestController extends RestApiController {

    private final WikiService wikiService;

    public WikiRestController(ObjectMapper objectMapper, WikiService wikiService) {
        super(objectMapper);
        this.wikiService = wikiService;
    }


    @PostMapping("/register")
    private ResponseEntity<String> registerWiki(@RequestBody @Valid WikiRegisterRequest request) {
        // Register Logic
        Wiki result = wikiService.registerWiki(request);

        return createRestResponse(new HashMap<>() {{
            put("wiki", result.getId());
        }});
    }

    // ===== ===== ===== ===== ===== Read Business Method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== Update Business Method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== Remove Business Method ===== ===== ===== ===== =====
}
