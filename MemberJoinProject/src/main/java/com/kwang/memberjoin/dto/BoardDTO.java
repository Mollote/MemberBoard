package com.kwang.memberjoin.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {

//	2. 게시글데이터: 글번호, 제목, 작성자, 내용, 조회수, 작성일자, 첨부파일명
	
	private int b_number;
	private String b_title;
	private String b_writer;
//	게시글과 댓글작성자는 회원테이블의 아이디를 참조
	private String b_contents;
	private int b_hits;
	private Timestamp b_date;
	
//	첨부파일명 b_file
	private MultipartFile b_file;
	private String b_fileName;
	
}
