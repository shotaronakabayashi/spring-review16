package com.example.demo;

import java.util.Optional;

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

	@Autowired
	MenuRepository menurepository;



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
			@RequestParam(name = "time1", defaultValue="0") int time1,
			@RequestParam(name = "time2", defaultValue="0") int time2,
			@RequestParam(name = "time3", defaultValue="0") int time3,
			@RequestParam(name = "scean1", defaultValue="0") int scean1,
			@RequestParam(name = "scean2", defaultValue="0") int scean2,
			@RequestParam(name = "scean3", defaultValue="0") int scean3,
			@RequestParam ("message") String message,
			ModelAndView mv) {

		int count = 0;		//何回メニューを登録したかの初期値

		String time0 = ""+ time1 + time2 + time3;
		String scean0 = "" + scean3 + scean2 + scean3;

		int time = Integer.parseInt(time0);
		int scean = Integer.parseInt(scean0);

		//追加
		Store store = new Store(name, categorycode1, categorycode2, categorycode3, address, tel, budget, time,scean,message);
		storeRepository.saveAndFlush(store);

		//コードの取得
		Store store2 = null;
		Optional<Store> list = storeRepository.findByName(name);
		if (list.isEmpty() == false) {
			store2 = list.get();
		}


		mv.addObject("code", store2.getCode());
		mv.addObject("count", count);
		mv.setViewName("addmenu");
		return mv;
	}


	//メニュー情報が入力されて登録するが押された
	@RequestMapping("/addmenu")
	public ModelAndView addmenu2 (
			@RequestParam("menucode") int menucode,
			@RequestParam ("menuname") String menuname,
			@RequestParam("menuprice") int menuprice,
			@RequestParam("count") int count,
			ModelAndView mv ) {

		//何回メニューを登録したか
		count++;

		//メニューを登録
		Menu menu = new Menu(menucode, menuname, menuprice);

		menurepository.saveAndFlush(menu);

		mv.addObject("menucode", menucode);
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
