package com.bolaa.controller;

import java.util.Date;
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

import com.bolaa.common.BeanUtil;
import com.bolaa.manage.entity.Adadm;
import com.bolaa.manage.entity.Adchannel;
import com.bolaa.manage.entity.Userlog;
import com.bolaa.manage.service.AdchannelService;
import com.bolaa.manage.service.UserlogService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 投放渠道 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/adchannel")
@Api(value="投放渠道",description = "投放渠道接口")
public class AdchannelController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	AdchannelService adchannelService;
	@Autowired
	UserlogService userLogService;

	/**
	 * 添加投放渠道
	 * 
	 * @param adchannel
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "添加投放渠道", notes = "", httpMethod = "POST")
	public BaseResult addAdchannel(@RequestBody Adchannel adchannel, HttpServletRequest request) {
		logger.info("添加投放渠道");
		BaseResult result = new BaseResult("1", "添加成功");
		try {
			Adadm user = (Adadm) request.getSession().getAttribute("admin");
			if(user == null){
				result.setE("0");
				result.setErrorMessage("请先登录");
				return result;
			}

			if(adchannel.getChannelname()==null || "".equals(adchannel.getChannelname())){
				result.setE("0");
				result.setErrorMessage("投放渠道不能为空");
				return result;
			}

			if(adchannel.getChannelcode()==null || "".equals(adchannel.getChannelcode())){
				result.setE("0");
				result.setErrorMessage("渠道代码不能为空");
				return result;
			}
			adchannel.setAdduserid(user.getUserid());
			adchannel.setAddtime(new Date());
			adchannelService.save(adchannel);
			Userlog log = new Userlog();
			log.setLogtime(new Date());
			log.setOptcode(adchannel.toString());
			log.setOptname("添加投放渠道");
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
	 * 修改投放渠道
	 * 
	 * @param adchannel
	 * @return
	 */
	@RequestMapping(value="/update",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "修改投放渠道", notes = "", httpMethod = "POST")
	public BaseResult updateAdchannel(@RequestBody Adchannel adchannel, HttpServletRequest request) {
		logger.info("修改投放渠道");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			Adadm user = (Adadm) request.getSession().getAttribute("admin");
			if(user == null){
				result.setE("0");
				result.setErrorMessage("请先登录");
				return result;
			}

			if(adchannel.getChannelname()==null || "".equals(adchannel.getChannelname())){
				result.setE("0");
				result.setErrorMessage("投放渠道不能为空");
				return result;
			}

			if(adchannel.getChannelcode()==null || "".equals(adchannel.getChannelcode())){
				result.setE("0");
				result.setErrorMessage("渠道代码不能为空");
				return result;
			}

			Adchannel channel = adchannelService.get(adchannel.getChannelid());
			BeanUtil.copyNotNullProperties(adchannel, channel);
			channel.setAdduserid(user.getUserid());
			adchannelService.update(channel);
			
			Userlog log = new Userlog();
			log.setLogtime(new Date());
			log.setOptcode(adchannel.toString());
			log.setOptname("修改投放渠道");
			log.setUserid(user.getUserid());
			userLogService.save(log);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除投放渠道
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "删除投放渠道", notes = "", httpMethod = "POST")
	public BaseResult deleteAdchannel(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		logger.info("删除投放渠道");
		BaseResult result = new BaseResult("1", "删除成功");
			Adadm user = (Adadm) request.getSession().getAttribute("admin");
			if(user == null){
				result.setE("0");
				result.setErrorMessage("请先登录");
				return result;
			}	
			try {
				adchannelService.delete((int)map.get("id"));
				Userlog log = new Userlog();
				log.setLogtime(new Date());
				log.setOptcode(map.toString());
				log.setOptname("删除投放渠道");
				log.setUserid(user.getUserid());
				userLogService.save(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		return result;
	}

	/**
	 * 根据id 查询投放渠道
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "根据id查询投放渠道", notes = "", httpMethod = "POST")
	public BaseObjectResult<Adchannel> getAdchannel(int id) {
		logger.info("根据id查询投放渠道");
		BaseObjectResult<Adchannel> result = new BaseObjectResult<Adchannel>("1", "根据id查询投放渠道成功");
		try {
			Adchannel adchannel = adchannelService.get(id);
			result.setResult(adchannel);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询投放渠道失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询投放渠道集合 分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查询投放渠道集合", notes = "", httpMethod = "POST")
	//int page, int rows
	public BaseListResult<Map<String, Object>> listAdchannel(@RequestBody Map<String, Object> map) {
		logger.info("查询投放渠道集合");
		BaseListResult<Map<String,Object>> result = adchannelService.listAdchannel((int)map.get("page"), (int)map.get("rows"));
		return result;
	}
	
	/**
	 * 查询所有投放渠道集合
	 *
	 * @return
	 */
	@RequestMapping(value = "/AllList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "查询所有投放渠道集合", notes = "", httpMethod = "POST")
	public BaseListResult<Adchannel> AllList() {
		logger.info("查询所有投放渠道集合");
		BaseListResult<Adchannel> result = new BaseListResult<Adchannel>("1", "查询所有投放渠道成功");
		try {
			String hql = "from Adchannel";
			List<Adchannel> list = adchannelService.find(hql);
			result.setResult(list);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询所有投放渠道失败");
			e.printStackTrace();
		}
		return result;
	}

}
