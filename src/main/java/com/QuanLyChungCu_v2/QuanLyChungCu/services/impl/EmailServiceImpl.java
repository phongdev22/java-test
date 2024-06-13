//package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;
//
//import com.QuanLyChungCu_v2.QuanLyChungCu.services.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailServiceImpl implements EmailService{
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Override
//    public void sendMail(String email, String subject, String messageSend) throws Exception {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject(subject);
//        message.setText(messageSend);
//
//        javaMailSender.send(message);
//    }
//
//}
