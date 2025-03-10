package com.example.demo.service.user;

import com.example.demo.entity.user.User;
import com.example.demo.form.FormCreateUser;
import com.example.demo.form.FormUpdateUser;

import java.util.List;

public interface UserService {
    User create( FormCreateUser form);
    User findById(int userId);
    List<User> findAll();
    User findUserByEmail(String email);
    User update(FormUpdateUser form, User user);
    User followUser(int id, int idFollower);
    List<User> searchUser(String keySearch);
    User findUserByJwt(String jwt);


}
