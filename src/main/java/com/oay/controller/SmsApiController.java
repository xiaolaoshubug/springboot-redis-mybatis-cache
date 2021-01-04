package com.oay.controller;

import com.oay.sms.SendSms;
import com.oay.utils.RedisUtil;
import com.oay.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*********************************************************
 * @Package: com.oay.controller
 * @ClassName: SmsApiController.java
 * @Description： 短信接口
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2021-01-04
 *********************************************************/
@RestController
//  跨域支持
@CrossOrigin
public class SmsApiController {

    @Autowired
    private SendSms sendSms;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/send/{phone}")
    public Result code(@PathVariable("phone") String phone) {
        // 调用发送方法（模拟真实业务redis）
        String code = String.valueOf(redisUtil.get(phone));
        //  验证是否已过期
        if (!StringUtils.isEmpty(code)) {
            return Result.succ(phone + ":" + code + "已存在，还没过期");
        }
        //  调用短信服务
        boolean flag = sendSms.send(phone);
        if (flag) {
            redisUtil.set(phone, code, 5);
            return Result.succ(phone + ":" + code + "发送成功");
        } else {
            return Result.succ("发送失败");
        }
    }
}
