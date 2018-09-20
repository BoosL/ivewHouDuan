package com.bolaa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bolaa.manage.entity.Ldysubmit;
import com.bolaa.manage.service.LdysubmitService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 落地页明细提交数据 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/ldysubmit")
@Api(value="落地页提交数据",description = "落地页提交数据接口")
public class LdysubmitController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	LdysubmitService ldysubmitService;

	/**
	 * 添加落地页明细提交数据
	 * 
	 * @param ldysubmit
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "添加落地页明细提交数据" ,notes = "", httpMethod = "POST")
	public BaseResult addLdysubmit(@RequestBody Map<String, Object> reqMap) {
		logger.info("添加落地页明细提交数据");
		BaseResult result = new BaseResult("1", "添加成功");
		Ldysubmit submit = new Ldysubmit();
		List<Map<String, Object>> listMap = (List<Map<String, Object>>) reqMap.get("formInfo");
		String substance = "";
		for (Map<String, Object> map : listMap) {
			substance += map.get("title")+":"+map.get("value")+",";
		}
		substance.substring(0, substance.length()-1);
		submit.setSubstance(substance);
		Map<String, Object> addrMap = (Map<String, Object>) reqMap.get("addrInfo");
		submit.setProvince(addrMap.get("province").toString());
		submit.setCity(reqMap.get("city").toString());
		submit.setDistrict(addrMap.get("district").toString());
		submit.setLdylinkid((int)reqMap.get("ldylinkid"));
		submit.setUniqueid(reqMap.get("uniqueid").toString());
		submit.setIp(reqMap.get("ip").toString());
		
//		System.out.println(map);
		try {
			ldysubmitService.save(submit);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("添加成功");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改落地页明细提交数据
	 * 
	 * @param ldysubmit
	 * @return
	 */
	@RequestMapping(value="/update",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "修改落地页明细提交数据" ,notes = "", httpMethod = "POST")
	public BaseResult updateLdysubmit(Ldysubmit ldysubmit) {
		logger.info("修改落地页明细提交数据");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			ldysubmitService.update(ldysubmit);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除落地页明细提交数据
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "删除落地页明细提交数据" ,notes = "", httpMethod = "POST")
	public BaseResult deleteLdysubmit(int[] ids) {
		logger.info("删除落地页明细提交数据");
		BaseResult result = new BaseResult("1", "删除成功");
		for (int id : ids) {
			try {
				ldysubmitService.delete(id);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("删除失败");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id 查询落地页明细提交数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "根据id查询落地页明细提交数据" ,notes = "", httpMethod = "POST")
	public BaseObjectResult<Ldysubmit> getLdysubmit(int id) {
		logger.info("根据id查询落地页明细提交数据");
		BaseObjectResult<Ldysubmit> result = new BaseObjectResult<Ldysubmit>("1", "根据id查询落地页明细提交数据成功");
		try {
			Ldysubmit ldysubmit = ldysubmitService.get(id);
			result.setResult(ldysubmit);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询落地页明细提交数据失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询落地页明细提交数据集合 分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查询落地页明细提交数据集合" ,notes = "", httpMethod = "POST")
	public BaseListResult<Ldysubmit> listLdysubmit(int page, int rows) {
		logger.info("查询落地页明细提交数据集合");
		BaseListResult<Ldysubmit> result = new BaseListResult<Ldysubmit>();
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			result = ldysubmitService.findListByPage(params, page, rows);
			result.setE("1");
			result.setErrorMessage("查询落地页明细提交数据集合成功");
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询落地页明细提交数据集合失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询已提交的表单集合 分页
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/overSubList",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查询已提交的表单集合 分页" ,notes = "", httpMethod = "POST")
	//int page, int rows
	public BaseObjectResult<Map<String, Object>> overSubList(@RequestBody Map<String, Object> map){
		logger.info("查询已提交的表单集合 分页");
		BaseObjectResult<Map<String,Object>> result = ldysubmitService.overSubList((int)map.get("ldylinkid"),(int)map.get("page"), (int)map.get("rows"));
		return result;
	}
	

}
