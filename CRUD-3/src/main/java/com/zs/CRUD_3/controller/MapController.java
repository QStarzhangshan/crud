package com.zs.CRUD_3.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class MapController {

	  @RequestMapping("/hello")
	    public String helloHtml(HashMap<String, Object> map) {
	        map.put("hello", "欢迎进入HTML页面");
	        return "/index";
}
}
