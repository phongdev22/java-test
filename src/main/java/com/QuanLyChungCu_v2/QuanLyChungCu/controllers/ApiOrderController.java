
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.User;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Userorder;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserOrderService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/orders")
public class ApiOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(Model model, @PathVariable("orderId") int orderId) {
        try {
            userOrderService.deleteOrder(orderId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ex.getMessage(), ex);
        }
    }

    @PostMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmOrder(@PathVariable("orderId") int orderId) {
        try {
            Userorder order = userOrderService.getOrderById(orderId);
            order.setStatus("Received");

            userOrderService.addOrUpdate(order);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ex.getMessage(), ex);
        }
    }

    @GetMapping(path = "/", consumes = {
            MediaType.ALL_VALUE
    })
    public ResponseEntity<?> getOrders(@RequestParam Map<String, String> params) {
        try {
            User user = userService.getUserById(Integer.parseInt(params.get("userId")));
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "User not found with ID: " + params.get("userId"));
            }

            List<Userorder> orders = userOrderService.getOrders(params);

            return ResponseEntity.status(HttpStatus.OK).body(orders);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }
}
