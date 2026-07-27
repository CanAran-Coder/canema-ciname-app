package org.test.canema.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.test.canema.entity.User;
import org.test.canema.repository.UserRepository;
import org.test.canema.util.jwt.JwtService;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token  = "";
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }else if(request.getCookies() != null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("jwtToken")) {
                    token = cookie.getValue();
                    break;
                }
            }


            if (jwtService.isTokenValid(token)){
                String email = jwtService.getEmail(token);
                if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){

                    User user = userRepository.findByEmail(email).orElse(null);
                    if(user !=null){
                        var authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
                        var authenticationToken = new UsernamePasswordAuthenticationToken(email,null,authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }

                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
