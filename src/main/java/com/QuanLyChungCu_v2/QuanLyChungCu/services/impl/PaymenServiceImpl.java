
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Payment;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.PaymentRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.PaymentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymenServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Override
    public void addPayment(Payment payment) {
        paymentRepo.addPayment(payment);
    }

    @Override
    public List<Payment> getPayments(Map<String, String> params) throws Exception {
        return paymentRepo.getPayments(params);
    }

}
