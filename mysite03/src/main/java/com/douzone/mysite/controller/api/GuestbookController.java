package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVO;

@RestController("GuestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	 
	@Autowired
	private GuestbookService guestBookService;
	
	@GetMapping("/list/{no}") //RestController에서는  @GetMapping 을 달면 뒤에 Method = ~~ 안적어 줘도 된다
	public JsonResult list(@PathVariable("no") Long startNo) {
		List<GuestbookVO> list = guestBookService.getMessageList(startNo);
		return JsonResult.success(list);
	}
	 
	@PostMapping("/add")
	public JsonResult add(@RequestBody GuestbookVO vo) {
		guestBookService.writeMessage(vo);
		vo.setPassword("");
		return JsonResult.success(vo);
	}
	
	@DeleteMapping("/delete/{no}")
	public JsonResult delete(@PathVariable("no") Long no,@RequestParam(value="password", required=true,defaultValue="") String password) {
		System.out.println(no+":"+password);
		boolean result = guestBookService.deleteMessage(no, password);
		return JsonResult.success(result ? no : -1);
	}
	

}
