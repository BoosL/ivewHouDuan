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

import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.entity.Lydlink;
import com.bolaa.manage.entity.Userlog;
import com.bolaa.manage.service.LydlinkService;
import com.bolaa.manage.service.UserlogService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 落地页连接 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/lydlink")
@Api(value = "落地页连接", description = "落地页连接接口")
public class LydlinkController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	LydlinkService lydlinkService;

	@Autowired
	UserlogService userLogService;
	/**
	 * 添加落地页连接
	 * 
	 * @param lydlink
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "保存落地页连接信息", notes = "", httpMethod = "POST")
	public BaseResult addLydlink(@RequestBody Lydlink lydlink, HttpServletRequest request) {
		logger.info("添加落地页连接");
		BaseResult result = new BaseResult("1", "添加成功");
		try {
			Adadm user =  (Adadm) request.getSession().getAttribute("admin");
			lydlink.setLstatus("待发布");
			lydlink.setAdduserid(user.getUserid());
			lydlink.setAddtime(new Date());
			lydlinkService.save(lydlink);
			Userlog log = new Userlog();
			log.setLogtime(new Date());
			log.setLuodiyeid(lydlink.getLuodiyeid());
			log.setOptcode(lydlink.toString());
			log.setOptname("添加落地页链接");
			log.setUserid(user.getUserid());
			userLogService.save(log);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("添加失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改落地页连接
	 * 
	 * @param lydlink
	 * @return
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "修改落地页连接信息", notes = "", httpMethod = "POST")
	public BaseResult updateLydlink(Lydlink lydlink) {
		logger.info("修改落地页连接");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			lydlinkService.update(lydlink);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除落地页连接
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "删除落地页连接", notes = "", httpMethod = "POST")
	public BaseResult deleteLydlink(int[] ids) {
		logger.info("删除落地页连接");
		BaseResult result = new BaseResult("1", "删除成功");
		for (int id : ids) {
			try {
				lydlinkService.delete(id);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("删除失败");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id 查询落地页连接
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "根据id查询落地页连接信息", notes = "", httpMethod = "POST")
	public BaseObjectResult<Lydlink> getLydlink(int id) {
		logger.info("根据id查询落地页连接");
		BaseObjectResult<Lydlink> result = new BaseObjectResult<Lydlink>("1", "根据id查询落地页连接成功");
		try {
			Lydlink lydlink = lydlinkService.get(id);
			result.setResult(lydlink);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询落地页连接失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询落地页连接集合 分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "查询落地页连接集合", notes = "", httpMethod = "POST")
	public BaseListResult<Lydlink> listLydlink(int page, int rows) {
		logger.info("查询落地页连接集合");
		BaseListResult<Lydlink> result = new BaseListResult<Lydlink>();
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			result = lydlinkService.findListByPage(params, page, rows);
			result.setE("1");
			result.setErrorMessage("查询落地页连接集合成功");
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询落地页连接集合失败");
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 查询所有落地页连接
	 *
	 * @return
	 */
	@RequestMapping(value = "/AllList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "查询所有落地页连接", notes = "", httpMethod = "POST")
	public BaseListResult<Lydlink> AllList() {
		logger.info("查询所有落地页连接");
		BaseListResult<Lydlink> result = new BaseListResult<Lydlink>("1", "查询所有落地页连接成功");
		try {
			String hql = "from Lydlink";
			List<Lydlink> list = lydlinkService.find(hql);
			result.setResult(list);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询所有落地页连接失败");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/isExistCode",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "检查自定义代码是否存在", notes = "", httpMethod = "POST")
	public BaseResult isExistCode(String code) {
		logger.info("落地页连接锁定");
		BaseResult lockLink = lydlinkService.isExistCode(code);
		return lockLink;
	}
	
	@RequestMapping(value="/getInfoByLuodiyeId",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "根据落地页id查询连接信息", notes = "", httpMethod = "POST")
	public BaseObjectResult<Lydlink> getInfoByLuodiyeId(int luodiyeid){
		logger.info("根据落地页id查询连接信息");
		BaseObjectResult<Lydlink> result = lydlinkService.getInfoByLuodiyeId(luodiyeid);
		return result;
	}
	
	
	@RequestMapping(value="/issueLink",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "落地页连接发布", notes = "", httpMethod = "POST")
	public BaseObjectResult<Map<String, Object>> issueLink(@RequestBody Lydlink link, HttpServletRequest req){
		BaseObjectResult<Map<String, Object>> result = lydlinkService.issueLink(link, req);
		return result;
	}
	
	
	@RequestMapping(value="/lockLink",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "落地页连接锁定", notes = "", httpMethod = "POST")
	public BaseResult lockLink(int LydlinkId) {
		logger.info("落地页连接锁定");
		BaseResult lockLink = lydlinkService.lockLink(LydlinkId);
		return lockLink;
	}
	
	@RequestMapping(value="/unlockLink",method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "落地页连接解锁", notes = "", httpMethod = "POST")
	public BaseResult unlockLink(int lydlinkId) {
		logger.info("落地页连接解锁");
		BaseResult result = lydlinkService.unlockLink(lydlinkId);
		return result;
	}

}
