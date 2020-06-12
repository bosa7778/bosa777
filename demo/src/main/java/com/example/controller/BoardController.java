package com.example.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.BoardDAO;
import com.example.vo.BoardVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardDAO bDAO =null;
	
	//127.0.0.1:8080/board/getimg?no=11
	@RequestMapping(value="/getimg")
	public ResponseEntity<byte[]> getimg(@RequestParam("no") int no){
		BoardVO obj = bDAO.selectBoardImg(no);
		try {
			if (obj.getBrd_img().length > 0) { //이미지가 있음
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.IMAGE_JPEG);
				ResponseEntity<byte[]> ret = new ResponseEntity<byte[]>(
							obj.getBrd_img(), header, HttpStatus.OK);
				return ret;
			}
			return null;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	
	
	//127.0.0.1:8080/board/insertbatch
	@RequestMapping(value = "/insertbatch", method = RequestMethod.GET)
	public String insertbatch() {
		return "insertbatch";
	}
	
	
	//http://127.0.0.1:8080/board/content?no=14
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(Model model, HttpSession httpSession, 
			@RequestParam(value="no", defaultValue = "0", required = false) int no) {
		if( no == 0) {
			return "redirect:/board/list";
		}
				
		Integer chk = (Integer)httpSession.getAttribute("SESSION_BOARD_HIT_CHECK");
		if (chk != null) {
			if( chk == 1) {
				bDAO.updateHit(no);
			}
			httpSession.setAttribute("SESSION_BOARD_HIT_CHECK", 0);
		}
		
		BoardVO obj = bDAO.selectBoardOne(no);
		model.addAttribute("obj", obj);
		
		return "/board/content";
	}

	
	//127.0.0.1:8080/board/list
	//127.0.0.1:8080/board/list?page=1
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpSession httpSession, 
			@RequestParam(value="page", defaultValue = "1", required = false) int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", page*10-9);
		map.put("end", page*10);
		
		httpSession.setAttribute("SESSION_BOARD_HIT_CHECK", 1);
		
		

		//목록
		List<BoardVO> list = bDAO.selectBoard(map);
		
		//개수
		int cnt = bDAO.countBoard();
		
		model.addAttribute("list", list);
		
		//System.out.println( (int) Math.ceil(n/10.0) );
		model.addAttribute("cnt", (cnt-1)/10+1);
		
		return "/board/list";
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertBoard(HttpSession httpSession, Model model) {
		//세션에서 로그인한 사용자의 아이디값을 가져옴.
		String userid = (String)httpSession.getAttribute("SESSION_ID");
		if(userid == null) { //아이디값이 없다면 로그인되지 않은 상태
			return "redirect:/member/login"; //로그인 페이지로 이동
		}
		//그렇지 않다면 게시판 글쓰기 화면 표시
		model.addAttribute("userid", userid);
		return "/board/insert";
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertBoardPost(@ModelAttribute BoardVO obj,
			@RequestParam MultipartFile[] imgs) throws IOException {
		if(imgs != null && imgs.length > 0) { //이미지가 첨부되었다면
			for ( MultipartFile one : imgs   ) {
				obj.setBrd_img( one.getBytes() );
			}
		}
		
		//DAO로 obj값 전달하기
		bDAO.insertBoard(obj);
		
		return "redirect:/";
	}
	
}