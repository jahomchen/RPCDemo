package org.jahom.demo;

import org.jahom.demo.model.User;
import org.jahom.demo.service.UserService;

/*
 *@author jahom
 *@date 2020/3/20 16:50
 */
public class UserServiceImp implements UserService {
    @Override
    public User getUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("name"+id);
        return user;
    }

    @Override
    public int getUserCount(int count) {
        return count;
    }
}
