package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Home(Model model){
        System.out.print("Test Home");
        UserEntity user1 = new UserEntity();
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setUsername("johndoe");
        user1.setPassword("password");
        user1.setEmail("john.doe@example.com");
        user1.setPhone("123456789");

        ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
        userList.add(user1);

        model.addAttribute("users", userList);

        return "home";
    }
}
