package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer>{
	Optional<Store> findByName(String name);

	List<Store> findByNameLike(String keyword);

	List<Store> findByAddressLike(String address);

	List<Store> findByCategorycode1(String categorycode1);

	List<Store> findByCategorycode2(String categorycode2);

	List<Store> findByTime(int time);

	List<Store> findByScean(int scean);

	List<Store> findByBudgetGreaterThan(int budget);



//	List<Store> findByBudgetBetween(int price1, int price2);
//
//	//詳細検索
//	List<Store> findByNameLikeAndAddressLikeAndCategorycode1AndCategorycode2AndTimeAndBudgetBetweenAndScean
//	(String keyword, String address, String categorycode1, String categorycode2, int time, int price1,int price2, int scean);
//
//	List<Store> findByNameLikeAndAddressLikeAndCategorycode1AndCategorycode2AndTimeAndScean
//	(String keyword, String address, String categorycode1, String categorycode2, int time,int scean);
//
//
//	List<Store> findByNameLikeAndAddressLike(String name, String address);

}
