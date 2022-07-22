package com.keduit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	//mapper 사용을 위해 주입
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	@Override
	//등록 1건 단위이므로 int로 처리 = mapper.insert
	public int register(ReplyVO vo) { //view 에게 받아온 내용을 insert
		log.info("댓글 등록 작업----> "+vo);
		return mapper.insert(vo);
	}

	@Override
	//댓글 1건 가져오기 = mapper.read
	public ReplyVO get(Long rno) {
		log.info("댓글 상세 조회----> "+rno);
		return mapper.read(rno);
	}

	@Override
	//수정 1건 단위이므로 int로 처리 = update
	public int modify(ReplyVO vo) {
		log.info("댓글 수정 하기----> "+vo);
		return mapper.update(vo);
	}

	@Override
	//삭제 1건 단위이므로 int로 처리 = delete
	public int remove(Long rno) {
		log.info("댓글 삭제 작업----> "+rno);
		return mapper.delete(rno);
	}

	@Override
	//리스트 가져오기 목록을 가져와야 하므로 bno
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("댓글 목록 가져오기----> "+cri+","+bno);
		return mapper.getListwithPaging(cri, bno);
	}
	
	//선택한 페이지에 대한 목록
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new ReplyPageDTO(
				mapper.getCountByBno(bno), //해당되는 게시글에 달린 댓글의 갯수를 가져옴
				mapper.getListwithPaging(cri, bno)); //해당되는 페이지에 게시글을 가져와서 보여줌
	}

}
