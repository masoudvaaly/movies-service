package com.snapppay.movies.configuration;

import com.snapppay.movies.exception.CustomException;
import com.snapppay.movies.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    @Qualifier(value = "handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            addJwtInfoInSecurityContext(request);
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            resolver.resolveException(request, response, null, e);
        }
    }

    private void addJwtInfoInSecurityContext(HttpServletRequest request) throws CustomException {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            String accessToken = headerAuth.substring(7);
            SecurityContextHolder.getContext().setAuthentication(jwtUtils.getAuthentication(accessToken));
        }
    }
}
