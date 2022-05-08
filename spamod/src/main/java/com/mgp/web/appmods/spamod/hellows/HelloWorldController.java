package com.mgp.web.appmods.spamod.hellows;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

//    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    @GetMapping(path = "hello")
    public String hello(){
        return "Hello machis";
    }

    @GetMapping(path = "/helloBean")
    public HellowBean hellowBean() {
        return new HellowBean("HellowBean Dude");
    }

    @GetMapping(path = "/helloBean/path-var/{message}")
    public HellowBean helloBeanWithPathVar(@PathVariable String message) {
        return new HellowBean(String.format("Hello helu now to %s",message));
    }
}
