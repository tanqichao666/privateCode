package com.example.eurekaclient.service;

import com.example.eurekaclient.dao.UserDao;
import com.example.eurekaclient.model.Pagenation;
import com.example.eurekaclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User queryStudentById(String id) {
        return userDao.queryStudentById(id);
    }

    @Override
    public void removeUserById(String id) {
        userDao.removeUserById(id);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public Pagenation queryUserByPage(Pagenation pagenation) {
        String address = pagenation.getAddress();
        String age = pagenation.getAge();
        String name = pagenation.getName();
        String pageSize = pagenation.getPageSize();
        int pageS = Integer.parseInt(pageSize);
        int count  = userDao.getUserCount(name,age,address);
        pagenation.setCount(count);
        int ceil = (int)Math.ceil(count* 1.0 / pageS );
        String pageNo = pagenation.getPageNo();
        int pageN = Integer.parseInt(pageNo);
        if(ceil<pageN){
            if(ceil>0){
                pageN=ceil;
                pagenation.setPageNo(ceil+"");
            }else {
                pageN = 1;
            }

        }
        int fromIndex = (pageN-1)*pageS;
        List<User> users = userDao.queryUserByPage(fromIndex,pageS,name,age,address);
        pagenation.setUserList(users);
        return pagenation;
    }

}
