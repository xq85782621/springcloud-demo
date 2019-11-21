package com.meizhi.client;

import com.meizhi.common.code.CommonCode;
import com.meizhi.common.response.ResponseResult;
import org.springframework.stereotype.Component;

@Component
public class OrderClientFallBack implements  OrderClient {

    /**
     * 当user服务调用order服务的这个接口的时候,如果超时则会触发该方法快速失败
     */
    @Override
    public ResponseResult orderListByUserId(Integer userId ,Integer time) {
        System.err.println("发生了失败回调.");
        return new ResponseResult(CommonCode.ORDER_SERVER_FAIL);
    }
}
