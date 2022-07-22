package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service //service로 등록
@RequiredArgsConstructor //mapper를 주입하기 위해
public class BoardServiceImpl implements BoardService{
	
	//BoardMapper mapper = new BoardMapper 로 하지 않고
	//바로 mapper.~~로 쓸 수 있도록 하기 위함.
	private final BoardMapper mapper;
	
	@Override
	public Long register(BoardVO board) {
		
		log.info("register.........."+board);
		
		mapper.insertSelectKey(board);
		return board.getBno();
	}

	@Override
	//1건 가져오기
	public BoardVO get(Long bno) {
		log.info("get...........");
		return mapper.read(bno);
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList.......");
//		return mapper.getList();
//	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList with criteria = "+cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int modify(BoardVO board) {
		log.info("modify............"+board);
		return mapper.update(board);
		//boolean 타입으로 반환하기 때문에 == 1 와 같은 조건 제시
		//**** mapper.update(bno)는 반환 타입이 int
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove................");
		
		return mapper.delete(bno) == 1;
		//boolean 타입으로 반환하기 때문에 == 1 와 같은 조건 제시
		//**** mapper.delete(bno)는 반환 타입이 int
	}

	@Override
	public int getTotalCount(Criteria cri) {
		log.info("get total count.........");
		return mapper.getTotalCount(cri);
	}
}
