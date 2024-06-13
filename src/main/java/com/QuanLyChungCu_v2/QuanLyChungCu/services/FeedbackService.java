
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Feedback;
import java.util.List;
import java.util.Map;

public interface FeedbackService {

    void addOrUpdate(Feedback fb);

    List<Feedback> getFeedbacks(Map<String, String> params);

    Feedback getFeedbackById(int id);

    void deleteFeedback(int id);

    int getTotalFeedback();
}
