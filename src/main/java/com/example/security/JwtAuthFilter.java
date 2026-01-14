package com.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtAuthFilter extends OncePerRequestFilter {

    AuthUtil authUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                log.info("incoming request : { }",request.getRequestURI());


                /*     DOING FOR BEARER AUTHORIZATION
                        THE HEADER OF THE TOKEN WILL BE TAKEN CARE OF
                        SECURITY CONTEXT HOLDER IN JWT
                 */

                final String authorizationHeader = request.getHeader("Authorization");

                if(authorizationHeader == null ||  !authorizationHeader.startsWith("Bearer "))
                {
                    filterChain.doFilter(request,response);
                    return;

                }

                String token = authorizationHeader.split("Bearer ")[1];

                JwtUserPrincipal user = authUtil.verifyAccessToken(token);

                if(user != null && SecurityContextHolder.getContext().getAuthentication() == null)
                {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                            (user,null, user.authorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }

                filterChain.doFilter(request,response);



    }
}
