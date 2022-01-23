package com.product.propose.example;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
//@WebMvcTest(controllers = MainRestController.class, includeFilters = @ComponentScan.Filter(classes = {EnableWebSecurity.class}))
@AutoConfigureMockMvc
public class WebMvcTestDefault {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("테스트 체크 웹 MVC 테스트")
    public void testCheck() throws Exception {
        JSONObject content = new JSONObject() {{
            put("name", 1);
        }};

        mvc.perform(
                MockMvcRequestBuilders.post("/api/v1/testcheck/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(content.toString())
            )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
