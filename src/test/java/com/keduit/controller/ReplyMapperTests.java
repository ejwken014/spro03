package com.keduit.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	private Long[] bnoArr = {75L, 65L, 22L, 8999L, 193L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testCreate() {
		//1~10까지
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			vo.setBno(bnoArr[i%5]);
			vo.setReply("댓글 테스트입니다."+i);
			vo.setReplyer("replyer"+i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		Long rno = 3L;
		
		ReplyVO vo = mapper.read(rno);
		log.info(vo);
	}
	
	@Test
	public void testDeletr() {
		Long rno = 5L;
		int result = mapper.delete(rno);
		log.info("삭제 결과 : "+result);
	}
	
	@Test
	public void testUpdate() {
		//1건 읽어올 rno
		long rno = 3L;
		
		//업데이트는 먼저 1건 읽어오기
		ReplyVO vo = mapper.read(rno);
		
		//읽어온 결과의 reply에 set
		vo.setReply("Update reply test~~~!");
		int result = mapper.update(vo);
		log.info("update 결과 : "+result);
		
		vo = mapper.read(rno);
		log.info("update 후의 replyVO : "+vo);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListwithPaging(cri, bnoArr[3]);
		replies.forEach(reply -> log.info(reply));
	}
	
	@Test
	public void testList2() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListwithPaging(cri, 8999L);
		replies.forEach(reply -> log.info(reply));
	}
}
