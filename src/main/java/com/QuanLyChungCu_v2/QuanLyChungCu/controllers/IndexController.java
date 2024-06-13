
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Locker;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Survey;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.InvoicetypeService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.LockerService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RoomService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RoomTypeService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.SurveyService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ControllerAdvice
@PropertySource("classpath:application.properties")
public class IndexController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private Environment env;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private LockerService lockerService;

    @Autowired
    private InvoicetypeService invoicetypeService;

    @Autowired
    private SurveyService surveyService;

    @ModelAttribute
    public void commonAtrr(Model model, @RequestParam Map<String, String> paramsRequest) {
        model.addAttribute("roomtypes", roomTypeService.getRoomtypes());
        model.addAttribute("invoicetypes", invoicetypeService.getInvoicetypes());

        Map<String, String> paramsLocker = new HashMap<>();
        paramsLocker.put("status", "Using");
        paramsLocker.put("list", "true");
        List<Locker> lockers = lockerService.getLockers(paramsLocker);

        Map<String, String> paramsRoom = new HashMap<>();
        paramsRoom.put("status", "Rented");
        paramsRoom.put("list", "true");
        List<Room> rooms = roomService.getRooms(paramsRoom);

        Map<String, String> paramsSurvey = new HashMap<>();
        paramsSurvey.put("list", "true");
        List<Survey> surveys = surveyService.getSurveys(paramsSurvey);

        model.addAttribute("roomsUsing", rooms);
        model.addAttribute("lockersUsing", lockers);
        model.addAttribute("surveys", surveys);

    }

    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        List<Room> rooms = roomService.getRooms(params);

        int totalRooms = roomService.getTotalRooms();
        int pageSize = Integer.parseInt(env.getProperty("user.pageSize"));
        int totalPages = (int) Math.ceil((double) totalRooms / pageSize);

        model.addAttribute("rooms", rooms);
        model.addAttribute("totalPages", totalPages);

        return "index";
    }
}
