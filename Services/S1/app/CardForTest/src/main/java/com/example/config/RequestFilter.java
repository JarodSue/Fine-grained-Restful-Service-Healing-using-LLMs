package com.example.config;
import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.io.IOException;

import io.jsonwebtoken.ExpiredJwtException;
@Component
public class RequestFilter implements Filter{
	private static final Logger LOGGER=LogManager.getLogger(RequestFilter.class);
	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {}

	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {    		
	    	/*LOGGER.info("Host={}, Dest={}, token={}, request-id={}, Action={}", 
	    			((HttpServletRequest) servletRequest).getRemoteAddr(), 
	    			((HttpServletRequest) servletRequest).getLocalAddr(), 
	    			((HttpServletRequest) servletRequest).getHeader("Authorization"),
	    			((HttpServletRequest) servletRequest).getHeader("x-request-id"), 
	    			((HttpServletRequest) servletRequest).getRequestURL()); */       
	    	filterChain.doFilter(servletRequest, servletResponse);
	    	/*LOGGER.info("Response, Host={}, Dest={}, token={}, request-id={}", 
	    			((HttpServletRequest) servletRequest).getLocalAddr(), 
	    			((HttpServletRequest) servletRequest).getRemoteAddr(),
	    			((HttpServletRequest) servletRequest).getHeader("Authorization"),
	    			((HttpServletRequest) servletRequest).getHeader("x-request-id")); */ 
	    }

	    @Override
	    public void destroy() {}
	
}
