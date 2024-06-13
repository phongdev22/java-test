
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoice;
import java.util.List;
import java.util.Map;

public interface InvoiceService {

    List<Invoice> getInvoices(Map<String, String> params);

    void addOrUpdate(Invoice invoice);

    Invoice getInvoiceById(int id);

    void deleteInvoice(int id) throws Exception;

    int getTotalInvoices();
}
