package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Integer>{

	List<Map> findByMapcode(int mapcode);
}
