package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

	//トップページを表示
	@RequestMapping ("/")
	public ModelAndView index (ModelAndView mv) {




		mv.setViewName("top");
		return mv;
	}



	// 新規会員登録がクリックされた
	@GetMapping("/adduser")
	public ModelAndView adduser (ModelAndView mv) {


		mv.setViewName("adduser");
		return mv;
	}


	//新規会員登録情報を入力して新規登録ボタンが押された
	@PostMapping("/adduser")
	public ModelAndView adduser2 (ModelAndView mv) {


		mv.setViewName("top");
		return mv;
	}



	//ログインがクリックされた
	@GetMapping("/login")
	public ModelAndView login (ModelAndView mv) {

		mv.setViewName("login");
		return mv;
	}


	//ログイン情報を入力してログインボタンが押された
	@PostMapping("login")
	public ModelAndView login2 (ModelAndView mv) {

		mv.setViewName("top");
		return mv;
	}

}
