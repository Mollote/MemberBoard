package com.kwang.memberjoin.service;

import java.io.IOException;
import java.util.List;

import com.kwang.memberjoin.dto.BoardDTO;
import com.kwang.memberjoin.dto.PageDTO;

public interface BoardService {

	public void write(BoardDTO board)throws IllegalStateException, IOException ;

	public PageDTO paging(int page);

	public List<BoardDTO> pagingList(int page);


	public BoardDTO detail(long b_number);

	public void update(BoardDTO board);

	public long delete(long b_number);

	public List<BoardDTO> search(String searchtype, String keyword);

}
