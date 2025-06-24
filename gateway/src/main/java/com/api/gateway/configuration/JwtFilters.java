package com.api.gateway.configuration;
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
        if (path.startsWith("/quizmicro") || path.startsWith("/question-service/question/getScore")) {
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
            System.out.println("🔐 Validating Token: " + token);
            jwtUtil.validateToken(token);
            System.out.println("✅ Token is valid.");
            return true;
        } catch (Exception e) {
            System.out.println("❌ Invalid Token: " + e.getMessage());
            return false;
        }
    }

}
