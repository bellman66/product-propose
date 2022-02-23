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
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WikiMvcTest {

    final Long DEFAULT_TARGET_ID = 1L;

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void initTestCase() throws Exception {
        // Sign Up Test Case
        WikiCreateForm wikiCreateForm = new WikiCreateForm(1L, "TestWiki1");
        PriceRecordCreateForm priceRecordCreateForm = new PriceRecordCreateForm(2L, 22000, 20000, new SaleWay("test1", "", "", "", "", "", "", ""));
        List<String> tagGroup = new ArrayList<>(){{ add("TestTag1"); add("TestTag2"); }};

        WikiCreateData wikiCreateData = new WikiCreateData(wikiCreateForm, priceRecordCreateForm, tagGroup);
        WikiRegisterRequest request = new WikiRegisterRequest(wikiCreateData);
        String content = objectMapper.writeValueAsString(request);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/wiki/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content));

        System.out.println(" \n\n >>> >>> >>> === === === >>> >>> >>> === === === >>> >>> >>> === === === >>> >>> >>> === === === \n\n ");
    }

    @Test
    @Order(1)
    @DisplayName("위키 등록 MVC TEST")
    void registerWikiTest() throws Exception {
        // GIVEN
        WikiCreateForm wikiCreateForm = new WikiCreateForm(1L, "TestWiki2");
        PriceRecordCreateForm priceRecordCreateForm = new PriceRecordCreateForm(2L, 22000, 20000, new SaleWay("test2", "", "", "", "", "", "", ""));
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

    @Test
    @Order(2)
    @DisplayName("위키 READ MVC TEST")
    @Commit
    void readWikiTest() throws Exception {
        // GIVEN - Default Use

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/wiki/read/" + DEFAULT_TARGET_ID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
