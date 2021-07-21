package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class Store2 {

	@Autowired
	StoreRepository storeRepository;

	//店舗詳細ページのメソッド
	public Store storedetail(int storecode) {

		Optional<Store> list = storeRepository.findById(storecode);
		Store store = list.get();

		return store;
	}

}
