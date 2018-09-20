package com.bolaa.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.entity.Adclass;
import com.bolaa.manage.entity.Userlog;
import com.bolaa.manage.service.AdclassService;
import com.bolaa.manage.service.UserlogService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 项目分类 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/adclass")
@Api(value = "项目分类", description = "项目分类")
public class AdclassController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	AdclassService adclassService;
	
	@Autowired
	UserlogService userLogService;

	/**
	 * 添加项目分类
	 * 
	 * @param adclass
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "添加项目分类" ,notes = "", httpMethod = "POST")
	public BaseResult addAdclass(@RequestBody Adclass adclass, HttpServletRequest request) {
		logger.info("添加项目分类");
		BaseResult result = new BaseResult("1", "添加成功");
		try {
			Adadm user = (Adadm) request.getSession().getAttribute("admin");
			if(user == null){
				result.setE("0");
				result.setErrorMessage("请先登录");
				return result;
			}
			adclassService.save(adclass);

			Userlog log = new Userlog();
			log.setLogtime(new Date());
			log.setOptcode(adclass.toString());
			log.setOptname("添加项目分类");
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
	 * 修改项目分类
	 * 
	 * @param adclass
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "修改项目分类" ,notes = "", httpMethod = "POST")
	public BaseResult updateAdclass(Adclass adclass, HttpServletRequest request) {
		logger.info("修改项目分类");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			Adadm user = (Adadm) request.getSession().getAttribute("admin");
			if(user == null){
				result.setE("0");
				result.setErrorMessage("请先登录");
				return result;
			}
			adclassService.update(adclass);

			Userlog log = new Userlog();
			log.setLogtime(new Date());
			log.setOptcode(adclass.toString());
			log.setOptname("修改项目分类");
			log.setUserid(user.getUserid());
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除项目分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "删除项目分类" ,notes = "", httpMethod = "POST")
	public BaseResult deleteAdclass(int[] ids) {
		logger.info("删除项目分类");
		BaseResult result = new BaseResult("1", "删除成功");
		for (int id : ids) {
			try {
				adclassService.delete(id);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("删除失败");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id 查询项目分类
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "根据id查询项目分类" ,notes = "", httpMethod = "POST")
	public BaseObjectResult<Adclass> getAdclass(int id) {
		logger.info("根据id查询项目分类");
		BaseObjectResult<Adclass> result = new BaseObjectResult<Adclass>("1", "根据id查询项目分类成功");
		try {
			Adclass adclass = adclassService.get(id);
			result.setResult(adclass);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询项目分类失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询项目所有分类集合
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "查询项目所有分类集合" ,notes = "", httpMethod = "POST")
	public BaseListResult<Adclass> listAdclass() {
		logger.info("查询项目所有分类集合");
		BaseListResult<Adclass> result = new BaseListResult<Adclass>("1", "查询所有项目分类集合成功");
		try {
			String hql = "from Adclass";
			List<Adclass> list = adclassService.find(hql);
			result.setResult(list);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询项目分类集合失败");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/testHtml", method = { RequestMethod.GET, RequestMethod.POST })
	public String testHtml(HttpServletRequest req) throws Exception{
		String hql = "from Adclass";
		List<Adclass> list = adclassService.find(hql);
		req.setAttribute("listClass", list);
		return "test";
	}

}
