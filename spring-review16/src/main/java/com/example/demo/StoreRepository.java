package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer>{
	Optional<Store> findByName(String name);

//	List<Store> findByNameLike(String keyword);
//
//	List<Store> findByCategory(String categorycode1);

}
