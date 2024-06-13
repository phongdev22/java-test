
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.Feedback;
import java.util.List;
import java.util.Map;

public interface FeedbackRepository {

    void addOrUpdate(Feedback fb);

    List<Feedback> getFeedbacks(Map<String, String> params);

    Feedback getFeedbackById(int id);

    void deleteFeedback(int id);

    int getTotalFeedback();
}
