
package com.QuanLyChungCu_v2.QuanLyChungCu.repositories;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.User;
import java.util.List;
import java.util.Map;

public interface UserRepository {

    List<User> getUsers(Map<String, String> params);

    void addOrUpdate(User u);

    User getUserById(int id);

    void deleteUser(int id) throws Exception;

    void blockUser(int id);

    boolean isUsernameExists(String username);

    boolean isEmailExists(String email);

    boolean isPhoneExists(String phone);

    int getTotalUsers();

    User getUserByUsername(String username);
}
