package com.flyex.servicesImpl;

import com.flyex.services.Custom;

public class CustomImpl implements Custom {
    @Override
    public String buy() {
        System.out.println("买买买");
        return "买买买";
    }

    @Override
    public void addCustom(String name) {
        System.out.println("来了："+name);
    }

    @Override
    public void goShopping() {
        System.out.println("购物咯");
    }

    @Override
    public void havaException() throws Exception {
        System.out.println("异常开始");
        throw new Exception("on focus");
    }
}
