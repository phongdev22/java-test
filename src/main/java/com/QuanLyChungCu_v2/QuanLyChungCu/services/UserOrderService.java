
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Userorder;
import java.util.List;
import java.util.Map;

public interface UserOrderService {

    List<Userorder> getOrders(Map<String, String> params) throws Exception;

    void addOrUpdate(Userorder order);

    Userorder getOrderById(int id);

    void deleteOrder(int id) throws Exception;

    int getTotalOrders();
}
