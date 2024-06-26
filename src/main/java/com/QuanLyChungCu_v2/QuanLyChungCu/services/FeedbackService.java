package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Feedback;
import com.QuanLyChungCu_v2.QuanLyChungCu.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FeedbackService {
    @Autowired
    private FeedbackRepository repo;

    public void CreateFeedback(Feedback feedback){
        repo.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return repo.findAll();
    }

    public Optional<Feedback> getFeedbackById(Integer id) {
        return repo.findById(id);
    }

    public List<Feedback> getFeedbacksByUserId(Integer id) {
        return repo.getFeedbacksByUserId(id);
    }

    public Feedback saveFeedback(Feedback feedback) {
        return repo.save(feedback);
    }

    public void deleteFeedback(Integer id) {
        repo.deleteById(id);
    }

    public boolean deleteFeedbackByIdAndUserId(Integer id, Integer userId) {
//        Optional<Feedback> existingFeedback = repo.findByIdAndUserId(id, userId);
//        if (existingFeedback.isPresent()) {
//            repo.deleteById(id);
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }
}
