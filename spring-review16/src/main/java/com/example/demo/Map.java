package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="map")
public class Map {

	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	private int mapcode;
	private String mapurl;


	//コンストラクタ
	public Map() {

	}

	public Map (int mapcode, String mapurl) {
		this.mapcode = mapcode;
		this.mapurl = mapurl;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getMapcode() {
		return mapcode;
	}

	public void setMapcode(int mapcode) {
		this.mapcode = mapcode;
	}

	public String getMapurl() {
		return mapurl;
	}

	public void setMapurl(String mapurl) {
		this.mapurl = mapurl;
	}




}
