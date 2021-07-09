package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Picture")
public class Picture {



	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	private int picturecode;
	private String pictureurl;


	//コンストラクタ
	public Picture () {

	}

	public Picture (int picturecode, String pictureurl) {
		this.picturecode = picturecode;
		this.pictureurl = pictureurl;
	}


	//ゲッターセッター
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getPicturecode() {
		return picturecode;
	}
	public void setPicturecode(int picturecode) {
		this.picturecode = picturecode;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}


























}
