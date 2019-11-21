package com.meizhi.client;

import com.meizhi.common.response.ResponseResult;
import com.meizhi.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-order",configuration = FeignClientConfig.class , fallback = OrderClientFallBack.class)
//@FeignClient(name = "service-order",configuration = FeignClientConfig.class )
public interface OrderClient {

    @GetMapping("/order/list/{userId}/sleep/{time}")
    ResponseResult orderListByUserId(@PathVariable("userId") Integer userId,@PathVariable("time") Integer time);
}
