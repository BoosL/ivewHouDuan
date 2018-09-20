package com.bolaa.manage.service;

import javax.servlet.http.HttpServletRequest;

import com.bolaa.manage.entity.Adadm;
import com.bolaa.result.BaseResult;

public interface AdadmService extends BaseService<Adadm>{

	/**
	 * 用户登录
	 * @param request 
	 * @param user
	 * @return
	 */
	BaseResult userLogin(HttpServletRequest request, Adadm user);
	
	
}
