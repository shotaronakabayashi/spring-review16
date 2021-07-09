package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SerchController {

	@Autowired
	StoreRepository storeRepository;



	//キーワード検索
	public ModelAndView SerchText(
			@RequestParam("keyword") String keyword,
			ModelAndView mv) {

		//入力されたキーワードを名前に含む店舗
		List<Store> list = storeRepository.findByNameLike("%" + keyword + "%");

		mv.addObject("list", list);

		mv.setViewName("result");
		return mv;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//カテゴリー検索
	public ModelAndView category (ModelAndView mv) {


		mv.setViewName("result");
		return mv;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//詳細検索
	public ModelAndView SerchDetail (


			ModelAndView mv) {


		mv.setViewName("result");
		return mv;
	}





}




















