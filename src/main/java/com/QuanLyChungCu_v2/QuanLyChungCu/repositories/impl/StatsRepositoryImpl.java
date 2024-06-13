
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Survey;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyanswer;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyoption;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyquestion;
import com.QuanLyChungCu_v2.QuanLyChungCu.models.Surveyresponse;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.StatsRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

        @Autowired
        private LocalSessionFactoryBean factory;

        @Override
        public List<Object[]> getReportForSurveyQuestion(int surveyId, int questionId) {
                Session s = this.factory.getObject().getCurrentSession();
                CriteriaBuilder b = s.getCriteriaBuilder();
                CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

                Root surveyQuestion = q.from(Surveyquestion.class);

                q.where(
                                b.equal(surveyQuestion.get("surveyId").get("id"), surveyId),
                                b.equal(surveyQuestion.get("id"), questionId));

                Join<Surveyquestion, Surveyoption> optionsJoin = surveyQuestion.join("surveyoptionSet", JoinType.LEFT);
                Join<Surveyoption, Surveyanswer> answersJoin = optionsJoin.join("surveyanswerSet", JoinType.LEFT);

                Expression<Long> answersCount = b.count(answersJoin);

                q.multiselect(
                                surveyQuestion.get("surveyId").get("title"),
                                surveyQuestion.get("questionText"),
                                surveyQuestion.get("questionType"),
                                optionsJoin.get("optionText"),
                                answersCount);

                q.groupBy(
                                surveyQuestion.get("id"), // Group by question
                                optionsJoin.get("id") // Group by option
                );

                List<Object[]> reportResults = s.createQuery(q).getResultList();

                return reportResults;
        }

        @Override
        public List<Object[]> getCountResponseForSurvey() {
                Session s = this.factory.getObject().getCurrentSession();
                CriteriaBuilder b = s.getCriteriaBuilder();
                CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

                Root survey = q.from(Survey.class);

                Join<Survey, Surveyresponse> responsesJoin = survey.join("surveyresponseSet", JoinType.LEFT);

                q.multiselect(
                                survey.get("id"),
                                survey.get("title"),
                                b.count(responsesJoin));

                q.groupBy(survey.get("id"));

                List<Object[]> results = s.createQuery(q).getResultList();

                return results;
        }

}
