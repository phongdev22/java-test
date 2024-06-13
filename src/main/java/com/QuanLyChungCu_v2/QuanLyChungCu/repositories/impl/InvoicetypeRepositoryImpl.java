
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Invoicetype;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.InvoicetypeRepository;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class InvoicetypeRepositoryImpl implements InvoicetypeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Invoicetype> getInvoicetypes() {
        Session s = factory.getObject().getCurrentSession();

        return s.createQuery("FROM Invoicetype", Invoicetype.class).list();
    }

    @Override
    public Invoicetype getInvoicetypeById(int id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(Invoicetype.class, id);
    }

}
