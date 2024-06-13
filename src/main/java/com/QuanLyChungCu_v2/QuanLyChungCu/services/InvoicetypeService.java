
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoicetype;
import java.util.List;

public interface InvoicetypeService {

    List<Invoicetype> getInvoicetypes();

    Invoicetype getInvoicetypeById(int id);

}
