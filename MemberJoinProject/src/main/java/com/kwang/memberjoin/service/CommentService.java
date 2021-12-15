package com.kwang.memberjoin.service;

import java.util.List;

import com.kwang.memberjoin.dto.CommentDTO;

public interface CommentService {

	public void save(CommentDTO comment);

	List<CommentDTO> findAll(long b_number);

}
