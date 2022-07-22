package com.keduit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor //파라미터를 전부 매개변수로 가지는 생성자 생성
public class ReplyController {
	
	private ReplyService service;
	//ResponseEntity : http의 상태를 보내준다.
	//RestController이기 때문에(데이터형을 반환) jsp가 아닌 String 타입을 리턴
	@PostMapping(value="/new",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		//                      댓글 작성 = insert
		log.info("ReplyVO : "+vo);
		int insertCount = service.register(vo);
		log.info("댓글 입력 count : "+insertCount);
		
		//insertCount == 1은 댓글 1줄이 등록 되었다는 것.
		return insertCount == 1?
				new ResponseEntity<>("success",HttpStatus.OK) //insertCount == 1 일 때
			  : new ResponseEntity<>("fail",HttpStatus.INTERNAL_SERVER_ERROR); //insertCount != 1 일 때
	}
	
	@GetMapping(value = "/pages/{bno}/{page}",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyPageDTO> getlist(
			@PathVariable("page") int page,
			@PathVariable("bno" ) Long bno){
		
		log.info("reply getlist........."+page+","+bno);
		Criteria cri = new Criteria(page,10);
		log.info(cri);
		return new ResponseEntity<>(service.getListPage(cri, bno),HttpStatus.OK);
	}
	
	@GetMapping(value="/{rno}",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("reply get : "+rno);
		return new ResponseEntity<>(service.get(rno),HttpStatus.OK);
	}

	
	@DeleteMapping(value = "/{rno}",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("remove" + rno);
		
		return service.remove(rno) == 1?
				new ResponseEntity<>("seccess",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
			value = "/{rno}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo,
			@PathVariable("rno") Long rno){
		vo.setRno(rno);
		
		log.info("rno : " + rno);
		log.info("modify : " + vo);
		
		return service.modify(vo) == 1?
				new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 
	}
}
