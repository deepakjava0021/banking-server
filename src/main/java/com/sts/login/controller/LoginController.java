package com.sts.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sts.login.service.LoginService;
import com.sts.transaction.entity.Transaction;
import com.sts.user.entity.TransactionData;
import com.sts.user.entity.User;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private LoginService lgser;
	
	

	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return lgser.saveUsers(user);
	}

	@GetMapping("/getByEmail")
	public User getByEmail(@RequestParam String email, @RequestParam String password) {
		return lgser.getDetails(email, password);
	}

	@GetMapping("/getAll")
	public List<User> getAll() {
		return lgser.getAllCustomers();
	}
	
	@DeleteMapping("/DeleteById")
	public void Delete(@RequestParam long id) {
		lgser.deleteDetails(id);
	}
	
	@PostMapping("/doTransaction")
	public String handleTransaction (@RequestBody TransactionData transactionData) {
		boolean success = lgser.processTrasaction(transactionData);
		return success ? "transaction complete" : "transaction not complete";
		
	}
	
	@GetMapping("/getTransactions")
	public List<Transaction> getTransaction(@RequestParam("userId") long userId){
		return  lgser.getTransactionByUserId(userId);
		
	}

}
