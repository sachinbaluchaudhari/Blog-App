package com.blog.app.jwtSecurity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    private Logger logger= LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        String userName=null;
        String token=null;
        if (requestHeader!=null && requestHeader.startsWith("Bearer"))
        {
            token=requestHeader.substring( 7);
            try{
              userName=  jwtHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException ex){
                logger.info("Illegal argument while fetching username!!");
                ex.printStackTrace();
            }catch (ExpiredJwtException ex)
            {
                logger.info("Given Jwt token expired!");
                ex.printStackTrace();
            }catch (MalformedJwtException ex)
            {
                logger.info("Some changes has done in token!! Invalid token");
                ex.printStackTrace();
            }catch (Exception ex)
            {
                logger.info("Exception occured!!");
                ex.printStackTrace();
            }
        }else {
            logger.info("Invalid Request Header!!");
        }
        if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            Boolean validateToken = jwtHelper.validateToken(token, userDetails);
            if (validateToken) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } else {
                logger.info("Validation failed!!");
            }
        }
       filterChain.doFilter(request,response);
    }
}
