package com.sss.house.api.controller;

import com.sss.house.api.common.RestResponse;
import com.sss.house.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiUserController class
 *
 * @author Sss
 * @date 2018/12/14
 */

@RestController
public class ApiUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "test/getUsername")
    public RestResponse<String> getUsername(Long id){
        return RestResponse.success(userService.getUserName(id));
    }
}
