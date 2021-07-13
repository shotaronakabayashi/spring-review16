package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="review")
public class Review {


	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	private int reviewcode;
	private int usercode;
	private String review;


	//コンストラクタ
	public Review () {

	}

	public Review (int reviewcode, int usercode, String review) {
		this.reviewcode = reviewcode;
		this.usercode = usercode;
		this.review = review;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getReviewcode() {
		return reviewcode;
	}

	public void setReviewcode(int reviewcode) {
		this.reviewcode = reviewcode;
	}

	public int getUsercode() {
		return usercode;
	}

	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}





















}
