package com.product.propose.domain.wiki;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.wiki.entity.embedded.SaleWay;
import com.product.propose.domain.wiki.entity.reference.Tag;
import com.product.propose.domain.wiki.repository.PriceRecordRepository;
import com.product.propose.domain.wiki.repository.WikiRepository;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.WikiCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import com.product.propose.domain.wiki.web.dto.request.PriceRegisterRequest;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;
import com.product.propose.domain.wiki.web.dto.response.WikiResponse;
import com.product.propose.global.data.dto.PageResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
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

    final Long DEFAULT_TARGET_ID = 1L;

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WikiRepository wikiRepository;
    @Autowired
    private PriceRecordRepository priceRecordRepository;

    @BeforeEach
    void initTestCase() throws Exception {
        // Sign Up Test Case
        WikiCreateForm wikiCreateForm = new WikiCreateForm(1L, "TestWiki1");
        PriceRecordCreateForm priceRecordCreateForm = new PriceRecordCreateForm(1L, 22000, 20000, new SaleWay("test1", "", "", "", "", "", "", ""));
        List<String> tagGroup = new ArrayList<>(){{ add("TestTag1"); add("TestTag2"); }};

        WikiCreateData wikiCreateData = new WikiCreateData(wikiCreateForm, priceRecordCreateForm, tagGroup);
        WikiRegisterRequest request = new WikiRegisterRequest(wikiCreateData);
        String content = objectMapper.writeValueAsString(request);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/wiki/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content));

        System.out.println(" \n\n>>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>>");
        System.out.println(" >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> \n\n ");

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
    void readWikiTest() throws Exception {
        // GIVEN - Default Use

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/wiki/" + DEFAULT_TARGET_ID + "/read")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    @DisplayName("가격 추가 등록 MVC TEST")
    void registerPriceRecordTest() throws Exception {
        // GIVEN - Default Use
        PriceRecordCreateForm priceRecordCreateForm = new PriceRecordCreateForm(1L, 30000, 24900, new SaleWay("Add Sale Way","","","","","","",""));
        PriceRegisterRequest priceRegisterRequest = new PriceRegisterRequest(priceRecordCreateForm);

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/wiki/1/price/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(priceRegisterRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    // JPA DATA ========================================================================================================

    @Test
    @DisplayName("위키 RESPONSE JPA DATA TEST")
    void getWikiResponse() throws Exception {
        // GIVEN - INIT

        // WHEN
        WikiResponse result = wikiRepository.findWikiResponseById(1L);
        List<Tag> tagGroup = result.getTagGroup();

        // THEN
        Assertions.assertThat(result)
                .isNotNull().isOfAnyClassIn(WikiResponse.class)
                .extracting(WikiResponse::getTitle).isInstanceOf(String.class);
        Assertions.assertThat(result.getTagGroup())
                .isNotEmpty().hasSize(2);
    }

    @Test
    @DisplayName("가격기록 PAGEABLE JPA DATA TEST")
    void priceRecordPageable() throws Exception {
        // GIVEN - INIT

        // WHEN
        PageRequest pageRequest = PageRequest.of(0, 5);
        PageResponse result = priceRecordRepository.readPageByWikiId(1L, pageRequest);

        // THEN
        Assertions.assertThat(result).isNotNull().isOfAnyClassIn(PageResponse.class);
    }
}
