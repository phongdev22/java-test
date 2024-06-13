
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Roomtype;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.RoomTypeRepository;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoomTypeRepositoryImpl implements RoomTypeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Roomtype> getRoomtypes() {
        Session s = factory.getObject().getCurrentSession();

        return s.createQuery("FROM Roomtype", Roomtype.class).list();
    }

}
