package com.kwang.memberjoin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kwang.memberjoin.dto.BoardDTO;
import com.kwang.memberjoin.dto.PageDTO;
import com.kwang.memberjoin.service.BoardService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	//로그아웃
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		//세션에 저장된 데이터를다 지운
		return "index";
	}
	
	// 글 작성페이지이동
	@RequestMapping(value="write", method=RequestMethod.GET)
	public String writeform() {
		return "board/write";
	}
	
	// 글 작성처리
	@RequestMapping(value="writeboard", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardDTO board)throws IllegalStateException, IOException {
		bs.write(board);
		return "redirect:/board/paging";
	}
	
	// 페이징
	@RequestMapping(value="paging", method=RequestMethod.GET)
	public String paging(@RequestParam(value="page", required=false, defaultValue="1")int page, Model model) {
		PageDTO paging = bs.paging(page);
		List<BoardDTO> boardList = bs.pagingList(page);
		model.addAttribute("bList", boardList);
		model.addAttribute("paging", paging);
		return "board/boardpage";
	}

	//상세조회
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(@RequestParam("b_number") long b_number, Model model,@RequestParam(value="page", required=false, defaultValue="1")int page) {	
		BoardDTO board = bs.detail(b_number);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
//		List<CommentDTO> commentList = cs.findAll(b_number);
//		model.addAttribute("commentList", commentList);
		return "board/detail";
	}
	
	
//	 수정
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateForm(@RequestParam("b_number") long b_number, Model model,@RequestParam(value="page", required=false, defaultValue="1")int page) {
		BoardDTO board = bs.detail(b_number);
		model.addAttribute("board", board);
		model.addAttribute("page", page); //update.jsp로 갈 때 page값을 가지고 감.
		return "board/update";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute BoardDTO board, @RequestParam(value="page", required=false, defaultValue="1")int page) {
		bs.update(board);
		return "redirect:/board/detail?b_number=" + board.getB_number()+ "&page="+page;
	}
	
//	삭제
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("b_number") long b_number, Model model) {
		long board = bs.delete(b_number);
		model.addAttribute("board", board);
		return "redirect:/board/paging";
	}
	
//	검색
	@RequestMapping(value="search",method=RequestMethod.GET)
	public String search(@RequestParam("searchtype") String searchtype, @RequestParam("keyword") String keyword, Model model) {
		List<BoardDTO> boardList = bs.search(searchtype, keyword);
		model.addAttribute("bList",boardList);
		return "board/boardpage";
	}

	
	
}
