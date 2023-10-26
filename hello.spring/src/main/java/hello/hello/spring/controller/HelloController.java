package hello.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  @GetMapping("hello")
  public String hello(Model model) {
    /// data 라는 이름으로 hello!! 라는 값을 넘긴다.
    model.addAttribute("data", "hello!!");
    /// hello 라는 템플릿을 가서 찾아라
    return "hello";
  }

  /// localhost:8080/hello-mvc?name=spring
  @GetMapping("hello-mvc")
  public String hello(@RequestParam("name") String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
  }

  /// 템플릿 엔진에 넣어주는게 아니라 http body에 데이터를 직접 넣어주겠다는 의미
  /// 웹에서 보면 그냥 쌩 문자열이 들어있음
  @GetMapping("hello-string")
  /// http body에 데이터를 넣어주겠다느 의미
  @ResponseBody
  public String helloString(@RequestParam("name") String name) {
    return "hello " + name; // "hello spring"
  }

  /// Json으로 데이터 내려줌
  @GetMapping("hello-api")
  @ResponseBody
  public Hello helloApi(@RequestParam("name") String name) {
    Hello hello = new Hello();
    hello.setName(name);
    return hello;
  }

  static class Hello {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
