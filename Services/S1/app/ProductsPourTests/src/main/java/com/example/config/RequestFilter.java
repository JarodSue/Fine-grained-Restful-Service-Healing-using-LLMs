package com.example.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.*;

import java.util.logging.Level;
import java.util.logging.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
@Component
public class RequestFilter implements Filter{
	 private static final Logger LOGGER = Logger.getLogger(RequestFilter.class.getName());
	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {}

	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {    		
                /*ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest)servletRequest);
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse)servletResponse);*/
		
		filterChain.doFilter(servletRequest, servletResponse);
/*
		String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
				servletRequest.getCharacterEncoding());
		String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
				servletResponse.getCharacterEncoding());*/
                /*LOGGER.log(Level.INFO, "arrivée Host='{'{0}{6}'}', Dest='{'{1}'}', token='{'{2}'}', request-id='{'{3}'}', Action='{'{4}'}, Body:'{'{5}'}''", 
                        new Object[]{
                            ((HttpServletRequest) servletRequest).getRemoteAddr(), 
                            ((HttpServletRequest) servletRequest).getLocalAddr(),
                            ((HttpServletRequest) servletRequest).getHeader("Authorization"),
                            ((HttpServletRequest) servletRequest).getHeader("x-request-id"),
                            ((HttpServletRequest) servletRequest).getRequestURL(),
                            ((HttpServletRequest) servletRequest).getInputStream(),
                            ((HttpServletRequest) servletRequest).getLocalPort()
                        });*/
	    		/*LOGGER.log(Level.INFO, "Host='{'{0}{6}'}', Dest='{'{1}{7}'}', token='{'{2}'}', request-id='{'{3}'}', Action='{'{4}'}', Body:'{'{5}"+requestBody+"'}'", 
                        new Object[]{
                            ((HttpServletRequest) servletRequest).getLocalAddr(), 
                            ((HttpServletRequest) servletRequest).getRemoteAddr(),
                            ((HttpServletRequest) servletRequest).getHeader("Authorization"),
                            ((HttpServletRequest) servletRequest).getHeader("x-request-id"),
                            ((HttpServletRequest) servletRequest).getRequestURL(),
                            ((HttpServletRequest) servletRequest).getInputStream(),
                            ((HttpServletRequest) servletRequest).getLocalPort(),
                            ((HttpServletRequest) servletRequest).getRemotePort()
                        });	   */
	    	//filterChain.doFilter(requestWrapper, responseWrapper);
                /*LOGGER.log(Level.INFO, "Host='{'{0}{6}'}', Dest='{'{1}{7}'}', token='{'{2}'}', request-id='{'{3}'}', Action='{'{4}'}', Body:'{'{5}"+requestBody+"'}'", 
                        new Object[]{
                            ((HttpServletRequest) servletRequest).getLocalAddr(), 
                            ((HttpServletRequest) servletRequest).getRemoteAddr(),
                            ((HttpServletRequest) servletRequest).getHeader("Authorization"),
                            ((HttpServletRequest) servletRequest).getHeader("x-request-id"),
                            ((HttpServletRequest) servletRequest).getRequestURL(),
                            ((HttpServletRequest) servletRequest).getInputStream(),
                            ((HttpServletRequest) servletRequest).getLocalPort(),
                            ((HttpServletRequest) servletRequest).getRemotePort()
                        });*/
	    	/*LOGGER.info("Response, Host={}, Dest={}, token={}, request-id={}", 
	    			((HttpServletRequest) servletRequest).getLocalAddr(), 
	    			((HttpServletRequest) servletRequest).getRemoteAddr(),
	    			((HttpServletRequest) servletRequest).getHeader("Authorization"),
	    			((HttpServletRequest) servletRequest).getHeader("x-request-id")); */ 
                /*responseWrapper.copyBodyToResponse();*/
	    }

	    @Override
	    public void destroy() {}
	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
		try {
			return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
