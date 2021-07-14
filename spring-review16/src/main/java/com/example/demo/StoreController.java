package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class StoreController {

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	PictureRepository pictureRepository;

	@Autowired
	ReviewRepository reviewRepository;


	//カテゴリー別のランキングを表示
	//トップページを表示
	@RequestMapping("/{category}")
	public ModelAndView index(
			@PathVariable("category") String category,
			ModelAndView mv) {


		Store store = null;
		List<Store> list = storeRepository.findByCategorycode1(category);

		float best1 = 0; float best2 = 0; float best3 = 0; float other = 0;
		for (Store s : list) {
			float a = s.getRankave();
			if (a > best1 ){
				best1 = a;
			}
			else if (a <= best1 && a >= best2) {
				 best2 = a;
			}
			else if (a <= best2 && a >= best3) {
				 best3 = a;
			} else {
				other = a;
			}
		}

			//ランクの情報から店舗情報を取得
			//1位
			List<Store> list1 = storeRepository.findByRankave(best1);

			//2位
			List<Store> list2 = storeRepository.findByRankave(best2);

			//3位
			List<Store> list3 = storeRepository.findByRankave(best3);

			mv.addObject("list1", list1);
			mv.addObject("list2", list2);
			mv.addObject("list3", list3);

			mv.setViewName("top");
			return mv;
		}




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//店舗登録をクリックされた
	@GetMapping("/addstore")
	public ModelAndView addStore(ModelAndView mv) {

		mv.setViewName("addstore");
		return mv;
	}


	//店舗情報が入力されて登録ボタンが押された
	//メニュー登録画面への遷移
	@PostMapping("/addstore")
	public ModelAndView addStore2(
			@RequestParam ("name") String name,
			@RequestParam ("categorycode1") String categorycode1,
			@RequestParam ("categorycode2") String categorycode2,
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
		String scean0 = "" + scean1 + scean2 + scean3;

		int time = Integer.parseInt(time0);
		int scean = Integer.parseInt(scean0);

		//追加
		Store store = new Store(name, categorycode1, categorycode2, address, tel, budget, time,scean,message);
		storeRepository.saveAndFlush(store);

		//コードの取得
		Store store2 = null;
		Optional<Store> list = storeRepository.findByName(name);
		if (list.isEmpty() == false) {
			store2 = list.get();
		}


		mv.addObject("code", store2.getCode());
		mv.addObject("count", count);
		mv.setViewName("addmenu");				//メニュー登録画面に遷移
		return mv;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//遷移後のメニューページ
	//メニュー情報が入力されて登録するが押された
	@RequestMapping("/addmenu")
	public ModelAndView addmenu2 (
			@RequestParam("code") int menucode,
			@RequestParam ("menuname") String menuname,
			@RequestParam("menuprice") int menuprice,
			@RequestParam("count") int count,
			ModelAndView mv ) {

		//何回メニューを登録したかのカウント変数
		count++;

		//メニューを登録
		Menu menu = new Menu(menucode, menuname, menuprice);

		menuRepository.saveAndFlush(menu);

		mv.addObject("code", menucode);
		mv.addObject("count", count);
		mv.setViewName("addmenu");
		return mv;
	}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//メニュー登録画面から写真も追加するがクリックされた

	@GetMapping("/addpicture/{code}")
	public ModelAndView addpicture (
			@PathVariable(name = "code") int picturecode,
			ModelAndView mv) {

		//何枚写真を送ったか
		int count = 0;

		mv.addObject("code", picturecode);
		mv.addObject("count", count);
		mv.setViewName("addpicture");
		return mv;
	}


	//写真情報が入力されて登録ボタンが押された
	@PostMapping("/addpicture")
	public ModelAndView addpicture2 (
			@RequestParam("code") int picturecode,
			@RequestParam("pictureurl") String pictureurl,
			@RequestParam("count") int count,
			ModelAndView mv) {

		Picture picture = new Picture(picturecode, pictureurl);

		pictureRepository.saveAndFlush(picture);

		count++;

		mv.addObject("code", picturecode);
		mv.addObject("count", count);
		mv.setViewName("addpicture");
		return mv;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//店舗詳細ページが押された
	@GetMapping("/store/{name}")
	public ModelAndView storeDetail (
			@PathVariable("name") String name,
			ModelAndView mv ) {

		//店舗情報を送る
		Store store = null;
		Optional<Store> list = storeRepository.findByName(name);

		if (list.isEmpty() == false) {
			store = list.get();
		}
		List<Store> storelist = new ArrayList<>();
		storelist.add(store);
		float rankave = store.getRankave();

		mv.addObject("storelist", storelist);
		mv.addObject("rankave", rankave);

		int code = store.getCode();

		//メニュー情報を送る
		List<Menu> menulist = menuRepository.findByMenucode(code);
		mv.addObject("menulist", menulist);


		//写真の情報を送る
		List<Picture> picturelist = pictureRepository.findByPicturecode(code);
		mv.addObject("picturelist", picturelist);


		//レビューの情報を送る
		List<Review> reviewlist = reviewRepository.findByReviewcode(code);
		mv.addObject("reviewlist", reviewlist);

		//レビューの数を送る
		int count = 0;
		for (Review s : reviewlist) {
			count++;
		}
		mv.addObject("count", count);

		mv.setViewName("store");
		return mv;
	}



	//レビューのもっと見るが押された
		@GetMapping("/review/{code}")
		public ModelAndView reviewMore (
				@PathVariable("code") int code,
				ModelAndView mv) {

			//レビューの情報を送る
			List<Review> reviewlist = reviewRepository.findByReviewcode(code);
			mv.addObject("list", reviewlist);

			mv.setViewName("review");
			return mv;
		}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//変更
	//店舗情報を変更するがクリックされた
	@GetMapping("/change/{code}")
	public ModelAndView change (
			@PathVariable("code") int storecode,
			ModelAndView mv) {

		List<Menu> menulist = menuRepository.findByMenucode(storecode);
		List<Picture> picturelist = pictureRepository.findByPicturecode(storecode);

		mv.addObject("picturelist", picturelist);
		mv.addObject("menulist", menulist);
		mv.setViewName("change");
		return mv;
	}


	//変更内容が入力されて変更するが押された
	//メニューの変更
	@PostMapping("/change")
	public ModelAndView change2 (
			@RequestParam("code") int code,
			@RequestParam("menucode") int menucode,
			@RequestParam("menuname") String menuname,
			@RequestParam("menuprice") int price,
			ModelAndView mv ) {

		Menu menu = new Menu(code, menucode, menuname,price);
		menuRepository.saveAndFlush(menu);


				//店舗詳細ページ用の情報を送る-------------------------------------------------------------------
				int storecode = menucode;
				Store store = null;
				Optional<Store> storelist0 = storeRepository.findById(storecode);
				if (storelist0.isEmpty() == false) {
					store = storelist0.get();
				}
				List<Store> storelist = new ArrayList<>();
				storelist.add(store);

				//店舗情報を送る
				mv.addObject("storelist", storelist);

				//メニュー情報を送る
				List<Menu> menulist = menuRepository.findByMenucode(storecode);
				mv.addObject("menulist", menulist);

				//写真の情報を送る
				List<Picture> picturelist = pictureRepository.findByPicturecode(storecode);
				mv.addObject("picturelist", picturelist);

				//レビューの情報を送る
				List<Review> reviewlist = reviewRepository.findByReviewcode(storecode);
				mv.addObject("reviewlist", reviewlist);

				//レビューの数を送る
				int count = 0;
				for (Review r : reviewlist) {
					count++;
				}
				mv.addObject("count", count);
				// ---------------------------------------------------------------------------------------------

		mv.setViewName("store");
		return mv;
	}


	//メニューの削除
	@PostMapping("/delete")
	public ModelAndView menuDelete (
			@RequestParam("md") int code1,
			@RequestParam("md2") int storecode,
			ModelAndView mv) {

		//メニューを削除
		menuRepository.deleteById(code1);

				//店舗詳細ページ用の情報を送る-------------------------------------------------------------------
				Store store = null;
				Optional<Store> storelist0 = storeRepository.findById(storecode);
				if (storelist0.isEmpty() == false) {
					store = storelist0.get();
				}
				List<Store> storelist = new ArrayList<>();
				storelist.add(store);

				//店舗情報を送る
				mv.addObject("storelist", storelist);

				//メニュー情報を送る
				List<Menu> menulist = menuRepository.findByMenucode(storecode);
				mv.addObject("menulist", menulist);

				//写真の情報を送る
				List<Picture> picturelist = pictureRepository.findByPicturecode(storecode);
				mv.addObject("picturelist", picturelist);

				//レビューの情報を送る
				List<Review> reviewlist = reviewRepository.findByReviewcode(storecode);
				mv.addObject("reviewlist", reviewlist);

				//レビューの数を送る
				int count = 0;
				for (Review r : reviewlist) {
					count++;
				}
				mv.addObject("count", count);
				// ---------------------------------------------------------------------------------------------

		mv.setViewName("store");
		return mv;
	}


	//写真の削除
	@PostMapping("/delete/picture")
	public ModelAndView deletePicture (
			@RequestParam("p_code") int code,
			@RequestParam("picturecode") int storecode,
			ModelAndView mv) {

		//削除
		pictureRepository.deleteById(code);

				//店舗詳細ページ用の情報を送る-------------------------------------------------------------------
				Store store = null;
				Optional<Store> storelist0 = storeRepository.findById(storecode);
				if (storelist0.isEmpty() == false) {
					store = storelist0.get();
				}
				List<Store> storelist = new ArrayList<>();
				storelist.add(store);

				//店舗情報を送る
				mv.addObject("storelist", storelist);

				//メニュー情報を送る
				List<Menu> menulist = menuRepository.findByMenucode(storecode);
				mv.addObject("menulist", menulist);

				//写真の情報を送る
				List<Picture> picturelist = pictureRepository.findByPicturecode(storecode);
				mv.addObject("picturelist", picturelist);

				//レビューの情報を送る
				List<Review> reviewlist = reviewRepository.findByReviewcode(storecode);
				mv.addObject("reviewlist", reviewlist);

				//レビューの数を送る
				int count = 0;
				for (Review r : reviewlist) {
					count++;
				}
				mv.addObject("count", count);
				// ---------------------------------------------------------------------------------------------


		mv.setViewName("store");
		return mv;
	}











}


















