package com.zt.demo.controller;

import com.zt.demo.dao.DeUserMapper;
import com.zt.demo.entity.DeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("demo")
@RestController
public class Demo {


	@Resource
    private DeUserMapper deUserMapper;

    @GetMapping("/demo")
    public DeUser demo(){
        return deUserMapper.selectByPrimaryKey("1");
    }
}
