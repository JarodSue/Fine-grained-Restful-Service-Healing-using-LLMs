package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SecuredProxyController {

    @Autowired
    private RestTemplate restTemplate;
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
       // Do any additional configuration here
       return builder.build();
    }

    @DeleteMapping("/prod/Products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://localhost:1082/prod/Products/" + id, HttpMethod.DELETE, entity, Void.class);
    }
}