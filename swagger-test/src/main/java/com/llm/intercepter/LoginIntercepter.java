package com.llm.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/12/21
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
@Component
public class LoginIntercepter implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token=httpServletRequest.getHeader("token");
        if (!(o instanceof HandlerMethod)){
            return true;
        }
        if (StringUtils.isEmpty(token)){
            httpServletResponse.setCharacterEncoding("utf-8");
            PrintWriter writer = httpServletResponse.getWriter();
            writer.print("error");
            writer.flush();

            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
