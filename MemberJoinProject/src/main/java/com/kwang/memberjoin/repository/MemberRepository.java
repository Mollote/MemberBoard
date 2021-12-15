package com.kwang.memberjoin.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.kwang.memberjoin.dto.MemberDTO;

@Repository
public class MemberRepository {

	@Autowired
	private SqlSessionTemplate sql;
	
	public void join(MemberDTO member) {
		sql.insert("Member.join", member);
	}

	public String idDuplicate(String m_id) {
		return sql.selectOne("Member.idDuplicate", m_id);
	}

	public MemberDTO login(MemberDTO member) {
		return sql.selectOne("Member.login", member);
	}



	public int mypage(MemberDTO member) {
		return sql.update("Member.mypage", member);
	}


	public MemberDTO mypageId(String m_id) {
		return sql.selectOne("Member.mypageId", m_id);
	}

	public List<MemberDTO> findAll() {
		// TODO Auto-generated method stub
		return sql.selectList("Member.findAll");
	}

	public int delete(long m_number) {
	
		return sql.delete("Member.delete", m_number);
	}



}
