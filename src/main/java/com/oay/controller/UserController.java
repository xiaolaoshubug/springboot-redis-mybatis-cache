package com.oay.controller;

import com.oay.entity.User;
import com.oay.service.UserService;
import com.oay.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*********************************************************
 * @Package: com.oay.controller
 * @ClassName: UserController.java
 * @Description：  暴露接口
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-12-23
 *********************************************************/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public Result hello() {
        return Result.succ("hello spring");
    }

    @GetMapping("/get/{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        return Result.succ(userService.queryById(id));
    }

    @GetMapping("/get")
    public Result queryAll() {
        return Result.succ(userService.queryAll());
    }

    //  这里为了方便测试使用了get，操作数据一定要用post
    @GetMapping("/update")
    public Result updateUser() {
        //  伪造一个user的修改数据
        User user = new User();
        user.setId(4).setName("学习Redis+Mybatis+Spring Cache");
        return Result.succ(userService.updateUser(user));
    }

    @GetMapping("delete")
    public Result deleteUser() {
        userService.deleteUser(4);
        return Result.succ("删除成功");
    }
}
