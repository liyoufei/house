package com.sss.house.user.controller;

import com.sss.house.user.common.RestResponse;
import com.sss.house.user.exception.IllegalParamsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * UserController class
 *
 * @author Sss
 * @date 2018/12/14
 */

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${server.port}")
    private int port;

    @RequestMapping("getUsername")
    public RestResponse<String> getUsername(Long id){
        LOGGER.info("incoming request,and port is"+port);
        if(id == null){
            throw new IllegalParamsException(IllegalParamsException.Type.WRONG_PAGE_NUM,"错误的页码");
        }

        return RestResponse.success("test-username");

    }

    @RequestMapping("health")
    public void keepAlive(){
    }
}
