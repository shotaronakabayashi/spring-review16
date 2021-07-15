package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	ReviewRepository reviewRepository;

	@Autowired
	PictureRepository pictureRepository;

	@Autowired
	HttpSession session;

	//トップページを表示
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mv) {

		//トップページのランキング表示------------------------
		List<Store> list = storeRepository.findAll();

		float best1 = 0;
		float best2 = 0;
		float best3 = 0;
		float other = 0;
		for (Store st : list) {
			for (Store s : list) {
				float a = s.getRankave();
				if (a >= best1) {
					best1 = a;
				} else if (a < best1 && a >= best2) {
					best2 = a;
				} else if (a < best2 && a >= best3) {
					best3 = a;
				} else {
					other = a;
				}
			}
		}

		//ランクの情報から店舗情報を取得
		//1位
		List<Store> list1 = storeRepository.findByRankave(best1);

		//2位
		List<Store> list2 = storeRepository.findByRankave(best2);

		//3位
		List<Store> list3 = storeRepository.findByRankave(best3);

		mv.addObject("list1", list1);
		mv.addObject("list2", list2);
		mv.addObject("list3", list3);

		//店舗の画像の情報を取得
		Store store1 = list1.get(0);
		int storecode1 = store1.getCode();
		List<Picture> picturelist1 = pictureRepository.findByPicturecode(storecode1);
		mv.addObject("picturelist1", picturelist1);

		Store store2 = list2.get(0);
		int storecode2 = store2.getCode();
		List<Picture> picturelist2 = pictureRepository.findByPicturecode(storecode2);
		mv.addObject("picturelist2", picturelist2);

		Store store3 = list3.get(0);
		int storecode3 = store3.getCode();
		List<Picture> picturelist3 = pictureRepository.findByPicturecode(storecode3);
		mv.addObject("picturelist3", picturelist3);

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
		//エラー処理
		if (name.length() == 0 || nickname.length() == 0 || address.length() == 0 || tel.length() == 0
				|| email.length() == 0 || password.length() == 0) {
			mv.addObject("message", "すべての項目に入力してください。");
			mv.setViewName("adduser");
			return mv;
		}

		//ニックネームの重複チェック
		//全体
		List<Account> listall = accountRepository.findAll();
		for (Account a : listall) {
			if (a.getNickname().equals(nickname)); {
				mv.addObject("message", "ニックネームが登録されています。");
				mv.setViewName("adduser");
				return mv;
			}
		}


		Account account = new Account(name, nickname, address, tel, email, password);

		//追加
		accountRepository.saveAndFlush(account);

		//トップページのランキング表示-------------------------------
		List<Store> list = storeRepository.findAll();

		float best1 = 0;
		float best2 = 0;
		float best3 = 0;
		float other = 0;
		for (Store st : list) {
			for (Store s : list) {
				float a = s.getRankave();
				if (a >= best1) {
					best1 = a;
				} else if (a < best1 && a >= best2) {
					best2 = a;
				} else if (a < best2 && a >= best3) {
					best3 = a;
				} else {
					other = a;
				}
			}
		}

		//ランクの情報から店舗情報を取得
		//1位
		List<Store> list1 = storeRepository.findByRankave(best1);

		//2位
		List<Store> list2 = storeRepository.findByRankave(best2);

		//3位
		List<Store> list3 = storeRepository.findByRankave(best3);

		mv.addObject("list1", list1);
		mv.addObject("list2", list2);
		mv.addObject("list3", list3);
		//-----------------------------------------------------------------

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

				if (password.equals(account.getPassword())) {

					session.setAttribute("usercode", account.getCode());
					session.setAttribute("nickname", account.getNickname());
					mv.setViewName("top");
				} else {
					mv.addObject("message", "ニックネームとパスワードが一致しません。");
					mv.setViewName("login");
				}
			} else {
				mv.addObject("message", "ニックネームとパスワードが一致しません。");
				mv.setViewName("login");
			}
		}

		//トップページのランキング表示-----------------------------
		List<Store> list = storeRepository.findAll();

		float best1 = 0;
		float best2 = 0;
		float best3 = 0;
		float other = 0;
		for (Store st : list) {
			for (Store s : list) {
				float a = s.getRankave();
				if (a >= best1) {
					best1 = a;
				} else if (a < best1 && a >= best2) {
					best2 = a;
				} else if (a < best2 && a >= best3) {
					best3 = a;
				} else {
					other = a;
				}
			}
		}

		//ランクの情報から店舗情報を取得
		//1位
		List<Store> list1 = storeRepository.findByRankave(best1);

		//2位
		List<Store> list2 = storeRepository.findByRankave(best2);

		//3位
		List<Store> list3 = storeRepository.findByRankave(best3);

		mv.addObject("list1", list1);
		mv.addObject("list2", list2);
		mv.addObject("list3", list3);
		//------------------------------------------------------------
		return mv;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//「マイページに行く」がクリックされた
	@GetMapping("mypage/{nickname}")
	public ModelAndView mypage(
			@PathVariable("nickname") String nickname,
			ModelAndView mv) {

		//マイページの内容
		List<Review> list = reviewRepository.findByReviewname(nickname);


		mv.addObject("list", list);
		mv.addObject("nickname", nickname);
		mv.setViewName("mypage");
		return mv;

	}

	//マイページから登録情報変更がクリックされた	遷移
	@GetMapping("/changeuser/{nickname}")
	public ModelAndView changeUser(
			@PathVariable("nickname") String nickname,
			ModelAndView mv) {

		Account account = null;
		Optional<Account> list0 = accountRepository.findByNickname(nickname);
		account = list0.get();
		List<Account> list = new ArrayList<>();
		list.add(account);

		//ユーザーのコード
		int code = account.getCode();

		mv.addObject("code", code);
		mv.addObject("list", list);
		mv.setViewName("changeuser");
		return mv;
	}

	//登録情報の変更内容が入力された
	@PostMapping("/changeuser")
	ModelAndView changeUser2(
			@RequestParam("code") int code,
			@RequestParam("name") String name,
			@RequestParam("nickname") String nickname,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			ModelAndView mv) {

		Account account = new Account(code, name, nickname, address, tel, email, password);

		//追加
		accountRepository.saveAndFlush(account);

		List<Review> list = reviewRepository.findByUsercode(code);

		session.invalidate();
		session.setAttribute("nickname", nickname);

		mv.addObject("list", list);
		mv.addObject("nickname", nickname);

		mv.setViewName("mypage");
		return mv;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//ログアウト
	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView mv) {

		//トップページのランキング表示---------------------------
		List<Store> list = storeRepository.findAll();

		float best1 = 0;
		float best2 = 0;
		float best3 = 0;
		float other = 0;
		for (Store st : list) {
			for (Store s : list) {
				float a = s.getRankave();
				if (a >= best1) {
					best1 = a;
				} else if (a < best1 && a >= best2) {
					best2 = a;
				} else if (a < best2 && a >= best3) {
					best3 = a;
				} else {
					other = a;
				}
			}
		}

		//ランクの情報から店舗情報を取得
		//1位
		List<Store> list1 = storeRepository.findByRankave(best1);

		//2位
		List<Store> list2 = storeRepository.findByRankave(best2);

		//3位
		List<Store> list3 = storeRepository.findByRankave(best3);

		mv.addObject("list1", list1);
		mv.addObject("list2", list2);
		mv.addObject("list3", list3);
		//--------------------------------------------------------

		session.invalidate();
		mv.setViewName("top");
		return mv;
	}

}
