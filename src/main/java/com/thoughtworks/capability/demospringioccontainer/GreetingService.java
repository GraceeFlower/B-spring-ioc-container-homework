package com.thoughtworks.capability.demospringioccontainer;

import java.util.Date;

public class GreetingService {

    public GreetingService() {
        System.out.println(this + " created when time is" + new Date());
    }

    String sayHi() {
        return "hello world";
    }
}
