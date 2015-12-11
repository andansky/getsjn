package com.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* 类名: CommonInterceptor </br>
* 包名： com.springmvc.interceptor
* 描述: 登录拦截器  </br>
* 创建时间：  2015-12-11 </br>
* 发布版本：V1.0  </br>
 */
public class CommonInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object obj) throws Exception {
        String str = (String) request.getSession().getAttribute("admin");
        if(str==null){
            request.getRequestDispatcher("/WEB-INF/pages/admin/login.jsp").forward(request, response);
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception err)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object obj, ModelAndView mav) throws Exception {
    }
}
