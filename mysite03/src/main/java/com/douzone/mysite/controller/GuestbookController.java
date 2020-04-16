package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVO;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestBookService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String list(Model model) {
		List<GuestbookVO> list = guestBookService.list();
		model.addAttribute("list",list);
		return "guestbook/list";
	}

	@RequestMapping(value="/spa",method=RequestMethod.GET)
	public String indexSpa(Model model) {
		return "guestbook/index-spa";
	}	
	
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String list(GuestbookVO vo) {
		guestBookService.writeMessage(vo);
		return "redirect:/guestbook/";
	}
	
	@RequestMapping(value="/delete/{no}",method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no,Model model) {
		model.addAttribute("no",no);
		return "guestbook/delete";
	}

/*	@RequestMapping(value="/delete/{no}",method=RequestMethod.GET)
	public String delete(@ModelAttribute @PathVariable("no") Long no) {
		return "guestbook/delete";
	}
*/	
	@RequestMapping(value="/delete/{no}",method=RequestMethod.POST)
	public String delete(
			@PathVariable("no") Long no,
			@RequestParam(value="password", required=true, defaultValue="") String password) {
		System.out.println(no);
		System.out.println(password);
		guestBookService.deleteMessage(no,password);
		return "redirect:/guestbook/";
	}
}
