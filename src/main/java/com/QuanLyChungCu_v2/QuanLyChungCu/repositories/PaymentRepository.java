
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Payment;
import java.util.List;
import java.util.Map;

public interface PaymentRepository {

    void addPayment(Payment payment);

    List<Payment> getPayments(Map<String, String> params) throws Exception;

}
