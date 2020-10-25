package com.thoughtworks.capability.demospringioccontainer;

import org.springframework.beans.factory.annotation.Autowired;

public class Foo {

    private Bar bar;

    @Autowired
    public void SetBar(Bar bar) {
        this.bar = bar;
    }

    public void hi() {
        System.out.println("Hi, " + bar.name());
    }

    public String name() {
        return "Foo";
    }
}
