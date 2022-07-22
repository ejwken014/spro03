package com.keduit.mapper;

import java.util.List;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardMapper {
//	@Select("select * from t_board where bno > 0")
	//Board.xml에 있는 id="getList"와 연동
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	//Board.xml에 있는 id="insert"와 연동
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	//BoardMapper.xml에 적은 delete문은 삭제 한 행 수를 반환해준다.
	//때문에 받환 받을 타입을 정해줘야 하므로 int 타입으로 리턴 받아야 한다.
	public int delete(Long bno);
	
	//BoardVO에 변경 할 값을 담아서 건네 주기 때문에 매개변수 타입을 BoardVO로 받는다.
	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri);
}
