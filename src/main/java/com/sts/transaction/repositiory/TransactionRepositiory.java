package com.sts.transaction.repositiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.transaction.entity.Transaction;

public interface TransactionRepositiory extends JpaRepository<Transaction, Long> {
	
	public List<Transaction> findByUserId(long userId);
	
	public void deleteByUserId(long id);

}
