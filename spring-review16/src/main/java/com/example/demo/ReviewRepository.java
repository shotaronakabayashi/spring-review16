package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	List<Review> findByReviewcode(int reviewcode);

	List<Review> findByReviewname(String name);

	List<Review> findByUsercode(int usercode);

	List<Review> findByStorename(String storename);

}
