package com.example.eurekaclient.service;

import com.example.eurekaclient.model.Pagenation;
import com.example.eurekaclient.model.User;
import java.util.List;

public interface UserService {

    User queryStudentById(String id);

    void removeUserById(String id);

    void addUser(User user);

    void editUser(User user);

    Pagenation queryUserByPage(Pagenation pagenation);
}
