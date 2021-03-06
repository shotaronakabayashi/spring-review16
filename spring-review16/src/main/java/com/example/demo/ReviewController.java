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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	MapRepository mapRepository;

	@Autowired
	PictureRepository pictureRepository;

	@Autowired
	HttpSession session;

	//レビューをするがクリックされた
	@GetMapping("/addreview/{name}/{code}")
	public ModelAndView addReview(
			@PathVariable("name") String storename,
			@PathVariable("code") int storecode,
			ModelAndView mv) {

		//ゲストがレビューしようとした際のエラー処理
		List<Store> restorelist = new ArrayList<>();
		Store store0 = null;

		if ((String) session.getAttribute("nickname") == null) {
			Optional<Store> list0 = storeRepository.findById(storecode);
			store0 = list0.get();
			restorelist.add(store0);
			float rankave = store0.getRankave();
			mv.addObject("storelist", restorelist);
			mv.addObject("rankave", rankave);
			//メニュー情報を送る
			List<Menu> menulist = menuRepository.findByMenucode(storecode);
			mv.addObject("menulist", menulist);
			//写真の情報を送る
			List<Picture> picturelist = pictureRepository.findByPicturecode(storecode);
			mv.addObject("picturelist", picturelist);
			//マップの情報を送る
			List<Map> maplist = mapRepository.findByMapcode(storecode);
			Map map = null;
			if (maplist.isEmpty() == false) {
				map = maplist.get(0);
				String mapurl = map.getMapurl();
				mv.addObject("mapurl", mapurl);
			}
			//レビューの情報を送る
			List<Review> reviewlist = reviewRepository.findByReviewcode(storecode);
			mv.addObject("reviewlist", reviewlist);
			//レビューの数を送る
			int count = 0;
			for (Review r : reviewlist) {
				count++;
			}
			mv.addObject("count", count);

			mv.addObject("message", "ログインしてください。");
			mv.setViewName("store");
			return mv;
		}

		mv.addObject("storename", storename);
		mv.setViewName("addreview");
		return mv;
	}

	//レビュー内容が入力されて送信ボタン押された
	@PostMapping("/addreview")
	public ModelAndView addReview2(
			@RequestParam("storename") String storename,
			@RequestParam("reviewname") String reviewname,
			@RequestParam("review") String review,
			@RequestParam("star") int star,
			ModelAndView mv) {

		//ニックネームからusercodeを取得
		Account account = null;

		Optional<Account> accountlist = accountRepository.findByNickname(reviewname);
		if (accountlist.isEmpty() == false) {
			account = accountlist.get();
		}
		int usercode = account.getCode();

		//店舗名からstorecodeを取得
		Store store = null;

		Optional<Store> storelist0 = storeRepository.findByName(storename);
		if (storelist0.isEmpty() == false) {
			store = storelist0.get();
		}
		//情報返還用//////////////////////////////
		List<Store> storelist = new ArrayList<>();
		storelist.add(store);
		///////////////////////////////////////////

		int storecode = store.getCode();

		//レビュー内容をデータベースに追加
		Review review0 = new Review(storecode, usercode, reviewname, review, star, storename);
		reviewRepository.saveAndFlush(review0);

		//店舗の現在のランクを取得
		Store store2 = null;
		Optional<Store> list1 = storeRepository.findById(storecode);

		if (list1.isEmpty() == false) {
			store2 = list1.get();
		}

		//その店舗をレビューした人数を取得
		List<Review> list2 = reviewRepository.findByReviewcode(storecode);

		int count = 0;
		for (Review s : list2) {
			count++;
		}

		//付けられた星の数の平均をStoreDBに追加

		int rank = store2.getRank() + star;
		float rankave = (float) rank / (float) count;

		store2.setRank(rank);
		store2.setRankave(rankave);

		storeRepository.saveAndFlush(store2);

		//店舗詳細ページ用の情報を送る

		//店舗情報を送る
		mv.addObject("storelist", storelist);

		//メニュー情報を送る
		List<Menu> menulist = menuRepository.findByMenucode(storecode);
		mv.addObject("menulist", menulist);

		//写真の情報を送る
		List<Picture> picturelist = pictureRepository.findByPicturecode(storecode);
		mv.addObject("picturelist", picturelist);

		//マップの情報を送る
		List<Map> maplist = mapRepository.findByMapcode(storecode);
		if (maplist.isEmpty() == false) {
			Map map = maplist.get(0);
			String mapurl = map.getMapurl();
			mv.addObject("mapurl", mapurl);
		}
		//レビューの情報を送る
		List<Review> reviewlist = reviewRepository.findByReviewcode(storecode);
		mv.addObject("reviewlist", reviewlist);
		mv.addObject("rankave", rankave);

		mv.addObject("count", count);
		mv.setViewName("store");
		return mv;
	}

}
