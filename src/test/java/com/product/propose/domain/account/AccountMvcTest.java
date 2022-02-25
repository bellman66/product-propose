package com.product.propose.domain.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.web.dto.data.AccountCreateForm;
import com.product.propose.domain.account.web.dto.data.LinkedAuthCreateForm;
import com.product.propose.domain.account.web.dto.data.UserProfileCreateForm;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.request.LoginRequest;
import com.product.propose.domain.account.web.dto.request.SignUpRequest;
import com.product.propose.global.utils.AccountFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String accessToken;

    @BeforeEach
    void before() throws Exception {
        this.accessToken = AccountFactory.create().getJwtToken();
    }

    @Test
    @Order(1)
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

    @Test
    @Order(2)
    @DisplayName("로그인 Mvc TEST")
    void loginTest() throws Exception {
        // GIVEN
        LoginRequest loginRequest = new LoginRequest(AccountType.PASSWORD, "Test@gmail.com", "PASSWORD");
        String content = objectMapper.writeValueAsString(loginRequest);

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/account/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    @DisplayName("유저 Info Mvc TEST")
    void infoTest() throws Exception {
        // GIVEN

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/account/info")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    @DisplayName("유저 Profile Mvc TEST")
    void ProfileTest() throws Exception {
        // GIVEN

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/account/profile")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
