package com.example.user

import org.example.model.UserDto
import java.time.LocalDateTime

fun main() {
	val userService = UserService()
	userService.logic(UserDto())
}

class UserService {
	fun logic(userDto: UserDto?) {
		val temp = UserDto(
			null,
			10,
			"홍길동@gmail.com",
			"010-1234-5678",
			LocalDateTime.now()
		)
		
		temp.name?.let {
			println(it)
		}?: println("이름이 없습니다.")
		
	}
}