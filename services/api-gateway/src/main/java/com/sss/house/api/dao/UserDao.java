package com.sss.house.api.dao;

import com.sss.house.api.common.RestResponse;
import com.sss.house.api.config.GenericRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

/**
 * UserDao class
 *
 * @author Sss
 * @date 2018/12/14
 */

@Repository
public class UserDao {

    @Autowired
    GenericRest genericRest;

    public String getUsername(Long id){
        String url = "http://user/getUsername?id="+id;
        RestResponse<String> restResponse = genericRest.get(url, new ParameterizedTypeReference<RestResponse<String>>() {}).getBody();
        return restResponse.getResult();
    }


}
