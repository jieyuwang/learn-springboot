package com.tyut.learnfreemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Created by 一伞烟雨 on 2018/2/28.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "你好";
    }

}
