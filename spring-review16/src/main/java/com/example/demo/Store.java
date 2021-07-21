package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Store")
public class Store {

	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;

	private String name;
	private String categorycode1;
	private String categorycode2;
	private String address;
	private String tel;
	private int budget;
	private int time;
	private int scean;
	private String message;
	private int rank;
	private float rankave;

	//コンストラクタ
	public Store() {
	}

	public Store(String name, String categorycode1, String categorycode2, String address, String tel, int budget,
			int time, int scene, String message) {
		this.name = name;
		this.categorycode1 = categorycode1;
		this.categorycode2 = categorycode2;
		this.address = address;
		this.tel = tel;
		this.budget = budget;
		this.time = time;
		this.scean = scene;
		this.message = message;
	}

	public Store(int code, String name, String categorycode1, String categorycode2, String address, String tel,
			int budget, int time, int scene, String message) {
		this.code = code;
		this.name = name;
		this.categorycode1 = categorycode1;
		this.categorycode2 = categorycode2;
		this.address = address;
		this.tel = tel;
		this.budget = budget;
		this.time = time;
		this.scean = scene;
		this.message = message;
	}

	public Store(int code, String name, String categorycode1, String categorycode2, String address, String tel,
			int budget, int time, int scene, String message, int rank, float rankave) {
		this.code = code;
		this.name = name;
		this.categorycode1 = categorycode1;
		this.categorycode2 = categorycode2;
		this.address = address;
		this.tel = tel;
		this.budget = budget;
		this.time = time;
		this.scean = scene;
		this.message = message;
		this.rank = rank;
		this.rankave = rankave;
	}

	public Store(int code) {

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

	public String getCategorycode1() {
		return categorycode1;
	}

	public void setCategorycode1(String categorycode1) {
		this.categorycode1 = categorycode1;
	}

	public String getCategorycode2() {
		return categorycode2;
	}

	public void setCategorycode2(String categorycode2) {
		this.categorycode2 = categorycode2;
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getRankave() {
		return rankave;
	}

	public void setRankave(float rankave) {
		this.rankave = rankave;
	}

}
