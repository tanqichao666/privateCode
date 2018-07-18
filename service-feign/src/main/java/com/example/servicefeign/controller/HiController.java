package com.example.servicefeign.controller;

import com.example.servicefeign.model.Pagenation;
import com.example.servicefeign.model.User;
import com.example.servicefeign.model.UserPageResponseVo;
import com.example.servicefeign.model.UsersResponseVO;
import com.example.servicefeign.model.VoidResponseVo;
import com.example.servicefeign.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    private SchedualServiceHi schedualServiceHi;
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @RequestMapping(value="/users/queryAll",method=RequestMethod.POST)
    UserPageResponseVo queryAll(@RequestBody Pagenation pagenation){
            return schedualServiceHi.queryAll(pagenation);
    }

    @RequestMapping(value="/users/editUser",method=RequestMethod.POST)
    VoidResponseVo editUser(@RequestBody User user){
        return schedualServiceHi.editUser(user);
    }

    @RequestMapping(value="/users/query",method=RequestMethod.GET)
    UsersResponseVO queryStudentById(@RequestParam String id) {
        return schedualServiceHi.queryStudentById(id);
    }
    @RequestMapping(value="/users/remove", method=RequestMethod.GET)
    VoidResponseVo removeUserById(@RequestParam String id){
        return schedualServiceHi.removeUserById(id);
    }

    @RequestMapping(value="/users/add",method=RequestMethod.POST)
    VoidResponseVo addUser(@RequestBody User user){
        return schedualServiceHi.addUser(user);
    }
}
