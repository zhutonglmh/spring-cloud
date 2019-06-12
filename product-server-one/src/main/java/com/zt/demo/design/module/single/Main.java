package com.zt.demo.design.module.single;

import com.zt.demo.design.module.single.single1.Single;

/**
 * @author zhutong
 * @date 2019/5/16 20:39
 */
public class Main {
    
    public static void main(String[] args) {
        //1
        System.out.println(Single.getSingle()  == Single.getSingle());
        //2
        System.out.println(com.zt.demo.design.module.single.single2.Single.getSingle() == com.zt.demo.design.module.single.single2.Single.getSingle());
        //3
        System.out.println(com.zt.demo.design.module.single.single3.Single.getSingle() == com.zt.demo.design.module.single.single3.Single.getSingle());
        //4
        System.out.println(com.zt.demo.design.module.single.single4.Single.getSingle() == com.zt.demo.design.module.single.single4.Single.getSingle());
        //5
        System.out.println(com.zt.demo.design.module.single.single5.Single.getSingle() == com.zt.demo.design.module.single.single5.Single.getSingle());
        //6
        System.out.println(com.zt.demo.design.module.single.single6.Single.getSingle() == com.zt.demo.design.module.single.single6.Single.getSingle());
        //7
        System.out.println(com.zt.demo.design.module.single.single7.Single.getSingle() == com.zt.demo.design.module.single.single7.Single.getSingle());
        //8
        System.out.println(com.zt.demo.design.module.single.single8.Single.INSTANCE == com.zt.demo.design.module.single.single8.Single.INSTANCE);
        
    }
}
