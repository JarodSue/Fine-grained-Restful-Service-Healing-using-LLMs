package com.example.model;

public class Card {

	private int id;



	public Card() {
	}

	public Card(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	
	

	@Override
	public String toString() {
		return "Card [id=" + id +"]";
	}

}
