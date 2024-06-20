package com.QuanLyChungCu_v2.QuanLyChungCu.controllers;

import com.QuanLyChungCu_v2.QuanLyChungCu.dto.InvoiceDTO;
import com.QuanLyChungCu_v2.QuanLyChungCu.dto.ResponseData;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Room;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.InvoiceService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.RoomService;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.UserEntityService;
import com.QuanLyChungCu_v2.QuanLyChungCu.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public String Index(){
        return "page-invoice";
    }

    @GetMapping("/list")
    public String GetAll(@RequestParam(defaultValue = "1") int currentPage,
                         @RequestParam(defaultValue = "10") int pageSize,
                         @RequestParam(defaultValue = "") String keyword,
                         Model model){

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        List<Invoice> invoices;

        if (keyword == null || keyword.isEmpty()) {
            invoices = invoiceService.GetAll(pageable).getContent();
        } else {
            keyword = keyword.toLowerCase();
            invoices = invoiceService.GetAllByKeyword(pageable, keyword).getContent();
        }

        model.addAttribute("invoices", invoices);
        return "list-invoice";
    }

    @GetMapping("/create")
    public String CreteInvoice(Model model){
        InvoiceDTO invoice = new InvoiceDTO();
        invoice.setPaymentCode(Utils.generateCode(6));
        invoice.setIsPaid(false);

        model.addAttribute("invoice",invoice);
        model.addAttribute("rooms", roomService.findAll());
        return "form-invoice";
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> CreateNew(@ModelAttribute InvoiceDTO invoiceDTO){
        Invoice invoice = new Invoice();
        System.out.println(invoiceDTO);
        invoice.setRoomId(invoiceDTO.getRoomId());
        invoice.setPaymentCode(invoiceDTO.getPaymentCode());
        invoice.setServiceType(invoiceDTO.getServiceType());
        invoice.setAmount(invoiceDTO.getAmount());
        invoice.setDescription(invoiceDTO.getDescription());
        invoice.setIsPaid(false);
        invoice.setDueDate(invoiceDTO.getDueDate());
        invoice.setCreatedAt(new Date());
        invoice.setUpdatedAt(new Date());

        invoiceService.Save(invoice);

        HashMap<String, Object> res = new HashMap<>();
        res.put("code",0);
        res.put("message","Create Success");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{invoiceId}")
    public String Details(Model model){
        return "form-invoice";
    }

    @DeleteMapping("/delete/{invoiceId}")
    public ResponseEntity<Map<String, Object>> Delete(@PathVariable("invoiceId")Integer invoiceId){
        try{
            invoiceService.Delete(invoiceId);

            HashMap<String, Object> res = new HashMap<>();
            res.put("code",0);
            res.put("message","Delete successfully!");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            HashMap<String, Object> res = new HashMap<>();
            res.put("code", 1);
            res.put("message","Delete fail!");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    private List<InvoiceDTO> parseListInvoiceDTO(List<Invoice> invoices, RoomService roomService){
        List<InvoiceDTO> result = new ArrayList<>();

        for(Invoice invoice : invoices){
            Room room = roomService.getRoomById(invoice.getRoomId());
            result.add(parseInvoiceDTO(room, invoice));
        }

        return result;
    }

    private InvoiceDTO parseInvoiceDTO(Room room, Invoice invoice){

        InvoiceDTO dto = new InvoiceDTO();

        dto.setId(invoice.getId());
        dto.setRoomId(room.getId());
        dto.setServiceType(invoice.getServiceType());
        dto.setDescription(invoice.getDescription());
        dto.setAmount(invoice.getAmount());
        dto.setStatus(invoice.getStatus());
        dto.setIsPaid(invoice.getIsPaid());
        dto.setDueDate(invoice.getDueDate());

        return dto;
    }
}
