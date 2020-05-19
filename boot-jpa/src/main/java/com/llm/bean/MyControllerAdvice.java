package com.llm.bean;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luolm
 * @description: exception统一处理类，跟catch 类似，首先匹配精确的exception
 * @createTime： 2020/5/19 2:25 下午
 */
@ControllerAdvice
public class MyControllerAdvice {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map exceptionHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;

    }

    /**
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Map exceptionHandler(RuntimeException ex) {
        Map map = new HashMap();
        map.put("code", 101);
        map.put("msg", ex.getMessage());
        return map;

    }
}
