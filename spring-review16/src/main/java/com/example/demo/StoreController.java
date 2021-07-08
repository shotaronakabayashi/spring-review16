package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
			@RequestParam ("categorycode1") int categorycode1,
			@RequestParam ("categorycode2") int categorycode2,
			@RequestParam ("categorycode3") int categorycode3,
			@RequestParam ("address") String address,
			@RequestParam ("tel") String tel,
			@RequestParam ("budget") int budget,
			@RequestParam("time1") int time1,
			@RequestParam("time2") int time2,
			@RequestParam("time3") int time3,
			@RequestParam("scean1") int scean1,
			@RequestParam("scean2") int scean2,
			@RequestParam("scean3") int scean3,
			@RequestParam ("message") String message,
			ModelAndView mv) {

		int time = 100*time1 + 10*time2 + time3;
		int scean = 100*scean3 + 10*scean2 + scean3;

		Store store = new Store(name, categorycode1, categorycode2, categorycode3, address, tel, budget, time,scean,message);
		//追加
		storeRepository.saveAndFlush(store);

		mv.setViewName("addmenu");
		return mv;
	}

	//メニュー情報が入力されて登録するが押された
	@PostMapping("/addmenu")
	public ModelAndView addmenu2 (
			@RequestParam ("menuname") String menuname,
			@RequestParam("menuprice") int menuprice,
			@RequestParam("count") int count,
			ModelAndView mv ) {

		count++;

		mv.addObject("count", count);
		mv.setViewName("addmenu");
		return mv;
	}




	//写真追加
	@RequestMapping("/addpicture")
	public ModelAndView addpicture (

			ModelAndView mv) {


		return mv;
	}






}
