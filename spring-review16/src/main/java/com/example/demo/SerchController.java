package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SerchController {

	@Autowired
	StoreRepository storeRepository;

	//キーワード検索
	@RequestMapping("/keyword")
	public ModelAndView SerchText(
			@RequestParam("keyword") String keyword,
			ModelAndView mv) {

		//入力されたキーワードを名前に含む店舗
		List<Store> list = storeRepository.findByNameLike("%" + keyword + "%");

		mv.addObject("result", list);

		mv.setViewName("result");
		return mv;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//カテゴリー検索
	@RequestMapping("/keyword/{category}")
	public ModelAndView category(
			@PathVariable("category") String category,
			ModelAndView mv) {

		//３つのカテゴリーのどこに含まれているか照合して送信
		List<Store> list1 = storeRepository.findByCategorycode1(category);
		List<Store> list2 = storeRepository.findByCategorycode2(category);

		if (list1.isEmpty() == false) {
			mv.addObject("result", list1);
		} else if (list2.isEmpty() == false) {
			mv.addObject("result", list2);
		}

		mv.setViewName("result");
		return mv;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//トップ画面から詳細検索がクリックされた
	@GetMapping("/search/detail")
	public ModelAndView SearchDetail(ModelAndView mv) {

		mv.setViewName("searchDetail");
		return mv;
	}


	//詳細検索
	@PostMapping("/search/detail")
	public ModelAndView SearchDetail2(
			@RequestParam("keyword") String keyword,
			@RequestParam("address") String address,
			@RequestParam("categorycode1") String categorycode1,
			@RequestParam("categorycode2") String categorycode2,
			@RequestParam(name="time1", defaultValue = "0" ) String time1,
			@RequestParam(name = "time2", defaultValue="0") int time2,
			@RequestParam(name = "time3", defaultValue="0") int time3,
			@RequestParam("minprice") int price1,
			@RequestParam("maxprice") int price2,
			@RequestParam(name = "scean1", defaultValue="0") int scean1,
			@RequestParam(name = "scean2", defaultValue="0") int scean2,
			@RequestParam(name = "scean3", defaultValue="0") int scean3,
			ModelAndView mv) {

		String time0 = "" + time1 + time2 + time3;
		String scean0 = "" + scean1 + scean2 + scean3;

		int time = Integer.parseInt(time0);
		int scean = Integer.parseInt(scean0);

		List<Store> list1 = storeRepository.findByBudgetBetween(price1,price2);

		List<Store> list2 = 	storeRepository.findByNameLikeAndAddressLikeAndCategorycode1AndCategorycode2AndTimeAndScean
				("%" + keyword + "%", "%" + address + "%",categorycode1,categorycode2,time,scean);

		List<Store> list = new ArrayList<>();

		for (Store s : list1) {
			if (list2.contains(s) ) {
				list.add(s);
			}
		}

		mv.addObject("result", list);

		mv.setViewName("result");
		return mv;
	}

}


























