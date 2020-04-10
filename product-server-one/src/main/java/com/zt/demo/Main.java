package com.zt.demo;

import com.zt.demo.entity.DeUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhutong
 * @date 2020/4/10 19:45
 */
public class Main {
    
    public static void main(String[] args) {
        List<DeUser> list = new ArrayList<>();
        Map<Integer,DeUser> map = MapUtil.listToMap(list, DeUser::getAge, b -> b);
    }
}
