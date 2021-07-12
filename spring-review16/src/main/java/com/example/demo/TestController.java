package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@Autowired
	PictureRepository pictureRepository;

	@RequestMapping("/test")
	public ModelAndView test (ModelAndView mv) {

		int code = 20;

		Picture picture = null;

		Optional<Picture> list = pictureRepository.findById(code);

		if (list.isEmpty() == false) {
			picture = list.get();
		}

		mv.addObject("pictureurl", picture.getPictureurl());
		mv.setViewName("test");
		return mv;
	}



}
