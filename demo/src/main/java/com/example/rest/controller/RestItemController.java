package com.example.rest.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ItemDAO;
import com.example.vo.ItemVO;

@CrossOrigin("*") // CORS 해제
@RestController
public class RestItemController {

	@Autowired
	private ItemDAO iDAO = null;

	// 127.0.0.1:8080/rest/itemsearch.json?txt=사과&key=abc
	@RequestMapping(value = "/rest/itemsearch.json", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HashMap<String, Object> itemSearch(@RequestParam("txt") String txt,
			@RequestParam("key") String key) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (key.equals("abc")) {
			List<ItemVO> list = iDAO.selectItemSearch(txt);
			map.put("ret", 1);
			map.put("data", list);
		} else {
			map.put("ret", 0);
			map.put("error", "유효하지 않는 키입니다.");
		}
		return map;
	}

	// 127.0.0.1:8080/rest/itemone.json?no=1&key=abc
	@RequestMapping(value = "/rest/itemone.json", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HashMap<String, Object> itemOne(@RequestParam("no") int no, @RequestParam("key") String key) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		if (key.equals("abc")) {
			ItemVO obj = iDAO.selectItemOne(no);
			map.put("ret", 1);
			map.put("data", obj);
		} else {
			map.put("ret", 0);
			map.put("error", "유효하지 않는 키입니다.");
		}

		return map;
	}

}