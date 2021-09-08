package de.adrianaschepers.shishabuddies.filter;

import de.adrianaschepers.shishabuddies.model.UserEntity;
import de.adrianaschepers.shishabuddies.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//get Token from header and decode it
//verify Token
//if verified: insert user(sub) into security context
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Autowired
    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                String token = authHeader.replace("Bearer", "").trim();


                Claims claims = jwtService.getClaims(token);
                String username = claims.getSubject();
                String role = claims.get("role", String.class);


                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                UserEntity.builder()
                                        .userName(username)
                                        .role(role).build(),
                                "",
                                List.of(new SimpleGrantedAuthority(role))
                        )
                );

               /*if (token != null) {
                    String username = jwtService.decodeUsername(token);
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    UserEntity.builder()
                                            .userName(username)
                                            .role("user")
                                            .build(),
                                    "",
                                    List.of(new SimpleGrantedAuthority("user"))
                            )
                    );*/
                }


        } catch (Exception e) {

            //user will be rejected if not authorized
        }

        filterChain.doFilter(request, response);
    }
}