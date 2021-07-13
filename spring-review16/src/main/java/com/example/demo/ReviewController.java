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


	//レビュー画面を表示するがクリックされた
	@GetMapping ("/review")
	public ModelAndView addReview (ModelAndView mv) {

		mv.setViewName("review");
		return mv;
	}

	//レビュー内容が入力されて送信ボタン押された
	@PostMapping ("/addreview")
	public ModelAndView addReview2 (
			@RequestParam("reviewcode") int storecode,
			@RequestParam("usercode") int usercode,
			@RequestParam("reviewname") String reviewname,
			@RequestParam("review") String review,
			@RequestParam("star") int star,
			ModelAndView mv ) {

		//レビュー内容をデータベースに追加
		Review review0 = new Review(storecode,usercode,reviewname,review);
		reviewRepository.saveAndFlush(review0);

		//付けられた星の数をStoreDBに追加
		Store store = null;
		Optional<Store> list = storeRepository.findById(storecode);

		if (list.isEmpty() == false) {
			store = list.get();
		}

		int rank = store.getRank() + star;
		store.setRank(rank);

		storeRepository.saveAndFlush(store);

		mv.setViewName("top");
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

		Review review = null;
		List<Review> list = reviewRepository.findByReviewcode(reviewcode);

		mv.addObject("list", list);

		mv.setViewName("review");
		return mv;
	}








}





























