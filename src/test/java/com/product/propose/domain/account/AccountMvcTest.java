package com.product.propose.domain.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.request.SignUpRequest;
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
@AutoConfigureMockMvc
public class AccountMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 MVC TEST")
    void signUpTest() throws Exception {
        // GIVEN
        AccountCreateForm accountCreateForm = new AccountCreateForm("Test@gmail.com", "Test");
        LinkedAuthCreateForm linkedAuthCreateForm = new LinkedAuthCreateForm(AccountType.PASSWORD, "PASSWORD");
        UserProfileCreateForm userProfileCreateForm = new UserProfileCreateForm("UserName", "01000001111", "06777", "Test Address");
        SignUpData signUpData = new SignUpData(accountCreateForm, linkedAuthCreateForm, userProfileCreateForm);
        SignUpRequest signUpRequest = new SignUpRequest(signUpData);

        String content = objectMapper.writeValueAsString(signUpRequest);

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/account/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
    }

}