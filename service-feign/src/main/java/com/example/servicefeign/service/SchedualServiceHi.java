package com.example.servicefeign.service;

import com.example.servicefeign.model.Pagenation;
import com.example.servicefeign.model.User;
import com.example.servicefeign.model.UserPageResponseVo;
import com.example.servicefeign.model.UsersResponseVO;
import com.example.servicefeign.model.VoidResponseVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eurekaclient")
public interface SchedualServiceHi {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    @RequestMapping(value="/users/queryAll",method=RequestMethod.POST)
    UserPageResponseVo queryAll(@RequestBody Pagenation pagenation);

    @RequestMapping(value="users/editUser",method=RequestMethod.POST)
    VoidResponseVo editUser(@RequestBody User user) ;

    @RequestMapping(value="/users/query",method=RequestMethod.GET)
    UsersResponseVO queryStudentById(@RequestParam(value = "id") String id) ;

    @RequestMapping(value="/users/remove", method=RequestMethod.GET)
     VoidResponseVo removeUserById(@RequestParam(value = "id") String id) ;

    @RequestMapping(value="/users/add",method=RequestMethod.POST)
    VoidResponseVo addUser(@RequestBody User user);
}