package com.mgp.web.appmods.spamod.usersws;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static final List<User> usersList  = new ArrayList<>();

    private static int usersCount = 3;

    static {
        usersList.add(new User("Honda",100, new Date()));
        usersList.add(new User("Volvo",101, new Date()));
        usersList.add(new User("Mazda",103, new Date()));

    }

    public List<User> findAll() {
        return usersList;
    }

    public User findById(Integer id) {
        return usersList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);

    }
    public User save(User user ){
        if(user.getId() == null) user.setId(++usersCount);
        usersList.add(user);
        return user;
    }
}
