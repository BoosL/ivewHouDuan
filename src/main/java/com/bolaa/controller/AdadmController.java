package com.bolaa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bolaa.common.MapToBeanUtil;
import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.entity.Userlog;
import com.bolaa.manage.service.AdadmService;
import com.bolaa.manage.service.UserlogService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 管理员 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/adadm")
@Api(value="后台用户",description = "后台用户接口")
public class AdadmController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	AdadmService adadmService;
	
	@Autowired
	UserlogService userLogService;
	/**
	 * 添加用户
	 * 
	 * @param adadm
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "保存用户信息" ,notes = "", httpMethod = "POST")
	public BaseResult addAdadm(@RequestBody Adadm adadm, HttpServletRequest request) {
		logger.info("添加用户");
		BaseResult result = new BaseResult("1", "添加成功");
		try {
			Adadm user = (Adadm) request.getSession().getAttribute("admin");
			if(user == null){
				result.setE("0");
				result.setErrorMessage("请先登录用户");
				return result;
			}
			adadmService.save(adadm);
			Userlog log = new Userlog();
			log.setLogtime(new Date());
			log.setOptcode(adadm.toString());
			log.setOptname("添加用户");
			log.setUserid(user.getUserid());
			userLogService.save(log);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("添加成功");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改用户
	 * 
	 * @param adadm
	 * @return
	 */
	@RequestMapping(value="/update",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "修改用户信息" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseResult updateAdadm(@RequestBody Adadm adadm) {
		logger.info("修改用户");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			adadmService.update(adadm);
			
			Userlog log = new Userlog();
			log.setLogtime(new Date());
			log.setOptcode(adadm.toString());
			log.setOptname("修改用户");
			userLogService.save(log);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "删除用户" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseResult deleteAdadm(int[] ids) {
		logger.info("删除用户");
		BaseResult result = new BaseResult("1", "删除成功");
		for (int id : ids) {
			try {
				adadmService.delete(id);
				
				Userlog log = new Userlog();
				log.setLogtime(new Date());
				log.setOptcode(ids.toString());
				log.setOptname("删除用户");
				userLogService.save(log);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("删除失败");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id 查询用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "根据id查询用户信息" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseObjectResult<Adadm> getAdadm(@RequestBody Map<String, Object> map) {
		logger.info("根据id查询用户");
		BaseObjectResult<Adadm> result = new BaseObjectResult<Adadm>("1", "根据id查询用户成功");
		try {
			//int id = (int) map.get("id");
			Adadm adadm = adadmService.get((int) map.get("id"));
			result.setResult(adadm);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询用户失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询用户集合 分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查询用户集合" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseListResult<Adadm> listAdadm(@RequestBody Map<String, Object> map) {
		//int page, int rows
		logger.info("查询用户集合");
		BaseListResult<Adadm> result = new BaseListResult<Adadm>();
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			result = adadmService.findListByPage(params, (int)map.get("page"), (int)map.get("rows"));
			result.setE("1");
			result.setErrorMessage("查询用户集合成功");
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询用户集合失败");
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * 查询所有用户
	 *
	 * @return
	 */
	@RequestMapping(value = "/AllList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "查询所有用户", notes = "", httpMethod = "POST")
	public BaseListResult<Adadm> AllList() {
		logger.info("查询所有用户");
		BaseListResult<Adadm> result = new BaseListResult<Adadm>("1", "查询所有用户成功");
		try {
			String hql = "from Adadm";
			List<Adadm> list = adadmService.find(hql);
			result.setResult(list);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询所有用户失败");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * 获取session对象
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getSessionObj",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "获取session对象" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseObjectResult<Map<String, Object>> getSessionObj(HttpServletRequest req) {
		logger.info("获取session对象");
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String, Object>>("1", "获取session对象成功");
		try {
			Adadm adadm = (Adadm) req.getSession().getAttribute("admin");
			if(adadm == null){
				result.setE("0");
				result.setErrorMessage("获取的对象为空");
				return result;
			}
			Map<String, Object> map = MapToBeanUtil.ConvertObjToMap(adadm);
			if((int)map.get("roleid") == 1){
				map.put("roleName", "超级管理");
			}else{
				map.put("roleName", "运营人员");
			}
			result.setResult(map);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("获取session对象失败");
			e.printStackTrace();
		}
		return result;
	}
	

}
