package com.bolaa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.service.AdadmService;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value="/admin")
@Api(value = "后台用户登录", description = "后台用户登录接口")
public class UserLoginController {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AdadmService adadmService;
	
	@RequestMapping(value="/adminLogin",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "用户登录" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseResult adminLogin(HttpServletRequest request,@RequestBody Adadm user){
		logger.info("用户登录");
		BaseResult result = new BaseResult();
		result = adadmService.userLogin(request,user);
		return result;
	}
	
	@RequestMapping(value="/adminLoginOut",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "用户退出" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseResult adminLoginOut(HttpServletRequest request){
		logger.info("用户退出");
		BaseResult result = new BaseResult("1", "退出成功");
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		return result;
	}
	
	@RequestMapping(value="/getSessionadmin",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "获取session用户" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseObjectResult<Adadm> getSessionadmin(HttpServletRequest request){
		logger.info("获取session用户");
		BaseObjectResult<Adadm> result = new BaseObjectResult<Adadm>("1", "获取session用户成功");
		Adadm user = (Adadm) request.getSession().getAttribute("admin");
		result.setResult(user);
		if(user == null){
			result.setE("0");
			result.setErrorMessage("获取session用户失败");
		}
		return result;
	}
}
