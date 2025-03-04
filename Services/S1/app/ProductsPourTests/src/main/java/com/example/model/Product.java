package com.example.model;

import java.io.Serializable;
import javax.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.*;

@Entity
@Table
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Length(min=3, max=20, message = "Nom trop long ou trop court.")
	private String name;
	private int cost;
	private int stock;

	public Product() {
	}

	public Product(String name, int cost, int stock) {
		this.name=name;
		this.cost=cost;
		this.stock=stock;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", cost=" + cost + "stock=" + stock +"]";
	}


}
