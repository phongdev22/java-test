
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.services.LockerService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LockerController {

    @Autowired
    private LockerService lockerService;

    @Autowired
    private Environment env;

    @GetMapping("/lockers")
    public String createView(Model model, @RequestParam Map<String, String> params) {

        int totalUsers = lockerService.getTotalLockers();
        int pageSize = Integer.parseInt(env.getProperty("user.pageSize"));
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("lockers", lockerService.getLockers(params));
        return "locker_list";
    }

}
