package com.bolaa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bolaa.manage.entity.Luodiye;
import com.bolaa.manage.model.req.ReqLuodiyeListMod;
import com.bolaa.manage.service.LuodiyeService;
import com.bolaa.manage.service.impl.RedisServiceImpl;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 落地页 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/luodiye")
@Api(value = "落地页", description = "落地页接口")
public class LuodiyeController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	LuodiyeService luodiyeService;
	
	@Autowired
	RedisServiceImpl redisService;

	/**
	 * 添加落地页
	 * 
	 * @param luodiye
	 * @return
	 */
	@ApiOperation(value = "添加落地页" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public BaseResult addLuodiye(Luodiye luodiye) {
		logger.info("添加落地页");
		BaseResult result = new BaseResult("1", "添加成功");
		try {
			luodiyeService.save(luodiye);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("添加成功");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改落地页
	 * 
	 * @param luodiye
	 * @return
	 */
	@RequestMapping(value="/update",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "修改落地页" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	@ResponseBody
	public BaseResult updateLuodiye(Luodiye luodiye) {
		logger.info("修改落地页");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			luodiyeService.update(luodiye);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除落地页
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "删除落地页" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	@ResponseBody
	public BaseResult deleteLuodiye(int[] ids) {
		logger.info("删除落地页");
		BaseResult result = new BaseResult("1", "删除成功");
		for (int id : ids) {
			try {
				luodiyeService.delete(id);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("删除失败");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id 查询落地页
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "根据id查询落地页" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	@ResponseBody
	public BaseObjectResult<Luodiye> getLuodiye(int id) {
		logger.info("根据id查询落地页");
		BaseObjectResult<Luodiye> result = new BaseObjectResult<Luodiye>("1", "根据id查询落地页成功");
		try {
			Luodiye luodiye = luodiyeService.get(id);
			result.setResult(luodiye);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询落地页失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询落地页集合 分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "查询落地页集合" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	@ResponseBody
	public BaseListResult<Luodiye> listLuodiye() {
		logger.info("查询落地页集合");
		BaseListResult<Luodiye> result = new BaseListResult<Luodiye>();
		try {
			
			String hql = " from Luodiye";
			List<Luodiye> list = luodiyeService.find(hql);
			result.setE("1");
			result.setResult(list);
			result.setErrorMessage("查询落地页集合成功");
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询落地页集合失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据项目id查询 落地页集合 分页
	 * @param projectId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/luodiyeListByProjectId",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "根据项目id查询 落地页集合 分页" ,notes = "", httpMethod = "POST") 
	@ResponseBody
	//Integer pid, int page, int rows
	public BaseListResult<Map<String, Object>> luodiyeListByProjectId(@RequestBody Map<String, Object> map){
		BaseListResult<Map<String, Object>> result = luodiyeService.luodiyeListByProjectId(Integer.parseInt((String)map.get("pid")), (int)map.get("page"), (int)map.get("rows"));
		return result;
	}
	
	/**
	 * 根据项目id查询 所有落地页
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value="/luodiyeAllByProjectId",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "根据项目id查询 所有落地页" ,notes = "", httpMethod = "POST") 
	@ResponseBody
	public BaseListResult<Luodiye> luodiyeAllByProjectId(Integer projectId){
		BaseListResult<Luodiye> result = new BaseListResult<Luodiye>("1", "根据项目id查询 所有落地页成功");
		String hql = "from Luodiye where pid = :projectId";
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("projectId", projectId);
			List<Luodiye> list = luodiyeService.find(hql, params );
			result.setResult(list);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据项目id查询 所有落地页失败");
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 根据条件查询落地页集合
	 * @return
	 */
	@RequestMapping(value="/luodiyeList",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "根据条件查询落地页集合" ,notes = "", httpMethod = "POST")
	@ResponseBody
	public BaseListResult<Map<String, Object>> luodiyeList(@RequestBody ReqLuodiyeListMod reqMod) {
		BaseListResult<Map<String,Object>> result = luodiyeService.luodiyelist(reqMod);
		return result;
	}
	
	
	/**
	 * 添加落地页
	 * @return
	 */
	/*@RequestMapping(value="/addLuodiye",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "添加落地页" ,notes = "", httpMethod = "POST")
	@ResponseBody
	public BaseResult addLuodiye(@RequestBody ReqAddLuodiyeMod reqMod, HttpServletRequest request) {
		BaseResult result = luodiyeService.addLuodiye(reqMod, request);
		return result;
	}*/
	
	/**
	 * 添加落地页
	 * @return
	 */
	@RequestMapping(value="/addLuodiye",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "添加落地页" ,notes = "", httpMethod = "POST")
	@ResponseBody
	public BaseObjectResult<Map<String, Object>> addLuodiye(@RequestBody Map<String, Object> map, HttpServletRequest req) {
		
		Luodiye luodiye = new Luodiye();
		luodiye.setPid((int)map.get("pid"));
		luodiye.setLname(map.get("lname").toString());
		luodiye.setAddtime(new Date());
		BaseObjectResult<Map<String, Object>> result = luodiyeService.addluodiyeItems(req,luodiye, map.get("items").toString());
		
		return result;
	}
	
	/**
	 * 根据落地页id查询落地页的模块信息
	 * @return
	 */
	@RequestMapping(value="/luodiyeInfoById",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "根据落地页id查询落地页模块信息" ,notes = "", httpMethod = "POST")
	@ResponseBody
	public BaseListResult<Map<String,Object>> luodiyeInfo(@RequestBody Map<String, Object> map, HttpServletRequest req) {
		
		logger.info("根据落地页id查询落地页模块信息");
		BaseListResult<Map<String,Object>> result = luodiyeService.luodiyeInfo((Integer) map.get("luodiyeId"));
		return result;
		
	}
	
	/**
	 * 落地页redis数据
	 * @param luodiyeId
	 * @return
	 */
	@RequestMapping(value="/luodiyeRedisInfo",method={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "添加落地页" ,notes = "", httpMethod = "POST")
	@ResponseBody
	public BaseObjectResult<Map<String, Object>> luodiyeRedisInfo(@RequestBody Map<String, Object> map) {
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String,Object>>("1", "获取落地页redis数据成功");
		Map<String, Object> redisMap = redisService.getClickTime(map.get("luodiyeId").toString());
		result.setResult(redisMap);
		return result;
	}
}
