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
	private String reviewname;
	private String review;
	private int star;
	private String storename;


	//コンストラクタ
	public Review () {

	}

	public Review (int reviewcode, int usercode, String reviewname, String review, int star, String storename) {
		this.reviewcode = reviewcode;
		this.usercode = usercode;
		this.reviewname = reviewname;
		this.review = review;
		this.star = star;
		this.storename = storename;
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

	public String getReviewname() {
		return reviewname;
	}

	public void setReviewname(String reviewname) {
		this.reviewname = reviewname;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}























}
