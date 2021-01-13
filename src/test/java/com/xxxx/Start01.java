package com.xxxx;

import com.xxxx.dao.UserDao;
import com.xxxx.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start01 {
    @SuppressWarnings("All")
    public static void main(String[] args) {
//        MyClassPathXMLApplication spring = new MyClassPathXMLApplication("spring.xml");
//        UserDao userDao = (UserDao) spring.getBean("userDao");
//        UserService userService = (UserService) spring.getBean("userService");
//        userService.test();
//        userDao.test();

        //多配置文件导入
        ApplicationContext ac = new ClassPathXmlApplicationContext("import.xml");
        UserDao userDao = (UserDao) ac.getBean("userDao");
        UserService userService = (UserService) ac.getBean("userService");
        userDao.test();
        userService.test();
    }
}
