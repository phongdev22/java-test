
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoicetype;
import java.util.List;

public interface InvoicetypeRepository {

    List<Invoicetype> getInvoicetypes();

    Invoicetype getInvoicetypeById(int id);
}
