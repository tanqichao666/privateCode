package com.example.eurekaclient.dao;

import com.example.eurekaclient.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    User queryStudentById(@Param("id") String id);

    void removeUserById(@Param("id")String id);

    void addUser(@Param("user")User user);

    void editUser(@Param("user")User user);

    int getUserCount(@Param("name") String name,@Param("age") String age,@Param("address") String address);

    List<User> queryUserByPage(@Param("fromIndex")int fromIndex, @Param("pageS")int pageS, @Param("name")String name, @Param("age")String age, @Param("address")String address);
}
