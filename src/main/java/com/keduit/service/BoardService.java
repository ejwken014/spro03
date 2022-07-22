package com.keduit.service;

import java.util.List;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardService {
	//insert하는 메서드
	public Long register(BoardVO board);
	
	public BoardVO get(Long bno);
	
//	public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	
	//update
	public int modify(BoardVO board);
	//delete
	public boolean remove(Long bno);
	
	public int getTotalCount(Criteria cri);
}
