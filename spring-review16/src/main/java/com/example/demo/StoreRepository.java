package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer>{
	Optional<Store> findByName(String name);

	List<Store> findByNameLike(String keyword);

	List<Store> findByCategorycode1(String categorycode1);

	List<Store> findByCategorycode2(String categorycode2);

	List<Store> findByCategorycode3(String categorycode3);

}
