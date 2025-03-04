package com.example.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import com.example.model.*;
import com.example.model.Product;
import com.example.config.RequestFilter;
import com.example.Exceptions.BadLoginException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
@RestController
public class UserController {
    private static final Logger LOGGER=LogManager.getLogger(UserController.class);
    public boolean verifToken(String header) {
        try {
            URL url = new URL("http://localhost:1081/ident/TokenVerification");
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestProperty ("Authorization", header);
            connect.setRequestMethod("GET");
            int TokenResponse =connect.getResponseCode();
            connect.disconnect();
            if (TokenResponse==200) {
                return true;
            }
		throw new BadLoginException("Problem with the logging");
        }
        catch(Exception e) {
            throw new BadLoginException("Problem with the logging");
        }
    }
    
    public void deleteAnonymousToken(String token) {
        try {
            URL url = new URL("http://localhost:1081/ident/delete/Anonymous");
            HttpURLConnection connect2 = (HttpURLConnection) url.openConnection();
            connect2.setRequestProperty("Authorization", token);
            connect2.setRequestMethod("DELETE");
            int rep=connect2.getResponseCode();
            connect2.disconnect();
            if(rep==200){
            	return;
		}
            else{
                throw new BadLoginException("Problem with the logging");
		}
        }
        catch(Exception e) {
            throw new BadLoginException("Problem with the logging");
        }
    }
    
    @GetMapping(value="/Products/{id}")
    public ResponseEntity<String>  getProduct(@PathVariable int id, @RequestHeader Map<String, String> headers) throws IOException {
        boolean tokver=false;
                
            tokver=verifToken(headers.get("Authorization"));
            if(tokver) {
                URL url = new URL("http://localhost:1084/prod/Products/"+ id );
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setRequestProperty("Authorization", headers.get("Authorization"));
                connect.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine.trim());
                }
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Authorization", connect.getHeaderField("Authorization"));
                connect.disconnect();
                if("true".equals(headers.get("Anonymous"))){
                    deleteAnonymousToken(headers.get("Authorization"));
               }
                return new ResponseEntity<String>(content.toString(),responseHeaders, HttpStatus.OK);
           }
           throw new BadLoginException("Your token is invalid");
       
       
       
   }
  @PostMapping(value="/Products/{id}/buy")
   public ResponseEntity<?> VerificationCard(@PathVariable int id, @RequestBody int cardNumber, @RequestHeader Map<String, String> headers) throws IOException {
      boolean tokver=false;
          tokver=verifToken(headers.get("Authorization"));
            if(tokver) {
               URL url = new URL("http://localhost:1084/prod/Products/Buy/" + id );
               HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setRequestProperty("Authorization", headers.get("Authorization"));
                connect.setRequestProperty("Content-Type", "application/json; utf-8");
                connect.setDoOutput(true);
               
                connect.setRequestMethod("POST");
               String jsonInputString = "{\"id\":"+cardNumber+"}";
               
               try(OutputStream os = connect.getOutputStream()) {
                   byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);    
               };
               int code=connect.getResponseCode();
               HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Authorization", connect.getHeaderField("Authorization"));
                connect.disconnect();
                if (code==200) {
                    if(headers.get("Anonymous").equals("true")){
                        deleteAnonymousToken(headers.get("Authorization"));
                   }
                    return new ResponseEntity<String>("you buyed the item",responseHeaders,  HttpStatus.OK);
               }
               else {
                    if (code==400) {
                        if(headers.get("Anonymous").equals("true")){
                            deleteAnonymousToken(headers.get("Authorization"));
                       }
                        return  new ResponseEntity<String>("Not enough product in Stock to buy",responseHeaders, HttpStatus.BAD_REQUEST);
                   }
               }
                if(headers.get("Anonymous").equals("true")){
                    deleteAnonymousToken(headers.get("Authorization"));
               }
                return new ResponseEntity<>(code, HttpStatus.INTERNAL_SERVER_ERROR);
           }
           throw new BadLoginException("Your token is invalid");
       }
       
  
	@DeleteMapping(value="/Products/{id}")
   public ResponseEntity<?> deleteProduct(@PathVariable int id, @RequestHeader Map<String, String> headers) throws IOException {
       boolean tokver=false;
           tokver=verifToken(headers.get("Authorization"));
            if(tokver) {
               URL url = new URL("http://localhost:1084/prod/Products/"+ id );
               HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setRequestProperty ("Authorization", headers.get("Authorization"));
                connect.setRequestMethod("DELETE");
		int code=connect.getResponseCode();
               HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Authorization", connect.getHeaderField("Authorization"));
                connect.disconnect();
                if(headers.get("Anonymous").equals("true")){
                    deleteAnonymousToken(headers.get("Authorization"));
               }
               if(code>300){
                                    return new ResponseEntity<String>("Product doesn't exist",responseHeaders, HttpStatus.BAD_REQUEST);
                                }
                return new ResponseEntity<String>(responseHeaders, HttpStatus.OK);
           }
           throw new BadLoginException("Your token is invalid");
       
       
    
    }
    @PutMapping(value="/Products")
    public ResponseEntity<?> putProduct(@RequestBody Product product, @RequestHeader Map<String, String> headers) throws IOException {
        boolean tokver=false;
            tokver=verifToken(headers.get("Authorization"));
            if(tokver) {
                                String prod="{\"name\":\""+product.getName()+"\",\"cost\":"+product.getCost()+",\"stock\":"+product.getStock()+"}";
                URL url = new URL("http://localhost:1084/prod/Products" );
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setRequestProperty ("Authorization", headers.get("Authorization"));
                connect.setRequestMethod("PUT");
                                connect.setRequestProperty("Content-Type","application/json"); 
                                connect.setDoOutput(true);
                                String str =  prod;
                                byte[] outputInBytes = str.getBytes("UTF-8");
                                OutputStream os = connect.getOutputStream();
                                os.write( outputInBytes );    
                                os.close();
                int in =connect.getResponseCode();
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Authorization", connect.getHeaderField("Authorization"));
                connect.disconnect();
                if(headers.get("Anonymous").equals("true")){
                    deleteAnonymousToken(headers.get("Authorization"));
                }
		if(in>300){
                                    return new ResponseEntity<String>("error",responseHeaders, HttpStatus.BAD_REQUEST);
                                }
                return new ResponseEntity<String>(responseHeaders, HttpStatus.OK);
            }
            throw new BadLoginException("Your token is invalid");
        }
        

    
}