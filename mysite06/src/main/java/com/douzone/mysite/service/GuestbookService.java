package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVO;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVO> list() {
		List<GuestbookVO> list = guestbookRepository.findAll();
		return list;
	}

	public Boolean writeMessage(GuestbookVO vo) {
		int count = guestbookRepository.insert(vo);
		return count == 1;
	}

	public Boolean deleteMessage(Long no,String password) {
		return 1 == guestbookRepository.delete(no,password);
	}

	public List<GuestbookVO> getMessageList(Long startNo) {
		return guestbookRepository.findAll(startNo);
	}
	
}
