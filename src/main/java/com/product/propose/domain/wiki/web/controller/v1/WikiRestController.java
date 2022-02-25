package com.product.propose.domain.wiki.web.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.repository.PriceRecordRepository;
import com.product.propose.domain.wiki.service.PriceRecordService;
import com.product.propose.domain.wiki.service.WikiService;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;
import com.product.propose.domain.wiki.web.dto.response.WikiResponse;
import com.product.propose.global.api.RestApiController;
import com.product.propose.global.data.dto.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;


@RestController(value = "wikiRestController")
@RequestMapping(value = "/api/v1/wiki", produces = MediaType.APPLICATION_JSON_VALUE)
public class WikiRestController extends RestApiController {

    private static final int DEFAULT_PAGE_SIZE = 20;

    // Service
    private final WikiService wikiService;
    private final PriceRecordService priceRecordService;

    // Repository
    private final PriceRecordRepository priceRecordRepository;

    public WikiRestController(ObjectMapper objectMapper, WikiService wikiService, PriceRecordService priceRecordService, PriceRecordRepository priceRecordRepository) {
        super(objectMapper);
        this.wikiService = wikiService;
        this.priceRecordService = priceRecordService;
        this.priceRecordRepository = priceRecordRepository;
    }

    // ===== ===== ===== ===== ===== Create Business Method ===== ===== ===== ===== =====

    @PostMapping("/register")
    private ResponseEntity<String> registerWiki(@RequestBody @Valid WikiRegisterRequest request) {
        // Register Logic
        Wiki result = wikiService.registerWiki(request);
        return createRestResponse(new HashMap<>() {{
            put("wikiId", result.getId());
        }});
    }

    // ===== ===== ===== ===== ===== Read Business Method ===== ===== ===== ===== =====

    @PostMapping("/read/{wikiId}")
    private ResponseEntity<String> readWiki(@PathVariable Long wikiId) {
        // get WikiResponse
        WikiResponse wikiSummary = wikiService.readWiki(wikiId);

        // get Page PriceRecord
        PageResponse priceRecords = priceRecordRepository.readPageByWikiId(wikiId, PageRequest.of(0, DEFAULT_PAGE_SIZE));

        return createRestResponse(new HashMap<>() {{
            put("summary", wikiSummary);
            put("priceRecords", priceRecords);
        }});
    }

    // ===== ===== ===== ===== ===== Update Business Method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== Remove Business Method ===== ===== ===== ===== =====
}
