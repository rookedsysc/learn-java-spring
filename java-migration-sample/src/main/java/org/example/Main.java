package org.example;

import com.example.user.UserModel;

public class Main {
  public static void main(String[] args) {
    var userModel = new UserModel(
        "홍길동", 10, "홍길동@gmail.com"
    );

    /// data class를 썻기 때문에 toString()이 자동으로 생성됨
    System.out.println(userModel.toString());
  }
}