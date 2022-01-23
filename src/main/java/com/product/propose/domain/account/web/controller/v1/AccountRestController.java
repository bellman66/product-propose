package com.product.propose.domain.account.web.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.propose.global.api.RestApiController;
import com.product.propose.domain.account.service.AccountService;
import com.product.propose.global.utils.jwt.JwtUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    // ===== ===== ===== ===== ===== Read Business Method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== Update Business Method ===== ===== ===== ===== =====

    // ===== ===== ===== ===== ===== Remove Business Method ===== ===== ===== ===== =====

}
