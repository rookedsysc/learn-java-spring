package com.example.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.restapi.model.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class RestApiApplicationTests {
	// Spring에서 관리하는 Bean을 주입받는다.
	@Autowired
	private ObjectMapper objectMapper;
	@Test
	void contextLoads() throws JsonProcessingException {
		UserRequest user = new UserRequest("홍길동", "rookedsysc36@gmail.com", 30);
		
		var json = objectMapper.writeValueAsString(user);
		// {"userName":"홍길동","userEmail":"rookedsysc36@gmail.com","age":30,"nmae":"홍길동","personEmail":"rookedsysc36@gmail.com","bornIn":30}
		System.out.println(json);
	}
}
