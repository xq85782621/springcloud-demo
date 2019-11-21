package com.meizhi.fallback;


import cn.hutool.json.JSONUtil;
import com.meizhi.common.code.CommonCode;
import com.meizhi.common.response.ResponseResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * 比如说通过 zuul-> order   但是order服务被关停了或者长时间不响应, 那么zuul就要对order服务的这种异常情况进行fallback处理
 * 这种情况,相当于zuul是消费方, order是提供方, 消费方要对长时间不响应的 提供方 进行 fallback
 * 就像 user服务 通过 feign 访问 order服务 的时候定义好的 fallback
 */
@Component
public class ProviderFallback implements   FallbackProvider  {


    /**
     * 返回 应用名  则对某个服务进行fallback
     * 返回  "*"  则对所有的服务进行fallback
     */
    @Override
    public String getRoute() {
        return "service-order";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders() ;
                headers.set("Content-Type", "text/html; charset=UTF-8");
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                // 响应体
                ResponseResult result = new ResponseResult(CommonCode.APPLICATION_FAIL);
                String s = JSONUtil.toJsonStr(result);
                return new ByteArrayInputStream(s.getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.BAD_REQUEST;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.BAD_REQUEST.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.BAD_REQUEST.getReasonPhrase();
            }

            @Override
            public void close() {

            }
        };
    }
}
