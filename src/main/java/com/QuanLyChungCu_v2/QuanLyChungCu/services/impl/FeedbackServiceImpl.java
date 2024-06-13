
package com.QuanLyChungCu_v2.QuanLyChungCu.services.impl;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Feedback;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.FeedbackRepository;
import com.QuanLyChungCu_v2.QuanLyChungCu.services.FeedbackService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepo;

    @Override
    public void addOrUpdate(Feedback fb) {
        feedbackRepo.addOrUpdate(fb);
    }

    @Override
    public List<Feedback> getFeedbacks(Map<String, String> params) {
        return feedbackRepo.getFeedbacks(params);
    }

    @Override
    public Feedback getFeedbackById(int id) {
        return feedbackRepo.getFeedbackById(id);
    }

    @Override
    public void deleteFeedback(int id) {
        feedbackRepo.deleteFeedback(id);
    }

    @Override
    public int getTotalFeedback() {
        return feedbackRepo.getTotalFeedback();
    }

}
