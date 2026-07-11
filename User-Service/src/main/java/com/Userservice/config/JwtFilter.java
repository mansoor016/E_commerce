package com.Userservice.config;

import com.Userservice.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomerUserDetailService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Header se token lo
        String authHeader = request.getHeader("Authorization");

        // 2. Token nahi hai — aage jaane do
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. "Bearer " hatao — sirf token lo
        String token = authHeader.substring(7);

        // 4. Token valid hai?
        if (jwtService.isTokenValid(token)) {

            // 5. Token se email nikalo
            String email = jwtService.extractEmail(token);

            // 6. DB se user load karo
            var userDetails = userDetailsService.loadUserByUsername(email);

            // 7. Spring Security ko batao — yeh user authenticated hai
            var authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext()
                    .setAuthentication(authToken);
        }

        // 8. Aage jaane do
        filterChain.doFilter(request, response);
    }
}