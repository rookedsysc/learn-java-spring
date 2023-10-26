package hello.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @GetMapping("hello")
  public String hello(Model model) {
    /// data 라는 이름으로 hello!! 라는 값을 넘긴다.
    model.addAttribute("data", "hello!!");
    /// hello 라는 템플릿을 가서 찾아라
    return "hello";
  }
}
