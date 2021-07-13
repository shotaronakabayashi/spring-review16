package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {


	@Autowired
	ReviewRepository reviewRepository;


	//レビュー画面を表示するがクリックされた
	@GetMapping ("/review")
	public ModelAndView review (ModelAndView mv) {

		mv.setViewName("review");
		return mv;
	}

	//レビュー内容が入力されて送信ボタン押された
	@PostMapping ("/addreview")
	public ModelAndView addReview (
			@RequestParam("reviewcode") int storecode,
			@RequestParam("usercode") int usercode,
			@RequestParam("reviewname") String reviewname,
			@RequestParam("review") String review,
			ModelAndView mv ) {

		Review review0 = new Review(storecode,usercode,reviewname,review);
		reviewRepository.saveAndFlush(review0);

		mv.setViewName("top");
		return mv;
	}











}
