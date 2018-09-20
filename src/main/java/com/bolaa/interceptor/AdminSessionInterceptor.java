package com.bolaa.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.bolaa.result.BaseObjectResult;

public class AdminSessionInterceptor extends HandlerInterceptorAdapter {
    protected Logger logger = Logger.getLogger(getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
	Object admin = request.getSession().getAttribute("admin");
	if (admin == null ) {
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json; charset=utf-8");
	    PrintWriter out = null;
	    try {
		BaseObjectResult<String> map = new BaseObjectResult<String>();
		map.setE("-1");
		map.setErrorMessage("未登录");
		out = response.getWriter();
		out.append(JSONObject.toJSONString(map));
		return false;
	    } catch (Exception e) {
		
		response.sendError(500);
		return false;
	    }
	}
	return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception {
    }
}
