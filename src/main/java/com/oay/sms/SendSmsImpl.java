package com.oay.sms;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

/*********************************************************
 * @Package: com.oay.sms
 * @ClassName: SendSms.java
 * @Description： 短信接口实现类
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2021-01-04
 *********************************************************/
@Service
public class SendSmsImpl implements SendSms {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public boolean send(String iphone, String templateCode) {
        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "用户的AccessKey ID", "密码");
        IAcsClient client = new DefaultAcsClient(profile);
        //  构建请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        //  自定义模板--> 参数在阿里云面板短信服务获取
        request.putQueryParameter("PhoneNumbers", iphone);                //  手机号
        request.putQueryParameter("SignName", "签名名称");           //   签名名称
        request.putQueryParameter("TemplateCode", templateCode);          //   模板的code
        //  构建一个测试短信
        HashMap<String, String> map = new HashMap<>();
        //  生成四位随机验证码
        map.put("code", UUID.randomUUID().toString().substring(0, 4));
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("<<<<<=====response=====>>>>>" + response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
