package com.example.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserDAO;
import com.example.model.JwtRequest;
import com.example.model.JwtResponse;
import com.example.model.User;
import com.example.util.JwtTokenUtil;




@RestController
public class JwtAuthenticationController {
	 @Autowired
	    private PasswordEncoder encoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDAO userDAO;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		final User user = authenticate(authenticationRequest);
		final String token = JwtTokenUtil.generateToken(user);		
		
		return ResponseEntity.ok(new JwtResponse(token, user));
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> createNewUser(@RequestBody JwtRequest authenticationRequest) throws Exception {
                if(userDAO.existsByUsername(authenticationRequest.getUsername())){
                    return new ResponseEntity<>("user with this username Already Exist",HttpStatus.CONFLICT);
                }
		User newUser= new User(authenticationRequest.getUsername(),encoder.encode(authenticationRequest.getPassword()));
		userDAO.save(newUser);
	    return new ResponseEntity<>(newUser,HttpStatus.OK);
	}
	
	@PostMapping(value = "/register/Anonymous")
	public ResponseEntity<?> createNewUserAnonymous() throws Exception {
		User newUser= new User();
		userDAO.save(newUser);
		newUser.setUsername(Integer.toString(newUser.getId()));
		userDAO.save(newUser);
		newUser.setPassword(encoder.encode(Integer.toString(newUser.getId())));
		userDAO.save(newUser);
		JwtRequest req = new JwtRequest(newUser.getUsername(),newUser.getUsername());
		final User user = authenticate(req);
		final String token = JwtTokenUtil.generateToken(user);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", "Bearer " + token);
	    return new ResponseEntity<>(responseHeaders,HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/delete/Anonymous")
	public ResponseEntity<?> deleteNewUserAnonymous( @RequestHeader Map<String, String> headers) throws Exception {
		String tokenstring = headers.get("authorization");
		String tokenstring2 = tokenstring.substring(7);	
		JwtTokenUtil token= new JwtTokenUtil(tokenstring2);
		userDAO.deleteById(userDAO.findByUsername(token.getUsernameFromToken()).getId());
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	private User authenticate(JwtRequest request) throws Exception {
		User user = null;
		
		try {
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			user = (User)auth.getPrincipal();
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		return user;
	}
	
	@GetMapping(value="/TokenVerification")
	private ResponseEntity<?> VerifToken(@RequestHeader Map<String, String> headers) throws Exception {
                String token=headers.get("authorization");
                HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", token);
                return new ResponseEntity<>(responseHeaders,HttpStatus.OK);

	}
	
	
}