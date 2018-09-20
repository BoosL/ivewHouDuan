package com.bolaa.manage.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bolaa.manage.entity.Lydlink;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

public interface LydlinkService extends BaseService<Lydlink>{
	/**
	 * 落地页连接 锁定
	 * @param lydlinkId
	 * @return
	 */
	BaseResult lockLink(int lydlinkId);

	/**
	 * 落地页连接 解锁
	 * @param lydlinkId
	 * @return
	 */
	BaseResult unlockLink(int lydlinkId);

	/**
	 * 检查自定义代码是否存在
	 * @param code
	 * @return
	 */
	BaseResult isExistCode(String code);
	
	
	/**
	 * 根据落地页id 查询连接对象
	 * @param luodiyeid
	 * @return
	 */
	BaseObjectResult<Lydlink> getInfoByLuodiyeId(int luodiyeid);

	
	/**
	 * 落地页连接发布
	 * @param link
	 * @param req
	 * @return
	 */
	BaseObjectResult<Map<String, Object>> issueLink(Lydlink link, HttpServletRequest req);
	
}
