package com.keduit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;

public interface ReplyMapper {
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long bno);
	
	public int delete(Long rno);
	
	public int update(ReplyVO vo);
	
	//paging 처리를 위해서 cri를 넘겨줌
	public List<ReplyVO> getListwithPaging(
			@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	public int getCountByBno(Long bno);
}
