package com.keduit.domain;

import lombok.Data;

@Data
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	private Criteria cri; //pageNum, amount를 들고 있음
	
	public PageDTO(int total, Criteria cri) {
		this.total = total;
		this.cri = cri;
		//getPageNum()을 가져와서 / 10.0 으로 실수로 변환한 다음 올림 후 *10 > int로 형변환             
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0) * 10);
		this.startPage = this.endPage-9;
		//total(151)을 실수로 바꿔주기 위해 *1.0 > /cri.getAmount()는 15.1 > ceil로 16 
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	
	
}
