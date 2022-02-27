package com.product.propose.domain.account.web.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.entity.enums.AccountType;
import com.product.propose.domain.account.repository.AccountRepository;
import com.product.propose.domain.account.service.AuthService;
import com.product.propose.domain.account.service.adapter.AuthServiceAdapter;
import com.product.propose.domain.account.web.dto.request.LoginRequest;
import com.product.propose.domain.account.web.dto.request.ProfileUpdateRequest;
import com.product.propose.domain.account.web.dto.request.SignUpRequest;
import com.product.propose.domain.account.web.dto.response.InfoResponse;
import com.product.propose.domain.account.web.dto.response.ProfileResponse;
import com.product.propose.global.annotation.CurrentAccount;
import com.product.propose.global.api.RestApiController;
import com.product.propose.domain.account.service.AccountService;
import com.product.propose.global.data.assertion.CommonAssert;
import com.product.propose.global.exception.dto.enums.ErrorCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController(value = "accountRestController")
@RequestMapping(value = "/api/v1/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRestController extends RestApiController {

    // service
    private final AccountService accountService;
    private final AuthServiceAdapter authServiceAdapter;

    // repository
    private final AccountRepository accountRepository;

    public AccountRestController(ObjectMapper objectMapper,
                                 AccountService accountService,
                                 AuthServiceAdapter authServiceAdapter,
                                 AccountRepository accountRepository) {
        super(objectMapper);
        this.accountService = accountService;
        this.authServiceAdapter = authServiceAdapter;
        this.accountRepository = accountRepository;
    }

    // ============================================  Create - Post  ===================================================

    /**
    *   @Author : Youn
    *   @Summary : 유저 가입 로직
    *   @Url : /account/signup
    *   @Param : SignUpRequest
    *   @Memo : Auth 별로 서비스 지원
    **/
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        // Sign Up Logic
        AuthService authService = authServiceAdapter.getService(signUpRequest.getAccountType());
        Account result = authService.signUp(signUpRequest.getSignUpData());

        return createRestResponse(new HashMap<>() {{
            put("accessToken" , result.getJwtToken());
        }});
    }

    /**
     *   @Author : Youn
     *   @Summary : 유저 로그인 로직
     *   @Url : /account/login
     *   @Param : LoginRequest
     *   @Memo : Auth 별로 서비스 지원, 세션 토큰이 생성되므로 Create
     **/
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        // login Logic
        AuthService authService = authServiceAdapter.getService(loginRequest.getAccountType());
        Account result = authService.login(loginRequest);

        return createRestResponse(new HashMap<>() {{
            put("accessToken", result.getJwtToken());
        }});
    }

    // ============================================  Read - Get  ======================================================

    /**
    *   @Author : Youn
    *   @Summary : 로그인시 Simple 사용자 정보 반환
    *   @Url : /account/info
    *   @Param : null
    *   @Memo : Security를 통한 접근 - accessToken
    **/
    @GetMapping("/info")
    public ResponseEntity<String> getInfo(@CurrentAccount Account account) {
        // Check Assertion
        CommonAssert.exists(account, ErrorCode.ACCOUNT_NOT_FOUND);

        // Get Account Info
        InfoResponse infoResponse = accountRepository.readInfoById(account.getId());

        return createRestResponse(new HashMap<>() {{
            put("info", infoResponse);
        }});
    }

    /**
    *   @Author : Youn
    *   @Summary : 프로필 정보 반환
    *   @Url : /account/profile
    *   @Param : null
    *   @Memo : Security를 통한 접근 , 연결 Auth Type 반환.
    **/
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(@CurrentAccount Account account) {
        // Check Assertion
        CommonAssert.exists(account, ErrorCode.ACCOUNT_NOT_FOUND);

        // Get Account Profile
        ProfileResponse profileResponse = accountRepository.readProfileById(account.getId());

        // Get Auth
        List<AccountType> authResponse = accountRepository.readAuthTypeById(account.getId());

        return createRestResponse(new HashMap<>() {{
            put("profile", profileResponse);
            put("auth", authResponse);
        }});
    }

    // ============================================  Update - Put  ====================================================

    /**
    *   @Author : Youn
    *   @Summary : 프로필 정보 수정
    *   @Url : /account/profile/update
    *   @Param : ProfileUpdateRequest
    *   @Memo : AccountId를 통한 변경
    **/
    @PutMapping("/profile/update")
    public ResponseEntity<String> putProfile(@CurrentAccount Account account,
                                             @RequestBody @Valid ProfileUpdateRequest profileUpdateRequest) {
        // Check Assertion
        CommonAssert.exists(account, ErrorCode.ACCOUNT_NOT_FOUND);

        // Update Account & Profile
        Account result = accountService.updateProfile(account.getId(), profileUpdateRequest.getProfileUpdateData());

        return createRestResponse(new HashMap<>() {{
            put("result", result.getId());
        }});
    }

    // ============================================  Delete - delete  =================================================


    /**
     *   @Author : Youn
     *   @Summary : 유저 탈퇴
     *   @Url : /account/exit
     *   @Param : null
     *   @Memo : AccountId를 통한 변경
     **/
    @DeleteMapping("/exit")
    public ResponseEntity<String> exitAccount(@CurrentAccount Account account) {
        // Check Assertion
        CommonAssert.exists(account, ErrorCode.ACCOUNT_NOT_FOUND);

        // Exit Account & Profile
        Account result = accountService.exitAccount(account.getId());

        return createRestResponse(new HashMap<>() {{
            put("result", result.getId());
        }});
    }
}
