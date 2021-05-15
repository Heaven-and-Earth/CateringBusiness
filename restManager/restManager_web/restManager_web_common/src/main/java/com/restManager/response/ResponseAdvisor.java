package com.restManager.response;

import com.restManager.response.exception.ExceptionResponse;
import com.restManager.response.vo.PageVO;
import com.restManager.utils.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName ResponseAdvisor
 * @Description TODO
 * @date 2021/5/15 21:24
 * @Version 1.0
 */
//声明该类为一个增强类 并且数据会以JSON形式返回
@RestControllerAdvice(basePackages = "com.restManager")
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {

    //判断哪些需求要被拦截  return true表示拦截所有
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof Result){
            return body;
        }

        if (body instanceof Boolean){
            boolean result = (boolean) body;
            return new BaseResponse<Boolean>(result);
        }

        if (body instanceof PageVO){
            return new BaseResponse<>(body);
        }

        if (body instanceof ExceptionResponse){
            return new BaseResponse<>(400,((ExceptionResponse)body).getMsg());
        }

        return new BaseResponse<>(body);
    }
}
