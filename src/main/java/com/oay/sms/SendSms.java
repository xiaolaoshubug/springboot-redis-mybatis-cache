package com.oay.sms;

/*********************************************************
 * @Package: com.oay.sms
 * @ClassName: SendSms.java
 * @Description： 短信接口
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2021-01-04
 *********************************************************/
public interface SendSms {
    /**
     * @param iphone 手机号
     * @param templateCode  短信模板
     * @return 返回信息
     */
    boolean send(String iphone,String templateCode);
}
