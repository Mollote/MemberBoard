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
import org.springframework.web.bind.annotation.ResponseBody;

import com.kwang.memberjoin.dto.MemberDTO;
import com.kwang.memberjoin.service.MemberService;

@Controller
@RequestMapping(value = "/member/*") // /member/* 로 요청하는 주소는 전부 받겠다.
public class MemberController {

	@Autowired
	private MemberService ms;

	// 회원가입페이지로 이동
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String joinform() {
		return "member/join";
	}

//	회원가입처리
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String join(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		ms.join(member);
		return "index";
	}

	// 아이디 중복체크
	@RequestMapping(value = "idDuplicate", method = RequestMethod.POST)
	public @ResponseBody String idDuplicate(@RequestParam("m_id") String m_id) {
		String result = ms.idDuplicate(m_id);
		return result;
	}

	// 로그인이동
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginform() {
		return "member/login";
	}

	// 로그인처리
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute MemberDTO member) {
		String result = ms.login(member);
		return result;
	}

//	마이페이지	이동
	@RequestMapping(value = "mypage", method = RequestMethod.POST)
	public String mypageform(@RequestParam("m_id") String m_id, Model model) {
		MemberDTO member = ms.mypageId(m_id);
		model.addAttribute("member", member);
		return "member/mypage";
	}

// 마이페이지 수정
	@RequestMapping(value = "mypageud", method = RequestMethod.POST)
	public String mypage(@ModelAttribute MemberDTO member, Model model) {
		ms.mypage(member);
		return "redirect:/board/paging";
	}

//	관리자페이지이동
	@RequestMapping(value = "adminpage", method = RequestMethod.GET)
	public String main() {
		return "member/adminpage";
	}

//	회원목록조회
	@RequestMapping(value = "memberList", method = RequestMethod.GET)
	public String memberView(Model model) {
		List<MemberDTO> mList = ms.findAll();
		model.addAttribute("mList", mList);
		return "member/memberList";
	}

//	삭제
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("m_number") long b_number, Model model) {
		long board = ms.delete(b_number);
		model.addAttribute("mList", board);
		return "redirect:/member/memberList";
	}
}
