package com.bolaa.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bolaa.manage.entity.Userlog;
import com.bolaa.manage.service.UserlogService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 管理员日志 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/userlog")
@Api(value="后台用户日志",description = "后台用户日志接口")
public class UserlogController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	UserlogService userlogService;

	/**
	 * 添加管理员日志
	 * 
	 * @param userlog
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "添加管理员日志" ,notes = "", httpMethod = "POST")
	public BaseResult addUserlog(Userlog userlog) {
		logger.info("添加管理员日志");
		BaseResult result = new BaseResult("1", "添加成功");
		try {
			userlogService.save(userlog);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("添加成功");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改管理员日志
	 * 
	 * @param userlog
	 * @return
	 */
	@RequestMapping(value="/update",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "修改管理员日志" ,notes = "", httpMethod = "POST")
	public BaseResult updateUserlog(Userlog userlog) {
		logger.info("修改管理员日志");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			userlogService.update(userlog);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除管理员日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "删除管理员日志" ,notes = "", httpMethod = "POST")
	public BaseResult deleteUserlog(int[] ids) {
		logger.info("删除管理员日志");
		BaseResult result = new BaseResult("1", "删除成功");
		for (int id : ids) {
			try {
				userlogService.delete(id);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("删除失败");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id 查询管理员日志
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "根据id查询管理员日志" ,notes = "", httpMethod = "POST")
	public BaseObjectResult<Userlog> getUserlog(int id) {
		logger.info("根据id查询管理员日志");
		BaseObjectResult<Userlog> result = new BaseObjectResult<Userlog>("1", "根据id查询管理员日志成功");
		try {
			Userlog userlog = userlogService.get(id);
			result.setResult(userlog);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询管理员日志失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询管理员日志集合 分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查询管理员日志集合" ,notes = "", httpMethod = "POST")
	public BaseListResult<Userlog> listUserlog(int page, int rows) {
		logger.info("查询管理员日志集合");
		BaseListResult<Userlog> result = new BaseListResult<Userlog>();
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			result = userlogService.findListByPage(params, page, rows);
			result.setE("1");
			result.setErrorMessage("查询管理员日志集合成功");
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询管理员日志集合失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询管理员日志集合 分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/logList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查询管理员日志集合" ,notes = "", httpMethod = "POST")
	//int page, int rows
	public BaseListResult<Map<String,Object>> logList(@RequestBody Map<String, Object> map) {
		logger.info("查询管理员日志集合");
		BaseListResult<Map<String,Object>> result = userlogService.logList((int)map.get("page"), (int)map.get("rows"));
		return result;
	}
	

}
