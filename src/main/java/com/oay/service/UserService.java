package com.oay.service;

import com.oay.entity.User;

import java.util.List;

/*********************************************************
 * @Package: com.oay.service
 * @ClassName: UserService.java
 * @Description： 服务接口类
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-12-23
 *********************************************************/
public interface UserService {

    //  根据ID查询一个用户
    User queryById(Integer id);

    //  查询所有用户
    List<User> queryAll();

    //  修改用户
    User updateUser(User user);

    //  删除一个用户
    int deleteUser(Integer id);

}
