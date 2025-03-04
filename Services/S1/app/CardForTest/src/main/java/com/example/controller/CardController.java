package com.example.controller;
import com.example.dao.CardDao;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.Exceptions.BadLoginException;
import com.example.model.Card;
import com.example.Exceptions.CardCheckedException;
import com.example.Exceptions.NoId0Exception;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class CardController {



    @Autowired
    private CardDao creditCard;
    
    public boolean verifToken(String header) {
        try {

            URL url = new URL("http://localhost:1081/ident/TokenVerification");
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestProperty ("Authorization", header);
            connect.setRequestMethod("GET");
            connect.disconnect();
            int cardResponse =connect.getResponseCode();
            if (cardResponse==200) {
                return true;
            }
            return false;
        }
        catch(Exception e) {
            return false;
        }
    }
    
    
    public boolean checkIfMoneyCardMoreThan0(int id) {

        Card checkedCreditCard = creditCard.findById(id);
        if(checkedCreditCard==null) throw new CardCheckedException("the card with id" + id + "is not registered in the database");
        else{
            if(checkedCreditCard.getMoney()>0) {
                return true;
            }
        }
/*Changement ici*/
        return true;
    }
    @GetMapping(value = "/Card/{id}")
    public ResponseEntity<?> getACard(@PathVariable int id, @RequestHeader Map<String, String> headers) {
        boolean tokver=false;
        if(headers.containsKey("authorization")) {
            tokver=verifToken(headers.get("authorization"));
            if(tokver) {
                if(!creditCard.existsById(id)){
                    return new ResponseEntity<>("card does not exist",HttpStatus.BAD_REQUEST);
                }
            Card checkedCreditCard = creditCard.findById(id);
            HttpHeaders responseHeaders = new HttpHeaders();
                            responseHeaders.set("Authorization", headers.get("authorization"));
                                            creditCard.save(checkedCreditCard);
                                            return new ResponseEntity<>(checkedCreditCard,responseHeaders,HttpStatus.OK);
                            }
                    
                    throw new BadLoginException("Your token is invalid");
        }
        throw new BadLoginException("You don't have a token, register at https://authenticationmicroservices.herokuapp.com/register to get one");

    }

    
    @GetMapping(value = "/Card/{cost}/{id}")
    public ResponseEntity<?> BuyAnItem(@PathVariable Map<String, String> pathVariables, @RequestHeader Map<String, String> headers) {
        boolean tokver=false;
        if(headers.containsKey("authorization")) {
            tokver=verifToken(headers.get("authorization"));
            if(tokver) {
                            HttpHeaders responseHeaders = new HttpHeaders();
                            responseHeaders.set("Authorization", headers.get("authorization"));
                            int id = Integer.parseInt(pathVariables.get("id"));
                            int cost = Integer.parseInt(pathVariables.get("cost"));
                            if (creditCard.existsById(id)) {
                                Card checkedCreditCard = creditCard.findById(id);
                            if(checkedCreditCard.getMoney()<cost) {
                                    return new ResponseEntity<>("card don't have enough money",responseHeaders,HttpStatus.BAD_REQUEST);
                            }
                            else{
                                            checkedCreditCard.setMoney(checkedCreditCard.getMoney()-cost);
                                            creditCard.save(checkedCreditCard);
                                            return new ResponseEntity<>(responseHeaders,HttpStatus.OK);
                            }
                        }
                        else {
                            return new ResponseEntity<>("card don't exist",responseHeaders,HttpStatus.BAD_REQUEST);
                        }
                    }
                    throw new BadLoginException("Your token is invalid");
        }
        throw new BadLoginException("You don't have a token, register at https://authenticationmicroservices.herokuapp.com/register to get one");

    }


    //sert a update des préexistants
    @PutMapping (value = "/Card")
    public ResponseEntity<Card> updateCard(@RequestBody Card card, @RequestHeader Map<String, String> headers) {
        boolean tokver=false;
        if(headers.containsKey("authorization")) {
            tokver=verifToken(headers.get("authorization"));
            if(tokver) {
                            try{
                                card.getId();
                                if(card.getId()==0){
                                    throw new CardCheckedException("card does not exist");
                                }
                            }
                            catch(Exception e){
                                throw new CardCheckedException("card does not exist");
                            }
                            if (creditCard.findById(card.getId())==null){
                                throw new CardCheckedException("card does not exist");
                            }
                            HttpHeaders responseHeaders = new HttpHeaders();
                            responseHeaders.set("Authorization", headers.get("authorization"));
                            creditCard.save(card);
                            return new ResponseEntity<>(card,responseHeaders,HttpStatus.OK);
            }
            throw new BadLoginException("Your token is invalid");
        }
        throw new BadLoginException("You don't have a token, register at https://authenticationmicroservices.herokuapp.com/register to get one");

    }
    
    @PostMapping(value = "/Card/ajout")
    public ResponseEntity<Card> newCard(@RequestBody Card card, @RequestHeader Map<String, String> headers) {
        boolean tokver=false;
        if(headers.containsKey("authorization")) {
            tokver=verifToken(headers.get("authorization"));
            if(tokver) {
                            
                            HttpHeaders responseHeaders = new HttpHeaders();
                            responseHeaders.set("Authorization", headers.get("authorization"));
                            if (creditCard.findById(card.getId())!=null){
                                throw new NoId0Exception("card already exist");
                            }
                            creditCard.save(card);
                            if(card.getId()==0){
                                    deleteMethod(0);
                                    throw new NoId0Exception("You can't create card with Id = 0");
                                }
                            return new ResponseEntity<>(card,responseHeaders,HttpStatus.OK);
            }
            throw new BadLoginException("Your token is invalid");
        }
        throw new BadLoginException("You don't have a token, register at https://authenticationmicroservices.herokuapp.com/register to get one");

    }
    
    @DeleteMapping(value = "/Card/delete/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable int id, @RequestHeader Map<String, String> headers) {
        boolean tokver=false;
        if(headers.containsKey("authorization")) {
            tokver=verifToken(headers.get("authorization"));
            if(tokver) {
                            
                            HttpHeaders responseHeaders = new HttpHeaders();
                            responseHeaders.set("Authorization", headers.get("authorization"));
                            if (creditCard.findById(id)!=null){
                                deleteMethod(id);
                                return new ResponseEntity<>("Delete worked as expected",responseHeaders,HttpStatus.OK);
                            }
                            else{
                                throw new CardCheckedException("card does not exist");
                            }
                            
                            
            }
            throw new BadLoginException("Your token is invalid");
        }
        throw new BadLoginException("You don't have a token, register at https://authenticationmicroservices.herokuapp.com/register to get one");

    }
    
    public Card deleteMethod(int id){
        Card card = creditCard.findById(id);
        creditCard.delete(card);
        return card;
    }
}

