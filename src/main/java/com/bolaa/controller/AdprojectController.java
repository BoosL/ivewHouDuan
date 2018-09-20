package com.bolaa.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.entity.Adproject;
import com.bolaa.manage.entity.Userlog;
import com.bolaa.manage.model.req.ReqProjectListMod;
import com.bolaa.manage.model.req.ReqProjectReidsMod;
import com.bolaa.manage.service.AdprojectService;
import com.bolaa.manage.service.LdysubmitService;
import com.bolaa.manage.service.UserlogService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 项目 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/adproject")
@Api(value = "项目", description = "项目接口")
public class AdprojectController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	AdprojectService adprojectService;
	@Autowired
	LdysubmitService ldysubmitService;
	@Autowired
	UserlogService userlogService;
	
	/**
	 * 添加项目
	 * 
	 * @param adproject
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "添加项目", notes = "", httpMethod = "POST")
	public BaseObjectResult<Map<String, Object>> addAdproject(@RequestBody Adproject adproject, HttpServletRequest request) {
		logger.info("添加项目");
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String, Object>>("1", "添加成功");
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Adadm user = (Adadm) request.getSession().getAttribute("admin");
			if(user == null){
				result.setE("0");
				result.setErrorMessage("请先登录");
				return result;
			}
			adproject.setAddtime(new Date());
			adproject.setPstatus("未完成");

			if(adproject.getPname()==null || adproject.getPname().equals("")){
				result.setE("0");
				result.setErrorMessage("项目名不能为空");
				return result;
			}

			boolean flg = adprojectService.checkOutPname(adproject);
			if(flg){
				result.setE("0");
				result.setErrorMessage("该项目名称已经存在");
				return result;
			}

			if(adproject.getBegintime()==null || adproject.getEndtime()==null){
				result.setE("0");
				result.setErrorMessage("项目开始时间或结束时间不能为空");
				return result;
			}
			if(adproject.getClassid()==null || adproject.getClassid()==-1){
				result.setE("0");
				result.setErrorMessage("项目类型不能为空");
				return result;
			}

			Serializable pid = adprojectService.save(adproject);
			resMap.put("pid", pid);
			result.setResult(resMap);
			// 项目日志
			Userlog log = new Userlog();
			log.setLogtime(new Date());
			log.setOptcode(adproject.toString());
			log.setOptname("添加项目");
			log.setUserid(user.getUserid());
			log.setPid(Integer.parseInt(pid.toString()));
			userlogService.save(log);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("添加失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改项目
	 * 
	 * @param adproject
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "修改项目", notes = "", httpMethod = "POST")
	public BaseResult updateAdproject(@RequestBody Adproject adproject,HttpServletRequest request) {
		logger.info("修改项目");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			Adadm user = (Adadm) request.getSession().getAttribute("admin");
			if(user == null){
				result.setE("0");
				result.setErrorMessage("请先登录");
				return result;
			}
			adproject.setAddtime(new Date());
			adproject.setPstatus("未完成");

			if(adproject.getPname()==null || adproject.getPname().equals("")){
				result.setE("0");
				result.setErrorMessage("项目名不能为空");
				return result;
			}

			boolean flg = adprojectService.checkOutPname(adproject);
			if(flg){
				result.setE("0");
				result.setErrorMessage("该项目名称已经存在");
				return result;
			}

			if(adproject.getBegintime()==null || adproject.getEndtime()==null){
				result.setE("0");
				result.setErrorMessage("项目开始时间或结束时间不能为空");
				return result;
			}
			if(adproject.getClassid()==null || adproject.getClassid()==-1){
				result.setE("0");
				result.setErrorMessage("项目类型不能为空");
				return result;
			}
			adprojectService.update(adproject);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除项目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "删除项目", notes = "", httpMethod = "POST") 
	public BaseResult deleteAdproject(int[] ids) {
		logger.info("删除项目");
		BaseResult result = new BaseResult("1", "删除成功");
		for (int id : ids) {
			try {
				adprojectService.delete(id);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("删除失败");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id 查询项目
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "根据id查询项目", notes = "", httpMethod = "POST")
	//int id
	public BaseObjectResult<Adproject> getAdproject(@RequestBody Map<String, Object> map) {
		logger.info("根据id查询项目");
		BaseObjectResult<Adproject> result = new BaseObjectResult<Adproject>("1", "根据id查询项目成功");
		try {
			Adproject adproject = adprojectService.get((int)map.get("id"));
			result.setResult(adproject);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询项目失败");
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping(value = "/getInfoById", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "根据id查询项目信息", notes = "", httpMethod = "POST")
	//int id
	public BaseObjectResult<Map<String, Object>> getInfoById(@RequestBody Map<String, Object> map) {
		logger.info("根据id查询项目信息");
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String,Object>>("1", "根据id查询项目信息成功");
		try {
			result = adprojectService.getInfoById(Integer.parseInt( map.get("id").toString()));
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询项目信息失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询所有项目
	 *
	 * @return
	 */
	@RequestMapping(value = "/AllList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "查询所有项目", notes = "", httpMethod = "POST")
	//int id
	public BaseListResult<Adproject> AllList() {
		logger.info("查询所有项目");
		BaseListResult<Adproject> result = new BaseListResult<Adproject>("1", "查询所有项目成功");
		try {
			String hql = "from Adproject";
			List<Adproject> list = adprojectService.find(hql);
			result.setResult(list);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询所有项目失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询项目集合 分页
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/indexList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "查询首页项目集合", notes = "", httpMethod = "POST")
	//String pstatus,int page, int rows
	public BaseListResult<Map<String, Object>> indexList(@RequestBody Map<String, Object> map) {
		logger.info("查询首页项目集合");
		BaseListResult<Map<String, Object>> result = adprojectService.indexProject((String)map.get("pstatus"), (int)map.get("page"), (int)map.get("rows"));
		return result;
	}
	
	@RequestMapping(value="/getRedisByProjectId", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "根据项目id查询redis数据", notes = "", httpMethod = "POST")
	//int id
	public BaseObjectResult<Map<String, Object>> getRedisByProjectId(@RequestBody Map<String, Object> map){
		BaseObjectResult<Map<String,Object>> result = adprojectService.getRedisByProjectId(Integer.parseInt((String)map.get("pid")) );
		return result;
	}

	@RequestMapping(value = "/projectList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "根据条件查询项目集合", notes = "", httpMethod = "POST") 
	public BaseListResult<Map<String, Object>> projectList(@RequestBody ReqProjectListMod reqMod) {
		logger.info("根据条件查询项目集合");
		BaseListResult<Map<String, Object>> result = new BaseListResult<Map<String, Object>>();
		result = adprojectService.projectList(reqMod.getBeginTime(), reqMod.getEndTime(), reqMod.getClassId(),
				reqMod.getProjectName(), reqMod.getPstatus(), reqMod.getPage(), reqMod.getRows());
		List<Map<String,Object>> list = result.getResult();
		if(!list.isEmpty()){
			for (Map<String, Object> map : list) {
				Integer countSubmit = ldysubmitService.countSubmitByPid((Integer)map.get("pid"));
				map.put("countSubmit", countSubmit);
				map.put("cycleTime", map.get("begintime") +"___" + map.get("endtime"));
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/redisData", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "根据条件查询redis数据", notes = "", httpMethod = "POST") 
	public BaseObjectResult<Map<String, Object>> redisData(@RequestBody ReqProjectReidsMod reqMod) {
		logger.info("根据条件查询redis数据");
		BaseObjectResult<Map<String,Object>> result = adprojectService.redisData(reqMod);
		return result;
	}

	@RequestMapping(value = "/topCount", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "首页上边的计数", notes = "", httpMethod = "POST") 
	public BaseObjectResult<Map<String, Object>> topCount() {
		logger.info("首页右边表单计数");
		BaseObjectResult<Map<String, Object>> result = adprojectService.topCount();
		return result;
	}
	
	@RequestMapping(value = "/lockProject", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "锁定项目", notes = "", httpMethod = "POST") 
	//Integer pid
	public BaseResult lockProject(@RequestBody Map<String, Object> map, HttpServletRequest request){
		//@RequestBody Map<String, Object> map
		logger.info("锁定项目");
		//(Integer)map.get("id")
		Object object = request.getSession().getAttribute("admin");
		BaseResult result = adprojectService.lockProject((Integer)map.get("id"));
		return result;
	}

}
