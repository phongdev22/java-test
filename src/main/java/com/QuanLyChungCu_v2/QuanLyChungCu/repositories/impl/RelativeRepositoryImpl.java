
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Relative;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.RelativeRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@PropertySource("classpath:application.properties")
public class RelativeRepositoryImpl implements RelativeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public void addOrUpdate(Relative relative) {
        Session s = factory.getObject().getCurrentSession();

        if (relative.getId() == null) {
            s.save(relative);
        } else {
            s.update(relative);
        }
    }

    @Override
    public List<Relative> getRelative(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        String hql = "FROM Relative r WHERE 1=1";

        if (params.containsKey("userId") && !params.get("userId").equals("")) {
            hql += " AND r.userId.id = :userId";
        }
        if (params.containsKey("type") && !params.get("type").equals("")) {
            hql += " AND r.type = :type";
        }
        if (params.containsKey("name") && !params.get("name").equals("")) {
            hql += " AND r.name LIKE :name";
        }

        int page = 1;

        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        Query query = s.createQuery(hql);

        if (params.containsKey("type") && !params.get("type").isEmpty()) {
            query.setParameter("type", params.get("type"));
        }
        if (params.containsKey("userId") && !params.get("userId").isEmpty()) {
            query.setParameter("userId", Integer.parseInt(params.get("userId")));
        }
        if (params.containsKey("name") && !params.get("name").isEmpty()) {
            query.setParameter("name", "%" + params.get("name") + "%");
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
    public Relative getRelativeById(int id) {
        Session s = factory.getObject().getCurrentSession();

        return s.get(Relative.class, id);
    }

    @Override
    public void deleteRelative(int id) {
        Session s = factory.getObject().getCurrentSession();
        Relative room = getRelativeById(id);

        s.delete(room);
    }

}
