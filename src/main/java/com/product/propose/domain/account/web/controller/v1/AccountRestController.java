package com.product.propose.domain.account.web.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.service.AuthService;
import com.product.propose.domain.account.service.adapter.AuthServiceAdapter;
import com.product.propose.domain.account.web.dto.mapper.AccountMapper;
import com.product.propose.domain.account.web.dto.request.LoginRequest;
import com.product.propose.domain.account.web.dto.request.SignUpRequest;
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

@RestController(value = "accountRestController")
@RequestMapping(value = "/api/v1/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRestController extends RestApiController {

    // service
    private final AccountService accountService;
    private final AuthServiceAdapter authServiceAdapter;

    public AccountRestController(ObjectMapper objectMapper, AccountService accountService, AuthServiceAdapter authServiceAdapter) {
        super(objectMapper);
        this.accountService = accountService;
        this.authServiceAdapter = authServiceAdapter;
    }

    // ===== ===== ===== ===== ===== Create Business Method ===== ===== ===== ===== =====

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        // Sign Up Logic
        AuthService authService = authServiceAdapter.getService(signUpRequest.getAccountType());
        Account result = authService.signUp(signUpRequest.getSignUpData());

        return createRestResponse(new HashMap<>() {{
            put("accessToken" , result.getJwtToken());
        }});
    }

    // ===== ===== ===== ===== ===== Read Business Method ===== ===== ===== ===== =====

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        // login Logic
        AuthService authService = authServiceAdapter.getService(loginRequest.getAccountType());
        Account result = authService.login(loginRequest);

        return createRestResponse(new HashMap<>() {{
            put("accessToken", result.getJwtToken());
        }});
    }

    /**
    *   @Author : Youn
    *   @Summary : 로그인시 사용자 정보 반환
    *   @Url : /account/info
    *   @Param : null
    *   @Memo : Security를 통한 접근
    **/
    @GetMapping("/info")
    public ResponseEntity<String> info(@CurrentAccount Account account) {
        CommonAssert.isTrue(account != null, ErrorCode.ACCOUNT_NOT_FOUND);

        return createRestResponse(new HashMap<>() {{
            put("accountInfo", AccountMapper.INSTANCE.accountToInfo(account));
        }});
    }

    // ===== ===== ===== ===== ===== Update Business Method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== Remove Business Method ===== ===== ===== ===== =====

}
