
package com.QuanLyChungCu_v2.QuanLyChungCu.services;

import com.QuanLyChungCu_v2.QuanLyChungCu.models.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    List<User> getUsers(Map<String, String> params);

    void addOrUpdate(User u) throws Exception;

    User getUserById(int id);

    void deleteUser(int id);

    boolean isUsernameExists(String username);

    boolean isEmailExists(String email);

    boolean isPhoneExists(String phone);

    int getTotalUsers();

    User getUserByUsername(String username);

    void blockUser(int id) throws Exception;
}
