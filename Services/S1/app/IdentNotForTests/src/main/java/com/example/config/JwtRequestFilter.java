package com.example.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.service.JwtUserDetailsService;
import com.example.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOG = LogManager.getLogger(JwtRequestFilter.class);

	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		    
        
       
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(jwtToken);
			
			try {
				LOG.info("Host={}, Dest={}, token={}, request-id={}, Action={}", 
		    			((HttpServletRequest) request).getRemoteAddr(), 
		    			((HttpServletRequest) request).getLocalAddr(),
		    			((HttpServletRequest) request).getHeader("Authorization"),
		    			((HttpServletRequest) request).getHeader("x-request-id"),
						((HttpServletRequest) request).getRequestURL());
				
				username = jwtTokenUtil.getUsernameFromToken();
				validateToken(jwtTokenUtil, username, chain, request, response);   		
				chain.doFilter(request, response);
				LOG.info("Response, Host={}, Dest={}, token={}, request-id={}", 
						((HttpServletRequest) request).getLocalAddr(), 
						((HttpServletRequest) request).getRemoteAddr(), 
						((HttpServletRequest) request).getHeader("Authorization"),
						((HttpServletRequest) request).getHeader("x-request-id"));
		    			  
				
				
			} catch (IllegalArgumentException e) {
				LOG.error("Unable to get JWT Token", e);
			} catch (ExpiredJwtException e) {
				LOG.error("JWT Token has expired", e);
			}
		} else {
			Enumeration<String> s = request.getHeaderNames();
			ArrayList<String> headers= new ArrayList();
			while (s.hasMoreElements()) {
			      headers.add(s.nextElement());
			    }
			if(headers.contains("NewToken")) {
				if ((!request.getHeader("NewToken").isEmpty()) && (!(request.getHeader("NewToken")==null))) {
					LOG.info("Host={}, Dest={}, tokenCreation=true, request-id={}, Action={}", 
			    			((HttpServletRequest) request).getRemoteAddr(), 
			    			((HttpServletRequest) request).getLocalAddr(),
			    			((HttpServletRequest) request).getHeader("x-request-id"),
							((HttpServletRequest) request).getRequestURL());
					 chain.doFilter(request, response);
				        LOG.info("Response, Host={}, Dest={}, request-id={}, token={}", 
								((HttpServletRequest) request).getLocalAddr(), 
								((HttpServletRequest) request).getRemoteAddr(), 
								((HttpServletRequest) request).getHeader("x-request-id"),
				        		((HttpServletResponse) response).getHeader("Authorization"));
						
				}
			}
			else {
			

	        chain.doFilter(request, response);


			}
		}
	}
	
	
	private void validateToken(JwtTokenUtil jwtTokenUtil, String username, FilterChain chain, HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		
	}
}
