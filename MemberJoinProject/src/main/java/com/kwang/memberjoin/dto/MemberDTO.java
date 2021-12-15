package com.kwang.memberjoin.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberDTO {

	private long m_number;
	private String m_id;
	private String m_password;
	private String m_name;
	private String m_email;
	
	//파일업로드하기
	private MultipartFile m_profile; 
	private String m_profileName;// db에 저장할 파일의 이름을 담는다

}
