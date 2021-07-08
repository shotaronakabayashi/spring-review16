package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="menu")
public class Menu {


	//フィールド
	@Id
	private int menucode;
	private String menuname;
	private int menuprice;

	//コンストラクタ
	public Menu() {

	}

	public Menu (int menucode, String menuname, int menuprice) {
		this.menucode = menucode;
		this.menuname = menuname;
		this.menuprice = menuprice;
	}



	//セッター・ゲッター
	public int getMenucode() {
		return menucode;
	}

	public void setMenucode(int menucode) {
		this.menucode = menucode;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public int getMenuprice() {
		return menuprice;
	}

	public void setMenuprice(int menuprice) {
		this.menuprice = menuprice;
	}







}
