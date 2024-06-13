
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

public interface EmailService {
    public void sendMail(String to, String subject, String message) throws Exception;
}
