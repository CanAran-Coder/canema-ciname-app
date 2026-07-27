package org.test.canema.service;

import org.springframework.http.ResponseEntity;
import org.test.canema.dto.request.AuthRequest;
import org.test.canema.dto.response.AuthUserEmailRole;


public interface AuthService {
    public ResponseEntity<String> login(AuthRequest authRequest);
    public ResponseEntity<String> register(AuthRequest authRequest);
    public ResponseEntity<String> logout();
}
