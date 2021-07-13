package com.example.demo;

import java.util.List;
import java.util.Optional;

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


	//レビューをするがクリックされた
	@GetMapping ("/addreview/{name}")
	public ModelAndView addReview (
			@PathVariable("name") String storename,
			ModelAndView mv) {

		mv.addObject("storename", storename);
		mv.setViewName("addreview");
		return mv;
	}

	//レビュー内容が入力されて送信ボタン押された
	@PostMapping ("/addreview")
	public ModelAndView addReview2 (
			@RequestParam("storename") String storename,
			@RequestParam("reviewname") String reviewname,
			@RequestParam("review") String review,
			@RequestParam("star") int star,
			ModelAndView mv ) {

		//ニックネームからusercodeを取得
		Account account = null;

		Optional<Account> accountlist = accountRepository.findByNickname(reviewname);
		if (accountlist.isEmpty() == false) {
			account = accountlist.get();
		}
		int usercode = account.getCode();

		//店舗名からstorecodeを取得
		Store store = null;

		Optional<Store> storelist = storeRepository.findByName(storename);
		if (storelist.isEmpty() == false) {
			store = storelist.get();
		}
		int storecode = store.getCode();


		//レビュー内容をデータベースに追加
		Review review0 = new Review(storecode,usercode,reviewname,review);
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
		rank = rank / count;

		store2.setRank(rank);

		storeRepository.saveAndFlush(store2);

		mv.addObject("count", count);
		mv.setViewName("store");
		return mv;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//レビューを表示するがクリックされた
	@GetMapping("/review/{name}")
	public ModelAndView review (
			@PathVariable("name") String storename,
			ModelAndView mv
			) {

		Store store = null;
		Optional<Store> list1 = storeRepository.findByName(storename);

		if (list1.isEmpty() == false) {
			store = list1.get();
		}

		int reviewcode = store.getCode();

		List<Review> list = reviewRepository.findByReviewcode(reviewcode);

		mv.addObject("list", list);

		mv.setViewName("review");
		return mv;
	}








}





























