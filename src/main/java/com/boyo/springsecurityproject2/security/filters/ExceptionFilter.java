package com.boyo.springsecurityproject2.security.filters;

import com.boyo.springsecurityproject2.dtos.APIError;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e){
            e.printStackTrace();
            setErrorResponse(HttpStatus.BAD_REQUEST, response, e);
        } catch ( RuntimeException e) {
            e.printStackTrace();
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, response, e);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable throwable){
        response.setStatus(status.value());
        response.setContentType("application/json");
        var apiError = new APIError(status, throwable.getLocalizedMessage(), throwable);
        try {
            var output = apiError.convertToJson();
            response.getWriter().write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
