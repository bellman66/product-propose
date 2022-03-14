package com.product.propose.domain.wiki;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.wiki.entity.aggregate.Wiki;
import com.product.propose.domain.wiki.entity.embedded.SaleWay;
import com.product.propose.domain.wiki.entity.reference.Tag;
import com.product.propose.domain.wiki.repository.PriceRecordRepository;
import com.product.propose.domain.wiki.repository.WikiRepository;
import com.product.propose.domain.wiki.web.dto.data.PriceRecordCreateForm;
import com.product.propose.domain.wiki.web.dto.data.WikiCreateForm;
import com.product.propose.domain.wiki.web.dto.data.integration.PriceUpdateData;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiCreateData;
import com.product.propose.domain.wiki.web.dto.data.integration.WikiUpdateData;
import com.product.propose.domain.wiki.web.dto.request.PriceRegisterRequest;
import com.product.propose.domain.wiki.web.dto.request.PriceUpdateRequest;
import com.product.propose.domain.wiki.web.dto.request.WikiRegisterRequest;
import com.product.propose.domain.wiki.web.dto.request.WikiUpdateRequest;
import com.product.propose.domain.wiki.web.dto.response.WikiSummaryResponse;
import com.product.propose.global.data.dto.PageResponse;
import com.product.propose.global.utils.AccountFactory;
import com.product.propose.global.utils.WikiFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Transactional
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

    private String accessToken;

    @BeforeEach
    void initTestCase() throws Exception {
        this.accessToken = AccountFactory.create().getJwtToken();

        // Wiki Test Case
        Wiki wiki = WikiFactory.create();

        System.out.println(" \n\n>>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>>");
        System.out.println(">>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> >>> \n\n ");

    }

    @Test
    @Order(1)
    @DisplayName("위키 등록 MVC TEST")
    void registerWikiTest() throws Exception {
        // GIVEN
        WikiCreateForm wikiCreateForm = new WikiCreateForm("TestWiki2");
        PriceRecordCreateForm priceRecordCreateForm = new PriceRecordCreateForm(22000, 20000, new SaleWay("test2", "", "", "", "", "", "", ""));
        List<String> tagGroup = new ArrayList<>(){{ add("TestTag1"); add("TestTag2"); }};

        WikiCreateData wikiCreateData = new WikiCreateData(wikiCreateForm, priceRecordCreateForm, tagGroup);
        WikiRegisterRequest request = new WikiRegisterRequest(wikiCreateData);
        String content = objectMapper.writeValueAsString(request);

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/wiki/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + accessToken)
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
    @DisplayName("위키 Page READ MVC TEST")
    void readWikiPageTest() throws Exception {
        // GIVEN - Default Use

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/wiki/read"+"?page=0&size=99"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    @DisplayName("가격 추가 등록 MVC TEST")
    void registerPriceRecordTest() throws Exception {
        // GIVEN - Default Use
        PriceRecordCreateForm priceRecordCreateForm = new PriceRecordCreateForm(30000, 24900, new SaleWay("Add Sale Way","","","","","","",""));
        PriceRegisterRequest priceRegisterRequest = new PriceRegisterRequest(priceRecordCreateForm);

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/wiki/1/price/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(priceRegisterRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(4)
    @DisplayName("위키 업데이트 MVC TEST")
    void putWikiTest() throws Exception {
        // GIVEN
        WikiUpdateData wikiUpdateData = new WikiUpdateData("update Title");
        WikiUpdateRequest wikiUpdateRequest = new WikiUpdateRequest(wikiUpdateData);

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/wiki/1/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(wikiUpdateRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(5)
    @DisplayName("위키-가격 업데이트 MVC TEST")
    void putPriceRecordTest() throws Exception {
        // GIVEN
        PriceUpdateData wikiUpdateData = new PriceUpdateData(40000,34500, new SaleWay("Update Way","","","","","","",""));
        PriceUpdateRequest wikiUpdateRequest = new PriceUpdateRequest(wikiUpdateData);

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/wiki/1/price/1/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(wikiUpdateRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(6)
    @DisplayName("위키 썸네일 업데이트 MVC TEST")
    void putWikiImageTest() throws Exception {
        // GIVEN
        ClassPathResource classPathResource = new ClassPathResource("/static/image/170.jpeg");
        File file = classPathResource.getFile();

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.multipart("/api/v1/wiki/1/update/image")
                        .file(new MockMultipartFile("image", new FileInputStream(file)))
                        .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    // JPA DATA ========================================================================================================

    @Test
    @DisplayName("위키 RESPONSE JPA DATA TEST")
    void getWikiResponse() throws Exception {
        // GIVEN - INIT

        // WHEN
        WikiSummaryResponse result = wikiRepository.readWikiResponseById(1L);
        List<Tag> tagGroup = result.getTagGroup();

        // THEN
        Assertions.assertThat(result)
                .isNotNull().isOfAnyClassIn(WikiSummaryResponse.class)
                .extracting(WikiSummaryResponse::getTitle).isInstanceOf(String.class);
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
