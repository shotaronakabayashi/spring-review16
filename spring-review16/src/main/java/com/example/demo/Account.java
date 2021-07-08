package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class Account {

	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;

	private String name;
	private String nickname;
	private String address;
	private String tel;
	private String email;
	private String password;

	//コンストラクタ
	public Account () {

	}

	public Account (int code, String name, String nickname, String address, String tel, String email, String password ) {
		this.code = code;
		this.name = name;
		this.nickname = nickname;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.password = password;
	}

	public Account (String name, String nickname, String address, String tel, String email, String password) {
		this.name = name;
		this.nickname = nickname;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.password = password;
	}



	//セッター・ゲッター
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


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}




}
