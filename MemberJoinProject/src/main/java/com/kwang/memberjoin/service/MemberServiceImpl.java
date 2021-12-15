package com.kwang.memberjoin.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kwang.memberjoin.dto.MemberDTO;
import com.kwang.memberjoin.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository mr;
	@Autowired
	private HttpSession session;
	
	//회원가입처리 정보전달
	@Override
	public void join(MemberDTO member)throws IllegalStateException, IOException  {
		
		MultipartFile m_profile = member.getM_profile();// 업로드 저장된 파일의 이름 확인
		String m_profileName = m_profile.getOriginalFilename();	//중복을 피하기위한 이름설정	
		m_profileName = System.currentTimeMillis() + "-" + m_profileName; //가져온 이름 앞에 작성시간을 구분하기위해 붙여준다
		
		String savePath =  "/Users/kwangwook/Desktop/STS/sts_class/MemberJoinProject/src/main/webapp/resources/upload/"+m_profileName;
		
		if(!m_profile.isEmpty()) {
			m_profile.transferTo(new File(savePath));
		} //파일이 존재시 저장
		
		//DB에 저장하기위해 dto로 파일이름을 담는다
		member.setM_profileName(m_profileName);
		mr.join(member);
	}


	@Override
	public String idDuplicate(String m_id) {
		String result = mr.idDuplicate(m_id);
		if(result==null) {
			return "ok";//조회결과가 없기 떄문에 해당 아이디는 사용가능
		}else {
			return "no";//조회결과가 있기 때문에 해당 아이디는 사용불가
		}
	}


	@Override
	public String login(MemberDTO member) {
		MemberDTO result = mr.login(member);
		if(result != null) {
			if(result.getM_id().equals("admin")) {
				session.setAttribute("loginId", member.getM_id());
				session.setAttribute("loginNumber", result.getM_number());
				return "member/adminpage";
			}
			session.setAttribute("loginId", member.getM_id());
			session.setAttribute("loginNumber", result.getM_number());
			return "redirect:/board/paging";
		} else {
			return "member/login";
		}
	}


	@Override
	public int mypage(MemberDTO member) {
		return mr.mypage(member);
	}


	@Override
	public MemberDTO mypageId(String m_id) {
		return mr.mypageId(m_id);
	}


	@Override
	public List<MemberDTO> findAll() {
		// TODO Auto-generated method stub
		return mr.findAll();
	}


	@Override
	public int delete(long m_number) {
		
		return mr.delete(m_number);
	}







	
	
	

}
