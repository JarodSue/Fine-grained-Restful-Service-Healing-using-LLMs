package com.example.config;
import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import com.example.model.ServletRequestAnonymous;


import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class RequestFilter implements Filter{
	private static final Logger LOGGER=LogManager.getLogger(RequestFilter.class);
	private boolean mutableBool =false;
	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {}

	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                if(((HttpServletRequest) servletRequest).getHeader("authorization")==null){
	    		LOGGER.info("Host={}, Dest={}, request-id={}, Action={}, Description=new_Request_Without_Register", 
		    			((HttpServletRequest) servletRequest).getRemoteAddr(), 
		    			((HttpServletRequest) servletRequest).getLocalAddr(), 
		    			((HttpServletRequest) servletRequest).getHeader("x-request-id"), 
		    			((HttpServletRequest) servletRequest).getRequestURL());
	    		String token=createAnonymousToken();
	    		HttpServletRequest req = (HttpServletRequest) servletRequest;
	    		ServletRequestAnonymous mutableRequest = new ServletRequestAnonymous(req);
	    		mutableRequest.putHeader("Authorization", token);
	    		mutableRequest.putHeader("Anonymous", "true");
	    		servletRequest=mutableRequest;
	    		mutableBool=true;
	    	}
	    	else {
	    		HttpServletRequest req = (HttpServletRequest) servletRequest;
	    		ServletRequestAnonymous mutableRequest = new ServletRequestAnonymous(req);
                        mutableRequest.putHeader("Authorization", req.getHeader("Authorization"));
	    		mutableRequest.putHeader("Anonymous", "false");
	    		servletRequest=mutableRequest;
	    	}
	    	LOGGER.info("Host={}, Dest={}, token={}, request-id={}, Action={}, Anonymous={}", 
	    			((HttpServletRequest) servletRequest).getRemoteAddr(), 
	    			((HttpServletRequest) servletRequest).getLocalAddr(), 
	    			((HttpServletRequest) servletRequest).getHeader("Authorization"),
	    			((HttpServletRequest) servletRequest).getHeader("x-request-id"), 
	    			((HttpServletRequest) servletRequest).getRequestURL(),
	    			mutableBool);
	    	
	    	filterChain.doFilter(servletRequest, servletResponse);
	    			
	    	LOGGER.info("Response, Host={}, Dest={}, token={}, request-id={}", 
	    			((HttpServletRequest) servletRequest).getLocalAddr(), 
	    			((HttpServletRequest) servletRequest).getRemoteAddr(),
	    			((HttpServletRequest) servletRequest).getHeader("Authorization"),
	    			((HttpServletRequest) servletRequest).getHeader("x-request-id"));
	    }
	    @Override
	    public void destroy() {}

	    public String createAnonymousToken() {
	    	try {
				URL url = new URL("http://localhost:1081/ident/register/Anonymous");
				HttpURLConnection connect = (HttpURLConnection) url.openConnection();
				connect.setRequestProperty("NewToken", "yes");
				connect.setRequestMethod("POST");
				String token = connect.getHeaderField("Authorization");
				connect.disconnect();
				return token;
			}
			catch(Exception e) {
				return e.toString();
			}
		}
	
}
















