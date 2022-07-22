package com.keduit.service;

import java.util.List;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;

public interface ReplyService {
	
	//등록 1건 단위이므로 int로 처리 = mapper.insert
	public int register(ReplyVO vo);
	
	//댓글 1건 가져오기
	public ReplyVO get(Long rno);
	
	//수정 1건 단위이므로 int로 처리
	public int modify(ReplyVO vo);
	
	//삭제 1건 단위이므로 int로 처리
	public int remove(Long rno);
	
	//리스트 가져오기 목록을 가져와야 하므로 bno
	public List<ReplyVO> getList(Criteria cri, Long bno);
	
	//선택한 페이지에 대한 목록
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
	
}
