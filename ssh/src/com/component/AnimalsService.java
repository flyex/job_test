package com.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("animalsService")
public class AnimalsService {
@Autowired
@Qualifier("cats")
    private animals a;
    public void junk(){
        this.a.bark();
    }
    public static void main(String[] args) {
        AnimalsService b = new AnimalsService();
        b.junk();
    }
}
