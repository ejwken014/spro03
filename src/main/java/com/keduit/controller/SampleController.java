package com.keduit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.SampleVO;
import com.keduit.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	//@Controller를 사용할 때에는 메소드 명과 일치하는 jsp를 호출하지만
	//@RestController 는 return 값 그대로를 반환한다.
	@GetMapping(value="/getText",produces="text/plain; charset=UTF-8")
	//RestController는 value 뒤에 produces를 붙여야함
	public String getText() {
		log.info("MIME TYPE : "+MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요.";
	}
	
	@GetMapping(value="/getSample",
			produces= {MediaType.APPLICATION_JSON_VALUE,
			           MediaType.APPLICATION_XML_VALUE})
	
	public SampleVO getSample() {
		return new SampleVO(1004, "스타", "로드");
	}
	
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		return new SampleVO(1004, "로켓","라군");
	}
	
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		return IntStream
				.range(1, 10)
				.mapToObj(i-> new SampleVO(i, i+ "First", i +"Last"))
				.collect(Collectors.toList());		
	}
	
	@GetMapping(value="/getMap")
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(1004, "그루트","주니어"));
		return map;
	}
	
	@GetMapping(value="/check", params= {"height","weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		//생성자가 SampleVO(int, String, String)이기 때문에
		//height와 weight는 int형으로 파라미터로 넣을 수 없어
		//""+height 를 하여 문자열로 변환해줌
		SampleVO vo = new SampleVO(0,""+ height, ""+weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) { //status는 상태코드
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {                           //status(HttpStatus.OK)는 200
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid) {
		return new String[] {"category : "+cat, "productid : "+pid};
	}
	
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert............"+ticket);
		
		return ticket;
	}
}
