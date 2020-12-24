package com.oay.service;

import com.oay.entity.User;

import java.util.List;

/*********************************************************
 * @Package: com.oay.service
 * @ClassName: UserService.java
 * @Description：  服务接口类
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-12-23
 *********************************************************/
public interface UserService {

    User queryById(Integer id);

    List<User> queryAll();

    User updateUser(User user);

    int deleteUser(Integer id);

}
