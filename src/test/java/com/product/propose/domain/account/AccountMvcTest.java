package com.product.propose.domain.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.web.dto.data.*;
import com.product.propose.domain.account.web.dto.data.integration.ProfileUpdateData;
import com.product.propose.domain.account.web.dto.data.integration.SignUpData;
import com.product.propose.domain.account.web.dto.request.LoginRequest;
import com.product.propose.domain.account.web.dto.request.ProfileUpdateRequest;
import com.product.propose.domain.account.web.dto.request.SignUpRequest;
import com.product.propose.global.utils.AccountFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
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
import javax.validation.constraints.NotNull;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
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
        System.out.println(" \nResult ==================================================================================== \n");
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
    @Order(4)
    @DisplayName("Profile 반환 Mvc TEST")
    void ProfileTest() throws Exception {
        // GIVEN

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/account/profile")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(5)
    @DisplayName("Profile Update Mvc TEST")
    void updateProfileTest() throws Exception {
        // GIVEN
        AccountUpdateForm accountUpdateForm = new AccountUpdateForm("new Nick");
        ProfileUpdateForm profileUpdateForm = new ProfileUpdateForm("New User", "new phone", "new Post", "new address", true, true);

        ProfileUpdateData profileUpdateData = new ProfileUpdateData(accountUpdateForm, profileUpdateForm);
        ProfileUpdateRequest pageRequest = new ProfileUpdateRequest(profileUpdateData);

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/account/profile/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(pageRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(6)
    @DisplayName("Account exit Mvc TEST")
    void exitAccountTest() throws Exception {
        // GIVEN

        // WHEN THEN
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/account/exit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
