package com.meizhi.config;


import cn.hutool.json.JSONUtil;
import com.google.common.collect.ImmutableMap;
import com.meizhi.common.code.CommonCode;
import com.meizhi.common.code.ResultCode;
import com.meizhi.common.exception.CustomException;
import com.meizhi.common.response.ResponseResult;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {


    //定义map的builder对象，去构建ImmutableMap
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();
    //定义map，配置异常类型所对应的错误代码
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;

    //定义异常类型所对应的错误代码
    static {
        builder.put(MyBatisSystemException.class, CommonCode.SQL_ERROR); // dao 层异常
        builder.put(ClassCastException.class, CommonCode.RESPONSE_CAST_FAIL); //参数转换异常
    }

    /**
     * 中捕获参数校验异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseResult NotValidException(HttpServletRequest req, BindException e) throws Exception {
        BindingResult bindingResult = e.getBindingResult();
        //在这里就能获取所有的校验失败的信息
        List<ObjectError> allErrors = bindingResult.getAllErrors();


        List<String> errMsg = new ArrayList<>();
        for (ObjectError allError : allErrors) {
            String defaultMessage = allError.getDefaultMessage();
            String s = JSONUtil.toJsonStr(allError);
            if (s.contains("Failed to convert property")) {
                defaultMessage = "参数类型不匹配";
            }
            errMsg.add(defaultMessage);
        }
        ResponseResult result = new ResponseResult(CommonCode.PARAMS_VERIFY_FAIL, errMsg);
        return result;
    }

    //捕获CustomException此类异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException customException) {
        //记录日志
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }

    //捕获Exception此类异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception exception) {

        System.err.println("出现了未知异常:" + exception);
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
        if (resultCode != null) {
            return new ResponseResult(resultCode);
        } else {
            //所有非自定义,也未提前预知的异常统一返回99999异常
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }


    }

}
