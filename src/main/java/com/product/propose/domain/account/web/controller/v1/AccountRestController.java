package com.product.propose.domain.account.web.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.domain.account.entity.aggregate.Account;
import com.product.propose.domain.account.web.dto.request.SignUpRequest;
import com.product.propose.global.api.RestApiController;
import com.product.propose.domain.account.service.AccountService;
import com.product.propose.global.utils.jwt.JwtUtil;
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

    // util
    private final JwtUtil jwtUtil;

    public AccountRestController(ObjectMapper objectMapper, AccountService accountService, JwtUtil jwtUtil) {
        super(objectMapper);
        this.accountService = accountService;
        this.jwtUtil = jwtUtil;
    }

    // ===== ===== ===== ===== ===== Create Business Method ===== ===== ===== ===== =====

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        Account result = accountService.signUpForDefault(signUpRequest.getSignUpData());

        return createRestResponse(new HashMap<>() {{
            put("accountId" , result.getId());
        }});
    }

    // ===== ===== ===== ===== ===== Read Business Method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== Update Business Method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== Remove Business Method ===== ===== ===== ===== =====

}
