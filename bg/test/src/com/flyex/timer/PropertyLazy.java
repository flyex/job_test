package com.flyex.timer;

import java.util.Properties;

public class PropertyLazy {
    public static Properties prop = null;

    public static Properties getProps() throws Exception{
        if (prop == null){
            synchronized (PropertyLazy.class){
                if (prop == null){
                    prop = new Properties();
                    prop.load(PropertyLazy.class.getClassLoader().getResourceAsStream("collect.properties"));
                }
            }
        }
        return prop;
    }
}
