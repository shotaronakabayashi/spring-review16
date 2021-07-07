package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

	//トップページを表示できません
	@RequestMapping ("/index")
	public ModelAndView index (ModelAndView mv) {




		mv.setViewName("index");
		return mv;
	}

}
