package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView category (
			@PathVariable("category") String category,
			ModelAndView mv ) {

		//３つのカテゴリーのどこに含まれているか照合して送信
		List<Store> list1 = storeRepository.findByCategorycode1(category);
		List<Store> list2 = storeRepository.findByCategorycode1(category);
		List<Store> list3 = storeRepository.findByCategorycode1(category);

		if (list1.isEmpty() == false) {
			mv.addObject("result", list1);
		} else if  (list2.isEmpty() == false) {
			mv.addObject("result", list2);
		} else if  (list3.isEmpty() == false) {
			mv.addObject("result", list3);
		}

		mv.addObject("category", category);
		mv.setViewName("result");
		return mv;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//詳細検索
	public ModelAndView SerchDetail (
		@RequestParam("categorycode1") String categorycode1,
		@RequestParam("categorycode2") String categorycode2,
		@RequestParam("categorycode3")
		String categorycode3,
				ModelAndView mv ) {

		List<Store> list = new ArrayList<>();
		List<Store> list0 = new ArrayList<>();

		//カテゴリーの検索
		List<Store> list1 = storeRepository.findByCategorycode1(categorycode1);
		List<Store> list2 = storeRepository.findByCategorycode2(categorycode2);
		List<Store> list3 = storeRepository.findByCategorycode3(categorycode3);

		//時間帯の検索



		//値段の検索



		//場所の検索


		//シーンの検索


		////カテゴリー１のみ選択された
		//if ("".equals(categorycode2) && "".equals(categorycode3) ) {
		//mv.addObject("list", list1);
		//}
		////カテゴリー2のみが選択された
		//else if ("".equals(categorycode1) && "".equals(categorycode3) ) {
		//mv.addObject("list", list2);
		//}
		////カテゴリー3のみが選択された
		//else if ("".equals(categorycode1) && "".equals(categorycode2) ) {
		//mv.addObject("list", list3);
		//}
		//
		////カテゴリー1と2が選択された
		//else if ("".equals(categorycode3) ) {
		//for (Store a : list1) {
		//	if (list2.contains(a) ) {
		//		list.add(a);
		//	}
		//}
		//mv.addObject("list", list);
		//}
		////カテゴリー２と３が選択された
		//else if ("".equals(categorycode1)) {
		//for (Store a : list2) {
		//	if (list3.contains(a) ){
		//		list.add(a);
		//	}
		//	mv.addObject("list", list);
	//}
	//}
	////カテゴリー１と３が選択された
	//else if ("".equals(categorycode2) ) {
	//for (Store a : list1) {
	//	if (list3.contains(a) ) {
	//		list.add(a);
	//	}
	//}
	//mv.addObject("list", list);
	//}
	////カテゴリー1と２と３が選択
	//else {
	//for (Store a : list1) {
	//	if (list2.contains(a) ) {
	//		list0.add(a);
	//	}
	//}
	//for (Store a : list0) {
	//	if (list3.contains(a) ) {
	//		list.add(a);
	//	}
	//}
	//mv.addObject("list", list);
	//}
	//
	mv.setViewName("result");
	return mv;
	}

}
