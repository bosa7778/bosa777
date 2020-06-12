package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.example.dao.ItemDAO;
import com.example.vo.ItemVO;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private ItemDAO iDAO =null;
	
	@RequestMapping(value = "/home")
	public String home() {
		return "/admin/home";
	}
	
	@RequestMapping(value="/item")
	public String itemlist(Model model) {
		List<ItemVO> list = iDAO.selectItemList();
		model.addAttribute("list", list);
		return "/admin/item";
	}
	
	
	
	
	
	@RequestMapping(value="/itemupdate", method = RequestMethod.POST)
	public String itemupdatepost(
			@RequestParam(value = "no[]") int[] no,
			@RequestParam("name[]") String[] name,
			@RequestParam("price[]") int[] price,
			@RequestParam("qty[]") int[] qty,
			@RequestParam("des[]") String[] des) {
		
		List<ItemVO> list = new ArrayList<ItemVO>();
		for(int i=0; i<no.length; i++) {
			ItemVO obj = new ItemVO();
			obj.setItemno(no[i]);
			obj.setItemname(name[i]);
			obj.setItemprice(price[i]);
			obj.setItemqty(qty[i]);
			obj.setItemdes(des[i]);
			list.add(obj);
		}
		
		iDAO.updateItemBatch(list);
		return "redirect:/admin/item";	
	}
		
		
		
	@RequestMapping(value="/itemupdate")
	public String itemupdate(Model model, HttpServletRequest req) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(req);
		if(map != null) {
			int[] tmp = (int[]) map.get("abc");
			for(int i=0;i<tmp.length;i++) {
				System.out.println(tmp[i]);
			}
			List<ItemVO> list = iDAO.selectItemWhere(tmp);
			model.addAttribute("list", list);	
			return "/admin/itemupdate";
		}
		else {
			return "redirect:/admin/item";
		}
	}
	
	//일괄수정, 일괄삭제를 같이 처리할 곳
	@RequestMapping(value="/item", method=RequestMethod.POST)
	public String itembatch(@RequestParam("btn") String btn,
			RedirectAttributes redirectAttributes,
			@RequestParam(value="chk[]", required = false) int[] itemno) {
		if(btn.equals("일괄삭제")) {
			iDAO.deleteItemBatch(itemno);
		}
		else if( btn.equals("일괄수정")) {
			redirectAttributes.addFlashAttribute("abc",itemno);
			//redirectAttributes.addAttribute("itemno", itemno);
			return "redirect:/admin/itemupdate";
		}
		return "redirect:/admin/item";
	}
	
	@RequestMapping(value = "/iteminsert")
	public String iteminsert() {
		return "/admin/iteminsert";
	}
	
	@RequestMapping(value = "/iteminsert", method=RequestMethod.POST)
	public String iteminsertpost(
			@RequestParam("name[]") String[] name,
			@RequestParam("price[]") int[] price,
			@RequestParam("qty[]") int[] qty,
			@RequestParam("content[]") String[] content) {
		
		List<ItemVO> list = new ArrayList<ItemVO>();
		for(int i=0; i<name.length; i++) {
			ItemVO obj = new ItemVO();
			obj.setItemname(name[i]);
			obj.setItemprice(price[i]);
			obj.setItemqty(qty[i]);
			obj.setItemdes(content[i]);
			
			list.add(obj);
		}
		iDAO.insertItemBatch(list);
		
		return "redirect:/admin/home"; //<a href="/admin/home">자동화</a>
	}
}