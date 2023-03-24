package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //templates에 있는 hello.html을 랜더링
    }
    @GetMapping("hello-mvc")
    public String hello_mvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-mvc";
    }
    @GetMapping("hello-string")
    @ResponseBody //body 부분에 직접 넣어주겠다.
    public  String helloString(@RequestParam("name") String name){
        return "hello "+ name; //"hello spring"을 그대로 보냄
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체네? 객체가 오면 디폴트로 json 방식의 파일로 만들어 http로 반환하겠다 언제? @ResponseBody가 있을때
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
