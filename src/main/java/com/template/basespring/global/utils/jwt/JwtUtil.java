package com.template.basespring.global.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.template.basespring.global.config.props.JwtProps;
import com.template.basespring.global.exception.dto.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtProps jwtProps;
    private final JWTVerifier jwtVerifier;

    public String decodeJwt(String jwtToken) throws AuthenticationException {
        DecodedJWT decodedJWT;

        try {
            decodedJWT = jwtVerifier.verify(jwtToken);
        }
        catch (TokenExpiredException te){
            throw new UsernameNotFoundException(ErrorCode.JWT_TOKEN_EXPIRED.getErrorString());
        }
        catch (JWTVerificationException ve) {
            throw new UsernameNotFoundException(ErrorCode.JWT_VERIFICATION_FAIL.getErrorString());
        }
        catch (Exception e){
            throw new UsernameNotFoundException(ErrorCode.JWT_EXCEPTION_FAIL.getErrorString());
        }
        return decodedJWT.getClaim(jwtProps.getClaimId()).asString();
    }

    public String encodeJwt(String email) {
        return JWT.create()
                .withIssuer(jwtProps.getIssur())
                .withClaim(jwtProps.getClaimId(), email)
                .withExpiresAt(Date.from(ZonedDateTime.now(ZoneId.systemDefault()).plusDays(1).toInstant())) // 하루 설정
                .sign(Algorithm.HMAC256(jwtProps.getSecretkey()));
    }
}
