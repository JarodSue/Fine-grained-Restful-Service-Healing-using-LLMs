package com.example.model;


public class Product {
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
		return "Product [, name=" + name + ", cost=" + cost + "stock=" + stock +"]";
	}
    
}
