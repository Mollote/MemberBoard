package com.kwang.memberjoin.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CommentDTO {

//	3. 댓글데이터: 댓글번호, 게시글번호, 작성자, 작성일자, 내용
	
	private long c_number;
	private long b_number;
	private String c_writer;
	private String c_contents;
	private Timestamp c_date;
	
}
