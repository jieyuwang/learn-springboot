package com.learn.core.service;


import com.learn.core.bean.School;
import com.learn.core.dao.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*
 * @author Created by 一伞烟雨 on 2018/2/1.

*/

@Service
public class SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    public List<School> getList(){
        return schoolMapper.selectByExample(null);
    }

}
