package com.bolaa.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bolaa.common.BeanUtil;
import com.bolaa.common.FileHandleUtil;
import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.entity.Adproject;
import com.bolaa.manage.entity.Ldyitems;
import com.bolaa.manage.entity.Userlog;
import com.bolaa.manage.model.req.ReqLdyitemInfo;
import com.bolaa.manage.service.AdprojectService;
import com.bolaa.manage.service.LdyitemsService;
import com.bolaa.manage.service.UserlogService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 落地页明细 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/ldyitems")
@Api(value="落地页明细",description = "落地页明细接口")
public class LdyitemsController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	LdyitemsService ldyitemsService;
	
	@Autowired
	AdprojectService adprojectService;
	
	@Autowired
	UserlogService userLogService;

	/**
	 * 添加落地页明细
	 * 
	 * @param ldyitems
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "添加落地页明细" ,notes = "", httpMethod = "POST") 
	public BaseObjectResult<Map<String, Object>> addLdyitems(@RequestBody Ldyitems ldyitems, HttpServletRequest request) {
		logger.info("添加落地页明细");
		BaseObjectResult<Map<String, Object>> result = new BaseObjectResult<Map<String, Object>>("1", "添加成功");
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Adadm user = (Adadm) request.getSession().getAttribute("admin");
			if(user == null){
				result.setE("0");
				result.setErrorMessage("请先登录");
				return result;
			}
			Serializable id = ldyitemsService.save(ldyitems);
			
			Userlog log = new Userlog();
			log.setLogtime(new Date());
			log.setOptcode(ldyitems.toString());
			log.setOptname("添加落地页明细");
			log.setUserid(user.getUserid());
			userLogService.save(log);

			resMap.put("itemId", id.toString());
			result.setResult(resMap);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("添加落地页明细失败");
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 修改落地页明细 有关上传文件的
	 * 
	 * @param ldyitems
	 * @return
	 */
	@RequestMapping(value="/ldyitemUpdate",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "上传图片和视频并修改落地页明细" ,notes = "", httpMethod = "POST") 
	public BaseResult imgOrmp4Update(ReqLdyitemInfo ldyitem, MultipartFile[] files, HttpServletRequest request) {
		logger.info("修改落地页明细");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			Ldyitems item = ldyitemsService.get(ldyitem.getLdyitemid());
			if(files.length > 0){
				String picUrl = FileHandleUtil.upload(files, request, ldyitem.getLdyitemid().toString());
				if("图片".equals(item.getItemclass())){
					item.setPicurl(picUrl);
					Adproject project = adprojectService.get(ldyitem.getPid());
					if(!StringUtils.hasText(project.getPicurl())){
						 //String projectPic = FileHandleUtil.ProjectPic(files[0], request);
						if("图片".equals(item.getItemclass())){ // 图片作为项目封面图
							project.setPicurl(picUrl);
							adprojectService.update(project);
						}
					}
				}
				if("轮播图".equals(item.getItemclass())){
					String picurls = item.getPicurls();
					String newUrl = "";
					if(StringUtils.hasText(picurls)){
						newUrl = picurls+","+picUrl;
					}else{
						newUrl = picUrl;
					}
					item.setPicurls(newUrl);
				}
				if("视频".equals(item.getItemclass())){
					item.setVideourl(picUrl);
				}
			}
			BeanUtil.copyNotNullProperties(ldyitem, item);
			ldyitemsService.update(item);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改落地页明细 
	 * 
	 * @param ldyitems
	 * @return
	 */
	@RequestMapping(value="/itemUpdate",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "修改落地页明细" ,notes = "", httpMethod = "POST") 
	public BaseResult itemUpdate(@RequestBody ReqLdyitemInfo ldyitem,HttpServletRequest request) {
		logger.info("修改落地页明细");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			Ldyitems item = ldyitemsService.get(ldyitem.getLdyitemid());
			BeanUtil.copyNotNullProperties(ldyitem, item);
			ldyitemsService.update(item);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * 修改落地页明细
	 * 
	 * @param ldyitems
	 * @return
	 */
	@RequestMapping(value="/update",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "修改落地页明细" ,notes = "", httpMethod = "POST") 
	public BaseResult updateLdyitems(Ldyitems ldyitems) {
		logger.info("修改落地页明细");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			ldyitemsService.update(ldyitems);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除落地页明细
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "删除落地页明细" ,notes = "", httpMethod = "POST") 
	public BaseResult deleteLdyitems(int[] ids) {
		logger.info("删除落地页明细");
		BaseResult result = new BaseResult("1", "删除成功");
		for (int id : ids) {
			try {
				ldyitemsService.delete(id);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("删除失败");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id 查询落地页明细
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "根据id查询落地页明细" ,notes = "", httpMethod = "POST") 
	public BaseObjectResult<Ldyitems> getLdyitems(int id) {
		logger.info("根据id查询落地页明细");
		BaseObjectResult<Ldyitems> result = new BaseObjectResult<Ldyitems>("1", "根据id查询落地页明细成功");
		try {
			Ldyitems ldyitems = ldyitemsService.get(id);
			result.setResult(ldyitems);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询落地页明细失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询落地页明细集合 分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查询落地页明细集合" ,notes = "", httpMethod = "POST") 
	public BaseListResult<Ldyitems> listLdyitems(int page, int rows) {
		logger.info("查询落地页明细集合");
		BaseListResult<Ldyitems> result = new BaseListResult<Ldyitems>();
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			result = ldyitemsService.findListByPage(params, page, rows);
			result.setE("1");
			result.setErrorMessage("查询落地页明细集合成功");
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询落地页明细集合失败");
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping(value = "/listLdyitemsByLuodiyeId",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "根据落地页id查询落地页明细redis集合" ,notes = "", httpMethod = "POST") 
	//Integer LuodiyeId
	public BaseListResult<Map<String, Object>> listLdyitemsByLuodiyeId(@RequestBody Map<String,Object> map){
		logger.info("根据落地页id查询落地页明细redis集合");
		BaseListResult<Map<String,Object>> result = ldyitemsService.listLdyitemsByLuodiyeId((Integer) map.get("luodiyeId"));
		return result;
	}
	

}
