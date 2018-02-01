package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookDao guestbookDao;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		System.out.println("list 진입완료");
		List<GuestbookVo> list=guestbookDao.getList();
		model.addAttribute("elist",list);
		return "list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("insert 진입완료");
		guestbookDao.insert(guestbookVo);
		return "redirect:list";
	}
	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteform(@RequestParam("no") int no, Model model) {
		System.out.println("deleteform 진입");
		
		model.addAttribute("no",no); //addAttribute는 setAttribute 와 비슷한 기능.
		return "deleteform";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam("no") int no,  @RequestParam("password") String password) {
		System.out.println("delete 진입");
		
		guestbookDao.delete(no, password);
		return "redirect:list";
	}
}
