package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Store")
public class Store {



	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;

	private String name;
	private String address;
	private String tel;
	private int categorycode1;
	private int categorycode2;
	private int categorycode3;
	private int time;
	private int scean;
	private int budget;
	private String messege;
	private int rank;

	public Store () {

	}

	public Store (int code) {

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getCategorycode1() {
		return categorycode1;
	}

	public void setCategorycode1(int categorycode1) {
		this.categorycode1 = categorycode1;
	}

	public int getCategorycode2() {
		return categorycode2;
	}

	public void setCategorycode2(int categorycode2) {
		this.categorycode2 = categorycode2;
	}

	public int getCategorycode3() {
		return categorycode3;
	}

	public void setCategorycode3(int categorycode3) {
		this.categorycode3 = categorycode3;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getScean() {
		return scean;
	}

	public void setScean(int scean) {
		this.scean = scean;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}






}
