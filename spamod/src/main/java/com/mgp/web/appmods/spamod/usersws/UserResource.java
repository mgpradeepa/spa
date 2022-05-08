package com.mgp.web.appmods.spamod.usersws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserResource {

    @Autowired
    UserDaoService userDaoService;

    @RequestMapping(path="/allUsers")
    public List<User> findAllUser() {
        return userDaoService.findAll();
    }

    @GetMapping(path="/{id}")
    public User findSpecificUser(@PathVariable Integer id) {
        return userDaoService.findById(id);
    }

    public void storeUser(@RequestBody User user){
        
    }
}
