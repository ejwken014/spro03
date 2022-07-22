package com.keduit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//(필드의 모든 값을 파라미터로 가지는 생성자를 만들어줌)
@NoArgsConstructor//(기본생성자를 만들어주는 어노테이션)
public class SampleVO {
	private Integer Mno;
	private String firstName;
	private String lastName;
	
}
