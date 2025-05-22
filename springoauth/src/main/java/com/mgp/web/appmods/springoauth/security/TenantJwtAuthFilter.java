package com.mgp.web.appmods.springoauth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class TenantJwtAuthFilter extends OncePerRequestFilter {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUriTemplate;

    //required for long running api requests
    private RequestAttributeSecurityContextRepository repository = new RequestAttributeSecurityContextRepository();
    private final Map<String, JwtDecoder> jwtDecoders = new ConcurrentHashMap<>();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = resolveToken(request);
        String realm = request.getHeader("X-REALM");

        try{
            Authentication auth = validateToken(token, realm);
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);
            this.repository.saveContext(context,request,response);
            filterChain.doFilter(request,response);
        }catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private Authentication validateToken(String token, String tenant) {
        try{
            JwtDecoder jwtDecoder = jwtDecoders.computeIfAbsent(tenant, this::createJwtDecoder);
            Jwt jwt = jwtDecoder.decode(token);
            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
            return new JwtAuthenticationToken(jwt, authorities);
        }catch(Exception e){
            throw new RuntimeException( "token validation failed");
        }
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if(realmAccess != null) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            if(roles != null) {
                return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    private JwtDecoder createJwtDecoder(String realm) {
        String jwkSetUri = String.format(jwkSetUriTemplate, realm);
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }
}
