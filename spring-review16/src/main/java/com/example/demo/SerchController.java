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

		//エラー処理
		if (keyword.length() == 0) {
			mv.addObject("message", "店舗名を入力してください");
			mv.setViewName("top");
			return mv;
		} else {

			//入力されたキーワードを名前に含む店舗
			List<Store> list = storeRepository.findByNameLike("%" + keyword + "%");

			mv.addObject("result", list);

			mv.setViewName("result");
			return mv;
		}
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
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "categorycode1", defaultValue = "") String categorycode1,
			@RequestParam(name = "categorycode2", defaultValue = "") String categorycode2,
			@RequestParam(name = "time1", defaultValue = "0") int time1,
			@RequestParam(name = "time2", defaultValue = "0") int time2,
			@RequestParam(name = "time3", defaultValue = "0") int time3,
			@RequestParam(name = "minprice", defaultValue = "0") int price1,
			@RequestParam(name = "maxprice", defaultValue = "10000000") int price2,
			@RequestParam(name = "scean1", defaultValue = "0") int scean1,
			@RequestParam(name = "scean2", defaultValue = "0") int scean2,
			@RequestParam(name = "scean3", defaultValue = "0") int scean3,
			ModelAndView mv) {

		//価格エラー処理
		if (price1 > price2) {
			mv.addObject("message", "価格を正しく入力してください。");
			mv.setViewName("searchDetail");
			return mv;
		}

		//店舗の時間帯で参照する
		int time = time1 * 100 + time2 * 10 + time3;
		//111リストのための仮リスト
		List<Store> tlist = new ArrayList<>();
		//最終的なリスト
		List<Store> timelist = new ArrayList<>();

		//朝が選択された場合	time1==1 time=100
		List<Store> timelist1 = storeRepository.findByTime(100);
		timelist1.addAll(storeRepository.findByTime(101));
		timelist1.addAll(storeRepository.findByTime(110));
		timelist1.addAll(storeRepository.findByTime(111));

		//昼が選択された場合	time2==1 time=10
		List<Store> timelist2 = storeRepository.findByTime(10);
		timelist2.addAll(storeRepository.findByTime(11));
		timelist2.addAll(storeRepository.findByTime(110));
		timelist2.addAll(storeRepository.findByTime(111));

		//夜が選択された場合	time3==1 time=1
		List<Store> timelist3 = storeRepository.findByTime(1);
		timelist3.addAll(storeRepository.findByTime(11));
		timelist3.addAll(storeRepository.findByTime(101));
		timelist3.addAll(storeRepository.findByTime(111));

		//100（朝)の場合
		if (time == 100) {
			timelist = timelist1;
		} //010 (昼)の場合
		else if (time == 10) {
			timelist = timelist2;
		} //100(夜)の場合
		else if (time == 1) {
			timelist = timelist3;
		} //110(朝・昼)の場合
		else if (time == 110) {
			for (Store s : timelist1) {
				if (timelist2.contains(s)) {
					timelist.add(s);
				}
			}
		} //101(朝・夜)の場合
		else if (time == 101) {
			for (Store s : timelist1) {
				if (timelist3.contains(s)) {
					timelist.add(s);
				}
			}
		} //011(昼・夜)の場合
		else if (time == 11) {
			for (Store s : timelist2) {
				if (timelist3.contains(s)) {
					timelist.add(s);
				}
			}
		} //111の場合
		else if (time == 111) {

			for (Store s : timelist1) {
				if (timelist2.contains(s)) {
					tlist.add(s);
				}
			}
			for (Store s : tlist) {
				if (timelist3.contains(s)) {
					timelist.add(s);
				}
			}
		} //何も選択されなかった場合
		else {
			timelist = storeRepository.findAll();
		}

		//シーン
		int scean = scean1 * 100 + scean2 * 10 + scean3;
		//111リストのための仮リスト
		List<Store> slist = new ArrayList<>();
		//最終
		List<Store> sceanlist = new ArrayList<>();

		//ファミリーが選択された場合	time1==1 scean=100
		List<Store> sceanlist1 = storeRepository.findByScean(100);
		sceanlist1.addAll(storeRepository.findByScean(101));
		sceanlist1.addAll(storeRepository.findByScean(110));
		sceanlist1.addAll(storeRepository.findByScean(111));

		//デートが選択された場合	time2==1 time=10
		List<Store> sceanlist2 = storeRepository.findByScean(10);
		sceanlist2.addAll(storeRepository.findByScean(11));
		sceanlist2.addAll(storeRepository.findByScean(110));
		sceanlist2.addAll(storeRepository.findByScean(111));

		//友人が選択された場合	time3==1 time=1
		List<Store> sceanlist3 = storeRepository.findByScean(1);
		sceanlist3.addAll(storeRepository.findByScean(11));
		sceanlist3.addAll(storeRepository.findByScean(101));
		sceanlist3.addAll(storeRepository.findByScean(111));

		//100（ファミリー)の場合
		if (scean == 100) {
			sceanlist = sceanlist1;
		} //010 (デート)の場合
		else if (scean == 10) {
			sceanlist = sceanlist2;
		} //100(友人)の場合
		else if (scean == 1) {
			sceanlist = sceanlist3;
		} //110(ファミリー・デート)の場合
		else if (scean == 110) {
			for (Store s : sceanlist1) {
				if (sceanlist2.contains(s)) {
					sceanlist.add(s);
				}
			}
		} //101(ファミリー・友人)の場合
		else if (scean == 101) {
			for (Store s : sceanlist1) {
				if (sceanlist3.contains(s)) {
					sceanlist.add(s);
				}
			}
		} //011(デート・友人)の場合
		else if (scean == 11) {
			for (Store s : sceanlist2) {
				if (sceanlist3.contains(s)) {
					sceanlist.add(s);
				}
			}
		} //111の場合
		else if (scean == 111) {
			for (Store s : sceanlist1) {
				if (sceanlist2.contains(s)) {
					slist.add(s);
				}
			}
			for (Store s : slist) {
				if (sceanlist3.contains(s)) {
					sceanlist.add(s);
				}
			}
		} //何も選択されなかった場合
		else {
			sceanlist = storeRepository.findAll();
		}

		//値段検索
		List<Store> pricelist = storeRepository.findByBudgetBetween(price1, price2);
		//店舗情報での検索
		List<Store> detaillist = storeRepository.findByNameLikeAndAddressLikeAndCategorycode1AndCategorycode2(
				"%" + keyword + "%", "%" + address + "%", categorycode1, categorycode2);

		//仮リスト
		List<Store> tslist = new ArrayList<>();
		List<Store> pdlist = new ArrayList<>();
		//最終的な送る値
		List<Store> list = new ArrayList<>();

		//時間帯とシーンで照合
		for (Store s : timelist) {
			if (sceanlist.contains(s)) {
				tslist.add(s);
			}
		}
		//価格と店舗情報で照合
		for (Store s : pricelist) {
			if (detaillist.contains(s)) {
				pdlist.add(s);
			}
		}
		//二つのリストを照合
		for (Store s : tslist) {
			if (pdlist.contains(s)) {
				list.add(s);
			}
		}

		mv.addObject("result", list);
		mv.setViewName("result");
		return mv;

	}

}
