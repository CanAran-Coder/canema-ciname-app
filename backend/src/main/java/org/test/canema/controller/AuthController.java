package org.test.canema.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.test.canema.dto.request.AuthRequest;
import org.test.canema.dto.response.AuthResponse;
import org.test.canema.dto.response.AuthUserEmailRole;
import org.test.canema.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PreAuthorize("permitAll")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }
    @PreAuthorize("permitAll")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);

    }
    @PreAuthorize("permitAll")
    @GetMapping("/me")
    public ResponseEntity<AuthUserEmailRole> me(Authentication authentication) {
        if(authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
        String email = authentication.getName();
        String role  = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_CUSTOMER");
        return ResponseEntity.ok(new AuthUserEmailRole(email,role));
    }
    @PreAuthorize("permitAll")
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return authService.logout();

    }
}
