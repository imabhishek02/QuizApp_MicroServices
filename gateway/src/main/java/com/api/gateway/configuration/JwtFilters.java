package com.api.gateway.filter;

import com.api.gateway.configuration.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtFilters implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtils jwtUtil;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // Only apply filter for restricted routes
        if (path.startsWith("/quizmicro") || path.startsWith("/question")) {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7);
            // Add your JWT validation logic here
            boolean isValid = validateToken(token);
            if (!isValid) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Optional: You can also add claims or user info in headers
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // High precedence
    }

    private boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token); // will throw exception if invalid
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
