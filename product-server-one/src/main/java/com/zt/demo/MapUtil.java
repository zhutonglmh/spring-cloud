package com.zt.demo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhutong
 * @date 2020/4/10 19:35
 */
public class MapUtil {
    
    public static <K,V> Map<K,V> listToMap(List<V> list, Function<V,K> key,Function<V,V> value){
        return list.stream().collect(Collectors.toMap(key,value,(a,b) -> b));
    }
}
