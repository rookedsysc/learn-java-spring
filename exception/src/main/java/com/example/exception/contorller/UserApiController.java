package com.example.exception.contorller;

import java.rmi.AccessException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.exception.model.UserResponse;
import com.example.exception.model.UserApi;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

  private static List<UserResponse> userList = List.of(
    UserResponse.builder().id("1").name("홍길동").age(10).build(),
    UserResponse.builder().id("2").name("유관순").age(10).build(),
    UserResponse.builder().id("3").name("잭스패로우").age(10).build()
  );
  // 먼저 처리되게 하고 싶으면 MIN_VALUE로 설정해주면 되고
  // 가장 나중에 처리되게 하고 싶으면 MAX_VALUE로 설정해준다
  @GetMapping("id/{user_id}")
  public UserApi<UserResponse> getUser(
    @PathVariable(name = "user_id") String userId
  ) throws AccessException {
    if(userId.equals("2")) {
      throw new AccessException("사용자 접근 오류");
    }

    // 여기서 선언한 it는 userList의 stream을 반복하는 것이고
    // 각 요소에서 id와 값이 같은 것을 찾아서 처음 값을 반환한다.
    var user = userList.stream().filter(userElement -> userElement.getId().equals(userId)).findFirst().get();

    UserApi<UserResponse> resposne = UserApi.<UserResponse>builder().resultCode(HttpStatus.OK.toString())
        .resultMessage(HttpStatus.OK.name()).data(user).build();
    return resposne;
  }
  
}
