package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StoreController {



	//店舗登録をクリックされた
	@GetMapping("/addstore")
	public ModelAndView addStore(ModelAndView mv) {

		mv.setViewName("addstore");
		return mv;
	}

	//店舗情報が入力されて登録ボタンが押された
	@PostMapping("/addstore")
	public ModelAndView addStore2(ModelAndView mv) {

		mv.setViewName("top");
		return mv;
	}

}
