package org.jahom.demo.service;

import org.jahom.demo.model.User;

/*
 *@author jahom
 *@date 2020/3/20 16:13
 */
public interface UserService {
    public User getUserById(Integer id);

    public int getUserCount(int count);
}
