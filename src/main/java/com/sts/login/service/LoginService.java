package com.sts.login.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.login.repositiory.LoginRepository;
import com.sts.transaction.entity.Transaction;
import com.sts.transaction.repositiory.TransactionRepositiory;
import com.sts.user.entity.TransactionData;
import com.sts.user.entity.User;

import jakarta.transaction.Transactional;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private TransactionRepositiory transRepo;

	public User saveUsers(User user) {
		return loginRepository.save(user);
	}

	public User getDetails(String email, String password) {
		return loginRepository.getByEmailAndPassword(email, password);
	}

	public List<User> getAllCustomers() {
		return loginRepository.findAll();
	}

	@Transactional
	public void deleteDetails(long id) {
		loginRepository.deleteById(id);
		transRepo.deleteByUserId(id);
	}

	public boolean processTrasaction(TransactionData transactionData) {

		User user = loginRepository.findById(transactionData.getUserId()).orElse(null);
		if (user == null) {
			return false;
		}
		if ("Withdraw".equals(transactionData.getType())) {
			if (user.getBalance() < transactionData.getAmount()) {
				return false;
			}
			user.setBalance(user.getBalance() - transactionData.getAmount());
		} else if ("Deposit".equals(transactionData.getType())) {
			user.setBalance(user.getBalance() + transactionData.getAmount());
		} else {
			return false;
		}
		loginRepository.save(user);

		Transaction transaction = new Transaction(transactionData.getUserId(), transactionData.getType(),
				transactionData.getAmount(), new Date());

		transRepo.save(transaction);

		return true;
	}

	public List<Transaction> getTransactionByUserId(long userId) {
		return transRepo.findByUserId(userId);
	}
}
