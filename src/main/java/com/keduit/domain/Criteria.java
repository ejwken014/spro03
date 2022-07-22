package com.keduit.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {
	
	private int pageNum;
	   private int amount;
	   
	   //W : Writer T:title C:content
	   private String type; //검색 유형 T, C, W, TC, TCW, CW
	   private String keyword; //검색어
	   
	   public Criteria() {
	      this(1,10); //1페이지에 10개로 기본값을 준 것
	   }
	   
	   public Criteria(int pageNum, int amount) {
	      this.pageNum = pageNum;
	      this.amount = amount;
	   }
	   
	   public String[] getTypeArr() {
	      return type == null? new String[] {}: type.split("");
	   }
	   
	   public String getListLink() {
	      UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
	               .queryParam("pageNum", this.pageNum)
	               .queryParam("amount", this.getAmount())
	               .queryParam("type", this.type)
	               .queryParam("keyword", this.keyword);
	               
	       return builder.toUriString();

}
	   
}
