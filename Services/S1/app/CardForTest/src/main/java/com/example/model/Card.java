package com.example.model;


import javax.persistence.*;


@Entity
@Table
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int money;
    
   

	public Card() {
    }
    
    public Card(int id, int money) {
    	this.id=id;
    	this.money=money;
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	 public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "Card [id=" + id + ", Money=" + money +"]";
	}
}