package com.sts.login.repositiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.user.entity.User;

public interface LoginRepository  extends JpaRepository<User, Long>{

	User getByEmailAndPassword(String email , String password);
}


