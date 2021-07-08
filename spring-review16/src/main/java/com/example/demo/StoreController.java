package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StoreController {

	@Autowired
	StoreRepository storeRepository;



	//店舗登録をクリックされた
	@GetMapping("/addstore")
	public ModelAndView addStore(ModelAndView mv) {

		mv.setViewName("addstore");
		return mv;
	}

	//店舗情報が入力されて登録ボタンが押された
	@PostMapping("/addstore")
	public ModelAndView addStore2(
			@RequestParam ("name") String name,
			@RequestParam ("categorycode1") String categorycode1,
			@RequestParam ("categorycode2") String categorycode2,
			@RequestParam ("categorycode3") String categorycode3,
			@RequestParam ("address") String address,
			@RequestParam ("tel") String tel,
			@RequestParam ("budget") String budget,
			@RequestParam ("message") String message,
			ModelAndView mv) {

		Store store = new Store();
		//追加
		storeRepository.saveAndFlush(store);

		mv.setViewName("top");
		return mv;
	}

}
