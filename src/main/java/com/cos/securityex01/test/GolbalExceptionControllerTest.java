package com.cos.securityex01.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GolbalExceptionControllerTest {
	
	@ExceptionHandler(value = {NullPointerException.class,IllegalArgumentException.class})
	// 하나가 아니라 두개를 걸고 싶다면? { , }
	
	public String 널_일리걸아규먼트_익셉션(Exception e) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h1>해당 아규먼트로 찾을 수 있는 정보가 없습니다.</h1>");
		sb.append("<script>alert('"+ e.getMessage()+"');");
		sb.append("location.href='/';</script>");
		return sb.toString();
	}
}
