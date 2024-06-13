
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyanswer;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.SurveyAnswerRepository;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SurveyAnswerRepositoryImpl implements SurveyAnswerRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Surveyanswer> getSurveyAnswers(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        String hql = "FROM Surveyanswer sa WHERE 1=1";

        if (params.containsKey("surveyId") && !params.get("surveyId").equals("")) {
            hql += " AND sa.responseId.surveyId.id = :surveyId";
        }
        if (params.containsKey("questionId") && !params.get("questionId").equals("")) {
            hql += " AND sa.questionId.id = :questionId";
        }

        Query query = s.createQuery(hql);

        if (params.containsKey("surveyId") && !params.get("surveyId").equals("")) {
            query.setParameter("surveyId", params.get("surveyId"));
        }
        if (params.containsKey("questionId") && !params.get("questionId").equals("")) {
            query.setParameter("questionId", params.get("questionId"));
        }

        return query.getResultList();
    }

    @Override
    public void addSurveyAnswer(Surveyanswer surveyAnswer) {
        Session s = factory.getObject().getCurrentSession();

        s.save(surveyAnswer);
    }

}
