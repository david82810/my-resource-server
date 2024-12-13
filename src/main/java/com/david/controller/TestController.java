package com.david.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class TestController {
    @GetMapping("/test")
    public Map<String, String> test(Authentication authentication) {
        log.info("authentication:{}", authentication);
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String username = jwt.getClaimAsString("sub");
        log.info("username:{}", username);

        return Map.of("message", "Hello World", "status", "ok");
    }

    @PreAuthorize("hasAuthority('SCOPE_write')")
    @GetMapping("/test2")
    public String tes2() {
        return "Hello World";
    }

}
