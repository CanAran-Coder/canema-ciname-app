package org.test.canema.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.test.canema.dto.response.AuthUserEmailRole;
import org.test.canema.service.AuthService;
import org.test.canema.dto.request.AuthRequest;

import org.test.canema.entity.Role;
import org.test.canema.entity.User;
import org.test.canema.repository.UserRepository;
import org.test.canema.util.jwt.JwtService;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public ResponseEntity<String> register(AuthRequest authRequest) {

        if(userRepository.existsByEmail(authRequest.email())){
            log.error("Email already exists");
            throw new RuntimeException("Email already exists");


        }

        User user = User.builder().password(passwordEncoder.encode(authRequest.password())).email(authRequest.email()).role(Role.ROLE_CUSTOMER).build();

        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail(),user.getRole().name());
        ResponseCookie cookie = ResponseCookie.from("jwtToken",token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ofDays(1))
                .sameSite("Lax")
                .build();
        log.info("Login Success:{}",user.getEmail());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body("Login Successfully!");

    }

    @Override
    public ResponseEntity<String> logout() {
        ResponseCookie cookie = ResponseCookie.from("jwtToken","")
                .maxAge(0)
                .path("/")
                .httpOnly(true)
                .sameSite("Lax")
                .secure(false)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body("Logged Out Successfully!");

    }


    @Override
    public ResponseEntity<String> login(AuthRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.email()).orElseThrow(()-> new RuntimeException("User not found!"));
        if(!passwordEncoder.matches(authRequest.password(),user.getPassword())){
            log.warn("Wrong password:{}",authRequest.email());
            throw new RuntimeException("Wrong password!");
        }
        String token  = jwtService.generateToken(user.getEmail(),user.getRole().name());
        ResponseCookie cookie = ResponseCookie.from("jwtToken",token)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .maxAge(Duration.ofDays(1))
                .path("/")
                .build();


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body("Login Successfully!");
    }
}
