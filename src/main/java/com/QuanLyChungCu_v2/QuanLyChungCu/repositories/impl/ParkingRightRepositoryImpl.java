
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.ParkingRight;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.ParkingRightRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@PropertySource("classpath:application.properties")
public class ParkingRightRepositoryImpl implements ParkingRightRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public void addOrUpdate(ParkingRight pr) {
        Session s = factory.getObject().getCurrentSession();
        Date currentDate = new Date();

        if (pr.getId() == null) {
            pr.setStatus("Pending");
            pr.setCreatedAt(currentDate);
            s.save(pr);
        } else {
            pr.setUpdatedAt(currentDate);
            s.update(pr);
        }
    }

    @Override
    public List<ParkingRight> getParkingRight(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        String hql = "FROM ParkingRight pr WHERE 1=1";

        if (params.containsKey("roomId") && !params.get("roomId").equals("")) {
            hql += " AND pr.relativeId.userId.room.id = :roomId";
        }
        if (params.containsKey("status") && !params.get("status").equals("")) {
            hql += " AND pr.status = :status";
        }

        int page = 1;

        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        Query query = s.createQuery(hql);

        if (params.containsKey("roomId") && !params.get("roomId").isEmpty()) {
            query.setParameter("roomId", Integer.valueOf(params.get("roomId")));
        }
        if (params.containsKey("status") && !params.get("status").isEmpty()) {
            query.setParameter("status", params.get("status"));
        }

        int pageSize = Integer.parseInt(env.getProperty("user.pageSize"));

        if (!params.containsKey("list")) {
            int startPosition = (page - 1) * pageSize;
            query.setFirstResult(startPosition);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }

    @Override
    public ParkingRight getParkingRightById(int id) {
        Session s = factory.getObject().getCurrentSession();

        return s.get(ParkingRight.class, id);
    }

    @Override
    public void deleteParkingRight(int id) {
        Session s = factory.getObject().getCurrentSession();
        ParkingRight pr = getParkingRightById(id);

        s.delete(pr);
    }

    @Override
    public int getTotalParkingRights() {
        Session s = factory.getObject().getCurrentSession();
        Query query = s.createQuery("SELECT COUNT(*) FROM ParkingRight");

        return ((Number) query.getSingleResult()).intValue();
    }

}
