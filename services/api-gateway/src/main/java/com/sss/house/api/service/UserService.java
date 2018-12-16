package com.sss.house.api.service;

import com.sss.house.api.common.RestResponse;
import com.sss.house.api.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService class
 *
 * @author Sss
 * @date 2018/12/14
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String getUserName(Long id){
        return userDao.getUsername(id);
    }
}
