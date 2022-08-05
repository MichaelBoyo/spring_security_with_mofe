package com.boyo.springsecurityproject2.security.filters;

import com.boyo.springsecurityproject2.security.jwt.JwtConfig;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var authHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        var tokenPrefix = jwtConfig.getTokenPrefix();

        if (Strings.isNullOrEmpty(authHeader) || !authHeader.startsWith(tokenPrefix)) {
            filterChain.doFilter(request, response);
            return;
        }

        var jwt = authHeader.replace(tokenPrefix, "");
        var jwtClaim = getClaim(jwt);
        String username = jwtClaim.getSubject();

        List<Map<String, String>> x = (List<Map<String, String>>) jwtClaim.get("authorities");
        log.info("auth --> {}", x);
        Set<SimpleGrantedAuthority> authorities = x.stream()
                .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                .collect(Collectors.toSet());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                username,
                null,
                authorities
        );
        log.info("setting security context --> {}", authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private Claims getClaim(String jwt) {
        var claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt);
        return claims.getBody();
    }
}
