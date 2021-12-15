package com.kwang.memberjoin.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kwang.memberjoin.dto.BoardDTO;
import com.kwang.memberjoin.dto.PageDTO;
import com.kwang.memberjoin.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository br;
	
	@Override
	public void write(BoardDTO board)throws IllegalStateException, IOException  {
		MultipartFile b_file = board.getB_file();
		String b_fileName = b_file.getOriginalFilename();
		b_fileName = System.currentTimeMillis() + "-" + b_fileName;

		String savePath =  "/Users/kwangwook/Desktop/STS/sts_class/MemberJoinProject/src/main/webapp/resources/board_upload/"+b_fileName;

		if(!b_file.isEmpty()) {
			b_file.transferTo(new File(savePath));
		} 
		
		board.setB_fileName(b_fileName);
		br.write(board);
	}

	
//	페이징처리
		private static final int PAGE_LIMIT = 5; // 한페이지에 보여질 글 개수 
		private static final int BLOCK_LIMIT = 3; // 한화면에 보여질 페이지 개수

		@Override
		public List<BoardDTO> pagingList(int page) {
			// TODO Auto-generated method stub
			int pagingStart = (page-1) * PAGE_LIMIT;
			Map<String, Integer> pagingParam = new HashMap<String, Integer>();
			pagingParam.put("start", pagingStart);
			pagingParam.put("limit", PAGE_LIMIT);
			List<BoardDTO> pagingList = br.pagingList(pagingParam);

			return pagingList;
		}

		@Override
		public PageDTO paging(int page) {
			int boardCount = br.boardCount(); // 전체 글 갯수 조회
			int maxPage = (int)(Math.ceil((double)boardCount / PAGE_LIMIT));// 페이지를 n개수 단위로 나눌때 올림을 해주는 Math.ceil 함수 (6.1->7) 
			int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1; // 스타트 페이지 계산하기 1 / 3 - 1 = 0.666
			int endPage = startPage + BLOCK_LIMIT - 1;
			if(endPage > maxPage)
				endPage = maxPage; 
			PageDTO paging = new PageDTO();
			paging.setPage(page);
			paging.setStartPage(startPage);
			paging.setEndPage(endPage);
			paging.setMaxPage(maxPage);

			return paging;
		}

		@Override
		public BoardDTO detail(long b_number) {
			return br.detail(b_number);
		}

		@Override
		public void update(BoardDTO board) {
			br.update(board);
		}

		@Override
		public long delete(long b_number) {
			return br.delete(b_number);
		}

		@Override
		public List<BoardDTO> search(String searchtype, String keyword) {
//			스트링값 두개를 받을때 맵을 쓰자
			Map<String, String> searchParam = new HashMap<String, String>();
			searchParam.put("type", searchtype);
			searchParam.put("word", keyword);
			List<BoardDTO> boardList = br.search(searchParam);
			return boardList;
		}
	
}
