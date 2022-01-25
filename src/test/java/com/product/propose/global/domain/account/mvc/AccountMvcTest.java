package com.product.propose.global.domain.account.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.service.AccountService;
import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.request.SignUpRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void signUpTest() throws Exception {
        // GIVEN
        AccountCreateForm accountCreateForm = new AccountCreateForm("Test@gmail.com", "Test");
        LinkedAuthCreateForm linkedAuthCreateForm = new LinkedAuthCreateForm(AccountType.PASSWORD, "PASSWORD");
        UserProfileCreateForm userProfileCreateForm = new UserProfileCreateForm("UserName", "01000001111", "06777", "Test Address");
        SignUpData signUpData = new SignUpData(accountCreateForm, linkedAuthCreateForm, userProfileCreateForm);
        SignUpRequest signUpRequest = new SignUpRequest(signUpData);

        String content = objectMapper.writeValueAsString(signUpRequest);

        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/account/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
    }

}
