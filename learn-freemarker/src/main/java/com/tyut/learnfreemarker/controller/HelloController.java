package com.tyut.learnfreemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by 一伞烟雨 on 2018/1/29.
 */
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        request.setAttribute("index","hello,热112323队署1111");
        // 加入一个属性，用来在模板中读取
        //map.addAttribute("host", "http://blog.didispace.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        System.out.println("nihao11");
        return "index";
    }
    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }
}
