package com.bolaa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.entity.Adrole;
import com.bolaa.manage.service.AdroleService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/adrole")
@Api(value="后台角色权限",description = "后台角色权限接口")
public class AdroleController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	AdroleService adroleService;

	/**
	 * 添加后台角色权限
	 * 
	 * @param adrole
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "保存后台角色信息" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseResult addAdrole(Adrole adrole) {
		logger.info("添加后台角色");
		BaseResult result = new BaseResult("1", "添加成功");
		try {
			adroleService.save(adrole);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("添加成功");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改后台角色
	 * 
	 * @param adrole
	 * @return
	 */
	@RequestMapping(value="/update",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "修改后台角色信息" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseResult updateAdrole(Adrole adrole) {
		logger.info("修改后台角色");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			adroleService.update(adrole);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除后台角色
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "删除后台角色" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseResult deleteAdrole(int[] ids) {
		logger.info("删除后台角色");
		BaseResult result = new BaseResult("1", "删除成功");
		for (int id : ids) {
			try {
				adroleService.delete(id);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("删除失败");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id 查询后台角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "根据id查询后台角色信息" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseObjectResult<Adrole> getAdrole( Integer id) {
		logger.info("根据id查询后台角色");
		BaseObjectResult<Adrole> result = new BaseObjectResult<Adrole>("1", "根据id查询后台角色成功");
		try {
			//int id = (int) map.get("id");
			Adrole adrole = adroleService.get(id);
			result.setResult(adrole);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询后台角色失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询后台角色集合 分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查询后台角色集合" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseListResult<Adrole> listAdrole() {
		logger.info("查询后台角色集合");
		BaseListResult<Adrole> result = new BaseListResult<Adrole>();
		try {
			List<Adrole> list = adroleService.find(" from Adrole ");
			result.setResult(list);
			result.setE("1");
			result.setErrorMessage("查询后台角色成功");
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询后台角色集合失败");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/rolePower",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查询后台角色权限" ,notes = "", httpMethod = "POST") // swagger2 只出现 post 请求方式
	public BaseResult rolePower(HttpServletRequest request) {
		BaseResult result = new BaseResult("1", "该用户具有权限");
		HttpSession session = request.getSession();
		Adadm adadm = (Adadm) session.getAttribute("admin");
		if(null == adadm){
			result.setE("0");
			result.setErrorMessage("请先登录用户");
		}
		return result;
	}

}
