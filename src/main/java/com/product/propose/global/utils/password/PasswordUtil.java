package com.product.propose.global.utils.password;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private static PasswordEncoder passwordEncoder;

    public PasswordUtil(PasswordEncoder passwordEncoder) {
        PasswordUtil.passwordEncoder = passwordEncoder;
    }

    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean matches(String password, String encodePassword) {
        return passwordEncoder.matches(password, encodePassword);
    }
}
