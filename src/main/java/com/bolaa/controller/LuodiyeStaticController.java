package com.bolaa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bolaa.common.IPUtils;
import com.bolaa.manage.entity.Luodiye;
import com.bolaa.manage.service.LuodiyeService;
import com.bolaa.manage.service.LydlinkService;
import com.bolaa.result.BaseListResult;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/luoOperate")
public class LuodiyeStaticController {
	
	@Autowired
	private LuodiyeService luodiyeService;
	
	
	@RequestMapping(value = "/lookLuodiye" , method = {RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "手机页面预览" ,notes = "", httpMethod = "POST") 
	public String lookLuodiye( int luodiyeId, HttpServletRequest request) throws Exception{
		
		BaseListResult<Map<String,Object>> result = luodiyeService.luodiyeInfo(luodiyeId);
		List<Map<String,Object>> list = result.getResult();
		for (Map<String, Object> map : list) {
			List<Map<String, Object>> tagList = new ArrayList<Map<String,Object>>();
			String picurls = (String) map.get("picurls");
			if(StringUtils.hasText(picurls)){
				List<String> listUrl = new ArrayList<String>();
				String[] split = picurls.split(",");
				for (String url : split) {
					listUrl.add(url);
				}
				map.put("listUrl", listUrl);
			}else{
				map.put("listUrl", null);
			}
			String tagStr = (String) map.get("choiceitmes");
			if(StringUtils.hasText(tagStr)){
				String[] l = tagStr.split("\\|");
				for (String obj : l) {
					String[] element = obj.split(":");
					Map<String, Object> valMap = new HashMap<String, Object>();
					valMap.put("imgUrl", element[0]);
					valMap.put("text", element[1]);
					tagList.add(valMap);
				}
				map.put("tagList", tagList);
			}else{
				map.put("tagList", null);
			}
		}
		request.setAttribute("items", list);
		Map<String, Object> mapInfo = luodiyeService.getDefaultInfo(luodiyeId);
		request.setAttribute("mapInfo", mapInfo);
		
		return "default";
	}
	
	
	@RequestMapping(value = "/getIpInfo" , method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "获取ip详细信息" ,notes = "", httpMethod = "POST") 
	public String getIpInfo(String ip){
		String ipInfo = IPUtils.getIpInfo(ip);
		return ipInfo;
	}
	
	
}
