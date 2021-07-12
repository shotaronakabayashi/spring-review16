package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {



	//レビュー画面を表示するがクリックされた
	@GetMapping ("/review")
	public ModelAndView review (ModelAndView mv) {

		mv.setViewName("review");
		return mv;
	}

	//レビュー内容が入力されて送信ボタン押された
	@PostMapping ("/addreview")
	public ModelAndView addReview
			(ModelAndView mv ) {

		mv.setViewName("top");
		return mv;
	}











}
