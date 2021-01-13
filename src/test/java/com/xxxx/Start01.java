package com.xxxx;

import com.xxxx.dao.UserDao;
import com.xxxx.service.UserService;

public class Start01 {
    @SuppressWarnings("All")
    public static void main(String[] args) {
        MyClassPathXMLApplication spring = new MyClassPathXMLApplication("spring.xml");
        UserDao userDao = (UserDao) spring.getBean("userDao");
        UserService userService = (UserService) spring.getBean("userService");
        userService.test();
        userDao.test();

    }
}
