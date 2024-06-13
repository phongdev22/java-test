
package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.InvoiceService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RoomService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
public class ApiInvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    public ResponseEntity<?> getInvoices(@RequestParam Map<String, String> params) {
        try {
            Room room = roomService.getRoomById(Integer.parseInt(params.get("room")));
            if (room == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Room not found with ID: " + params.get("room"));
            }

            List<Invoice> invoices = invoiceService.getInvoices(params);

            return ResponseEntity.status(HttpStatus.OK).body(invoices);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }
}
