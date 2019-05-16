package com.luke.web.tool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luke on 2018/11/2.
 */
public class LKMap<K,V> extends HashMap<K,V> {

    public LKMap(){

    }
    public LKMap put1(K key, V value) {
        super.put(key, value);
        return this ;
    }
    public LKMap put2(Map<? extends K,? extends V> map) {
        super.putAll(map);
        return this ;
    }
}
