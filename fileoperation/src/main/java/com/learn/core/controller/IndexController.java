package com.learn.core.controller;


import com.learn.core.service.SchoolService;
import com.learn.core.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
*
 * @author Created by 一伞烟雨 on 2018/1/29.

*/


@Controller
public class IndexController {

    @Autowired
    private WorkService workService;

    @Autowired
    private SchoolService schoolService;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mv,String result){
        mv.setViewName("index");
        mv.addObject("workList",workService.getList())
                .addObject("schoolList",schoolService.getList())
                .addObject("result",result);
        return mv;
    }
    @RequestMapping("/index")
    public String index1(ModelAndView mv, String result){

        mv.addObject("workList",workService.getList())
                .addObject("schoolList",schoolService.getList())
                .addObject("result",result);
        return "index";
    }
    @RequestMapping("/admin")
    public ModelAndView admin(ModelAndView mv) throws Exception {
        mv.setViewName("download");
        mv.addObject("workList",workService.getList());
        return mv;
    }
    @RequestMapping("/show")
    public ModelAndView show(ModelAndView mv,
                             String id) throws Exception{
        mv.setViewName("show");
        //文件路径
        File directory = new File("");
        String courseFile = directory.getCanonicalPath()+"/src/main/resources/images/"+id+"/";
        File directory1 = new File(courseFile);
        if (!directory1.exists()){
            ModelAndView mv1 = new ModelAndView();
            mv1.setViewName("redirect:"+"/");
            return mv1.addObject("result", "empty");
        }
        System.out.println("进来show了");
        List<String> map = new ArrayList<>();
        int counnt = 0;
        for(File temp:directory1.listFiles()){
            if(temp.isFile()){
                String temp1 = temp.toString();
                counnt+=1;
//                System.out.println(map);
//                mv.addObject("works",temp1.substring(temp1.lastIndexOf("\\")+1));
                String context = temp1.substring(temp1.lastIndexOf("/") + 1);
                map.add(context);
            }
        }
        mv.addObject("result","success");
        mv.addObject("works",map);
        mv.addObject("counnt",counnt);
        return mv;
    }
}
