package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<Account, Integer> {
	Optional<Account> findByNickname(String nickname);

}