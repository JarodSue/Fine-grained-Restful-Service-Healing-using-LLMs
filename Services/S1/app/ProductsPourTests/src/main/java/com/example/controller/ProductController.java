package com.example.controller;

import java.io.IOException;



import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.Exceptions.BadLoginException;
import com.example.Exceptions.ProductNotFoundException;
import com.example.config.RequestFilter;
import com.example.model.Product;
import com.example.dao.ProductDao;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.example.model.ProductForClient;
import com.example.model.Card;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


@RestController
public class ProductController {
	@Autowired
	private ProductDao productDao;
	private static final Logger LOGGER=LoggerFactory.getLogger(RequestFilter.class);
	public boolean verifToken(String header) {
		try {
			URL url = new URL("http://localhost:1080/ident/TokenVerification");
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
	@GetMapping(value="/Products")
	public ResponseEntity<?> listProductsForClient(@RequestHeader Map<String, String> headers) {
		boolean tokver=false;
		if(headers.containsKey("authorization")) {
			tokver=verifToken(headers.get("authorization"));
			if(tokver) {
				List<Product> products = productDao.findAll();
				List<ProductForClient> productsForClient = new ArrayList<ProductForClient>();
				for(Product produit: products) {
					productsForClient.add(new ProductForClient(produit.getId(),produit.getName(), produit.getCost()));
				}
				return new ResponseEntity<>(productsForClient, HttpStatus.OK);
			}
			throw new BadLoginException("Your token is invalid");
		}
		throw new BadLoginException("You don't have a token, register at https://localhost:1080/register to get one");
	}
	@GetMapping(value="/Admin/Products")
	public ResponseEntity<MappingJacksonValue> listProducts(@RequestHeader Map<String, String> headers) {
		boolean tokver=false;
		if(headers.containsKey("authorization")) {
			tokver=verifToken(headers.get("authorization"));
			if(tokver) {
				List<Product> products = productDao.findAll();
				MappingJacksonValue produitsFiltres = new MappingJacksonValue(products);
				return new ResponseEntity<>(produitsFiltres,HttpStatus.OK);
			}
			throw new BadLoginException("Your token is invalid");
		}
		throw new BadLoginException("You don't have a token, register at https://localhost:1080/register to get one");
	}

	//Récupérer un produit par son Id
	@GetMapping(value = "/Admin/Products/{id}")
	public ResponseEntity<Product> getAProductAdmin(@PathVariable int id, @RequestHeader Map<String, String> headers) {
		boolean tokver=false;
		if(headers.containsKey("authorization")) {
			tokver=verifToken(headers.get("authorization"));
			if(tokver) {
				Product produit = productDao.findById(id);
				if(produit==null) throw new ProductNotFoundException("The product with ID " + id + " does not exist in the database.");
                                HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("Authorization", headers.get("authorization"));
				return new ResponseEntity<>(produit,responseHeaders,HttpStatus.OK);
			}
			throw new BadLoginException("Your token is invalid");
		}
		throw new BadLoginException("You don't have a token, register at https://localhost:1080/register to get one");
	}
	@GetMapping(value = "/Products/{id}")
	public ResponseEntity<ProductForClient> getAProduct(@PathVariable int id, @RequestHeader Map<String, String> headers) {
		boolean tokver=false;
		String autho=headers.get("authorization");
		if(autho!=null) {
			tokver=verifToken(autho);
			if(tokver) {
				Product produit = productDao.findById(id);
				if(produit==null) throw new ProductNotFoundException("The product with ID " + id + " does not exist in the database.");
				ProductForClient productForClient = new ProductForClient(produit.getId(),produit.getName(), produit.getCost());
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("Authorization", autho);
				return new ResponseEntity<>(productForClient,responseHeaders, HttpStatus.OK);
			}
			throw new BadLoginException("Your token is invalid");
		}
		throw new BadLoginException("You don't have a token, register at https://authenticationmicroservices.herokuapp.com/register to get one");
	}

	//ajouter un produit
	@PostMapping(value = "/Admin/Products/ajout")
	public ResponseEntity<?> newProduct(@RequestBody Product product, @RequestHeader Map<String, String> headers) {
		boolean tokver=false;
		if(headers.containsKey("authorization")) {
			tokver=verifToken(headers.get("authorization"));
                        if((product.getName().equals(""))||(product.getName().equals("null"))){
                            HttpHeaders responseHeaders = new HttpHeaders();
                            responseHeaders.set("Authorization", headers.get("authorization"));
                            return new ResponseEntity<>(responseHeaders, HttpStatus.BAD_REQUEST);
                        }
			if(tokver) {
                                if(productDao.existsByName(product.getName())){
                                    HttpHeaders responseHeaders = new HttpHeaders();
                                    responseHeaders.set("Authorization", headers.get("authorization"));
                                    return new ResponseEntity<>("product with this name already exist", responseHeaders, HttpStatus.CONFLICT);
                                }
				productDao.save(product);
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("Authorization", headers.get("authorization"));
				return new ResponseEntity<>(product,responseHeaders, HttpStatus.OK);
			}
			throw new BadLoginException("Your token is invalid");
		}
		throw new BadLoginException("You don't have a token, register at https://authenticationmicroservices.herokuapp.com/register to get one");
	}
	@DeleteMapping (value = "/Products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id, @RequestHeader Map<String, String> headers) {
		boolean tokver=false;
		if(headers.containsKey("authorization")) {
			tokver=verifToken(headers.get("authorization"));
			if(tokver) {
				Product produit = productDao.findById(id);
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("Authorization", headers.get("authorization"));
				if(produit==null) {
					return new ResponseEntity<>("Product doesn't exist",responseHeaders,HttpStatus.BAD_REQUEST);
				}
				productDao.deleteById(id);
				return new ResponseEntity<>("Product Deleted",responseHeaders,HttpStatus.OK);
			}
			throw new BadLoginException("Your token is invalid");
		}
		throw new BadLoginException("You don't have a token, register at https://authenticationmicroservices.herokuapp.com/register to get one");
	}
	//sert a update des préexistants
	@PutMapping (value = "/Products")
	public ResponseEntity<?> updateProductFromAdmin(@RequestBody Product product, @RequestHeader Map<String, String> headers) {
		boolean tokver=false;
		if(headers.containsKey("authorization")) {
			tokver=verifToken(headers.get("authorization"));
			if(tokver) {
                                Product produit = productDao.findByName(product.getName());
                                product.setId(produit.getId());
				if(produit==null) throw new ProductNotFoundException("The product with name " + product.getName() + " does not exist in the database.");
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("Authorization", headers.get("authorization"));
				productDao.save(product);
				return new ResponseEntity<>(responseHeaders,HttpStatus.OK);
			}
			throw new BadLoginException("Your token is invalid");
		}
		throw new BadLoginException("You don't have a token, register at https://authenticationmicroservices.herokuapp.com/register to get one");
	}
	public void updateFromBuy(Product product) {
				productDao.save(product);
	}
	@PostMapping(value = "/Products/Buy/{id}")
	public ResponseEntity<String> productBuyed(@PathVariable int id, @RequestBody Card cardNumber, @RequestHeader Map<String, String> headers) throws IOException {
		boolean tokver=false;
		if(headers.containsKey("authorization")) {
			tokver=verifToken(headers.get("authorization"));
			if(tokver) {
				Product product=productDao.findById(id);
				URL url = new URL("http://localhost:1080/pay/Card/"+product.getCost()+"/"+ cardNumber.getId());
				HttpURLConnection connect = (HttpURLConnection) url.openConnection();
				connect.setRequestProperty("Authorization", headers.get("authorization"));
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("Authorization", headers.get("authorization"));
				connect.setRequestMethod("GET");
				connect.disconnect();
				int cardResponse =connect.getResponseCode();
				if(product.getStock()<1){
                                    return new ResponseEntity<>("no products in stock",responseHeaders, HttpStatus.BAD_REQUEST);
                                }
				if (cardResponse==200) {
					Product product2=new Product(product.getName(),product.getCost(),product.getStock()-1);
					updateFromBuy(product2);
					return new ResponseEntity<>("Product Buyed",responseHeaders, HttpStatus.OK);
				}
				if (cardResponse==400) {
					return new ResponseEntity<>("not enough money to buy the product",responseHeaders, HttpStatus.BAD_REQUEST);
				}
				else {
/*Changement ici*/
return null;
				}
			}
			throw new BadLoginException("Your token is invalid");
		}
		throw new BadLoginException("You don't have a token, register at https://authenticationmicroservices.herokuapp.com/register to get one");
	}
        @GetMapping(value = "/Drop")
        public void dropDatabaseProductsAndRecreateIt() throws SQLException{
        String jdbcURL = "jdbc:h2:mem:testdb";
        Connection connection = DriverManager.getConnection(jdbcURL,"sa","");
        System.out.println("Connected to H2 in-memory database.");
        String sql = "TRUNCATE TABLE PRODUCT";
        Statement statement = connection.createStatement();
        statement.execute(sql);
        String sql2="ALTER TABLE PRODUCT ALTER COLUMN ID RESTART WITH 1";
        Statement statement2 = connection.createStatement();
        statement2.execute(sql2);
        
        
        }
}

