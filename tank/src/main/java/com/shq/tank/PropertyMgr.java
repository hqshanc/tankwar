package com.shq.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties prop = new Properties();
    private volatile static PropertyMgr propMgr;
    private PropertyMgr(){}
    public static PropertyMgr getInstance(){
        if (propMgr == null) {
            synchronized (PropertyMgr.class){
                if (propMgr == null) {
                    propMgr = new PropertyMgr();
                }
            }
        }
        return propMgr;
    }

    static {
        try {
            prop.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config/config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        if (prop == null) return null;
        return (String) prop.get(key);
    }
}
