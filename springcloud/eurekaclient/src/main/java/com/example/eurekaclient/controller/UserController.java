package com.example.eurekaclient.controller;

import com.example.eurekaclient.model.Pagenation;
import com.example.eurekaclient.model.User;
import com.example.eurekaclient.model.UserPageResponseVo;
import com.example.eurekaclient.model.UsersResponseVO;
import com.example.eurekaclient.model.VoidResponseVo;
import com.example.eurekaclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/query", produces="application/json",method=RequestMethod.GET)
    public UsersResponseVO queryStudentById(@RequestParam String id) {
        UsersResponseVO usersResponseVO = new UsersResponseVO();

        if(StringUtils.isEmpty(id)) {
            usersResponseVO.setErrorMessage("student id is error!");
            return usersResponseVO;
        }
        User users = userService.queryStudentById( id);
        usersResponseVO.setDatas(users);

        return usersResponseVO;
    }

    @RequestMapping(value="/remove", produces="application/json",method=RequestMethod.GET)
    public VoidResponseVo removeUserById(@RequestParam String id) {
        VoidResponseVo voidResponse = new VoidResponseVo();
        if(StringUtils.isEmpty(id)) {
            voidResponse.setErrorMessage("student id is error!");
            return voidResponse;
        }
        userService.removeUserById( id);
        return voidResponse;
    }

    @RequestMapping(value="/add", produces="application/json",method=RequestMethod.POST)
    public VoidResponseVo addUser(@RequestBody User user) {
        VoidResponseVo voidResponse = new VoidResponseVo();
        if(user==null) {
            voidResponse.setErrorMessage("User information is error!");
            return voidResponse;
        }
        userService.addUser( user);
        return voidResponse;
    }

    @RequestMapping(value="/editUser", produces="application/json",method=RequestMethod.POST)
    public VoidResponseVo editUser(@RequestBody User user) {
        VoidResponseVo voidResponse = new VoidResponseVo();
        if(user==null) {
            voidResponse.setErrorMessage("User information is error!");
            return voidResponse;
        }
        userService.editUser( user);
        return voidResponse;
    }

    @RequestMapping(value="/queryAll", produces="application/json")
    public UserPageResponseVo queryAll(@RequestBody Pagenation pagination) {
        UserPageResponseVo usersResponseVO = new UserPageResponseVo();

        if(pagination==null) {
            usersResponseVO.setErrorMessage("student id is error!");
            return usersResponseVO;
        }
        Pagenation pagenationresult = userService.queryUserByPage( pagination);
        usersResponseVO.setDatas(pagenationresult);
        return usersResponseVO;
    }
}
