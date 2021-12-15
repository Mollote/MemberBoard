package com.kwang.memberjoin.service;

import java.io.IOException;
import java.util.List;

import com.kwang.memberjoin.dto.MemberDTO;

public interface MemberService{

	public void join(MemberDTO member)throws IllegalStateException, IOException;

	public String idDuplicate(String m_id);

	public String login(MemberDTO member);

	

	public int mypage(MemberDTO member);

	public MemberDTO mypageId(String m_id);

	public List<MemberDTO> findAll();

	public int delete(long m_number);




}
