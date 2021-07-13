package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	HttpSession session;


	//トップページを表示
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mv) {

		Store store = null;
		List<Store> list = storeRepository.findAll();






		mv.setViewName("top");
		return mv;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 新規会員登録がクリックされた
	@GetMapping("/adduser")
	public ModelAndView adduser(ModelAndView mv) {

		mv.setViewName("adduser");
		return mv;
	}

	//新規会員登録情報を入力して新規登録ボタンが押された
	@PostMapping("/adduser")
	public ModelAndView adduser2(
			@RequestParam("name") String name,
			@RequestParam("nickname") String nickname,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			ModelAndView mv) {

		Account account = new Account(name, nickname, address, tel, email, password);

		//追加
		accountRepository.saveAndFlush(account);

		mv.setViewName("top");
		return mv;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//ログインがクリックされた
	@GetMapping("/login")
	public ModelAndView login(ModelAndView mv) {

		mv.setViewName("login");
		return mv;
	}

	//ログイン情報を入力してログインボタンが押された
	@PostMapping("/login")
	public ModelAndView login2(
			@RequestParam("nickname") String nickname,
			@RequestParam("password") String password,
			ModelAndView mv) {


		//未入力の場合のエラー処理
		if (nickname == null || nickname.length() == 0 || password == null || password.length() == 0) {
			mv.addObject("message", "ニックネームとパスワードを入力してください");
			mv.setViewName("login");
		}
		//ログイン処理
		else {
			Account account = null;
			Optional<Account> list = accountRepository.findByNickname(nickname);

			if (list.isEmpty() == false) {
				account = list.get();

				if (password.equals(account.getPassword() ) ){

					session.setAttribute("usercode", account.getCode());
					session.setAttribute("nickname", account.getNickname());
					mv.setViewName("top");
				} else {
					mv.addObject("message", "ニックネームとパスワードが一致しません。");
					mv.setViewName("login");
				}
			}
			else {
				mv.addObject("message", "ニックネームとパスワードが一致しません。");
				mv.setViewName("login");
			}
		}
		return mv;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//ログアウト
	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView mv) {

		session.invalidate();
		mv.setViewName("top");
		return mv;
	}

}
