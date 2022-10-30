package com.hspedu.services;

import com.hspedu.dao.UserDAO;
import com.hspedu.javaBean.User;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class UserService {
    private UserDAO userDAO = new UserDAO();
    public User getUserService(String username){
        User user = userDAO.querySingle("select * from usertable where `name` = ?",
                User.class, username);
        return user;
    }
}
