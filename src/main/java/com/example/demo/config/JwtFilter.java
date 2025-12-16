package com.example.demo.config;

import jarkata.servlet.FilterChain;
import jarkata.servlet.ServletException;
import jarkata.servlet.http.HttpServletRequest;
import jarkata.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter{

    private final JwtUtil jwtUtil;
    private final UserDetailsService UserDetailsService;

    public JwtFilter(JwtUtil,jwtUtil,UserDetailsService UserDetailsService){
        this.jwtUtil=jwtUtil;
        this.UserDetailsService=userDetailsService
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
    throws ServletException,IOException{
        
        String authHeader=request.getHeader("Authorization");
        String username=null;
        String token=null;

        if(authHeader != null && authHeader.startsWith("Bearer")){
            token=authHeader.substring(7);
            username=jwtUtil.extractUsername(token);

        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userdetails=userDetailsService.loadUserByUsername(username);

            if(jwtUtil.validateToken(token,UserDetails)){
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
            }
        }
    }
}

public class JwtFilter{

}