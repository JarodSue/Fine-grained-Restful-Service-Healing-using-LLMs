package com.example.PaymentMicroservice.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Card;

	public interface CardDao extends JpaRepository<Card, Integer> {
		Card findById(int id);
}