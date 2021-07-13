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
	ReviewRepository reviewreposiyory;








	//レビュー画面を表示するがクリックされた
	@GetMapping ("/review")
	public ModelAndView review (ModelAndView mv) {

		mv.setViewName("review");
		return mv;
	}

	//レビュー内容が入力されて送信ボタン押された
	@PostMapping ("/addreview")
	public ModelAndView addReview (
			@RequestParam("reviewcode") int reviewcode,
			@RequestParam("usercode") int usercode,
			@RequestParam("reviewname") int reviewname,
			@RequestParam("review") String review,
			ModelAndView mv ) {



		//String ninkname = session.getAttribute("nickname");

		mv.setViewName("top");
		return mv;
	}











}
