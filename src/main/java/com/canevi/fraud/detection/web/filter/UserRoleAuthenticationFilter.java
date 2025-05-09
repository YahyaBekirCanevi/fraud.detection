package com.canevi.fraud.detection.web.filter;

import com.canevi.fraud.detection.web.client.UserServiceClient;
import com.canevi.fraud.detection.web.dto.UserDetailResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRoleAuthenticationFilter extends OncePerRequestFilter {

    private final UserServiceClient userServiceClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String userId = request.getHeader("X-User-Id");

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetailResponse userDetail = userServiceClient.getUserDetails(userId);

            List<GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + userDetail.role())
            );

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userDetail.userId(), null, authorities
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
