package com.learn.core.service;


import com.learn.core.bean.Work;
import com.learn.core.dao.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Created by 一伞烟雨 on 2018/2/1.
 */

@Service
public class WorkService {
    @Autowired
    private WorkMapper workMapper;

    public List<Work> getList(){
        return workMapper.selectByExample(null);
    }
}
