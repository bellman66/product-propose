package com.product.propose.domain.wiki;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.entity.embedded.SaleWay;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.WikiCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WikiMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("위키 등록 MVC TEST")
    @Order(1)
    void registerWikiTest() throws Exception {
        // GIVEN
        WikiCreateForm wikiCreateForm = new WikiCreateForm(1L, "TestWiki");
        PriceRecordCreateForm priceRecordCreateForm = new PriceRecordCreateForm(2L, 20000, new SaleWay("test1", "", "", "", "", "", "", ""));
        List<String> tagGroup = new ArrayList<>(){{ add("TestTag1"); add("TestTag2"); }};

        WikiCreateData wikiCreateData = new WikiCreateData(wikiCreateForm, priceRecordCreateForm, tagGroup);
        WikiRegisterRequest request = new WikiRegisterRequest(wikiCreateData);
        String content = objectMapper.writeValueAsString(request);

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/wiki/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
