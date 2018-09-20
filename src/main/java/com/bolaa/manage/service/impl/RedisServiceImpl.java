package com.bolaa.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolaa.manage.dao.impl.RedisDaoImp;

@Service
public class RedisServiceImpl {
	@Autowired
	private RedisDaoImp redisDao;
	
	/**
	 * 获取项目首页信息
	 * @param jsonStr
	 * @return
	 */
	public List<Map<String, Object>> indexProjectInfo(String jsonStr){
		List<Map<String, Object>> retList = new ArrayList<Map<String,Object>>();
		String[] split = jsonStr.split(",");
		for (int i = 0; i < split.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String pvKey = "pv_pid_"+split[i];
			String jumpKey = "ratio_pid_"+split[i];
			String timeKey = "time_pid_"+split[i];
			int pv = redisDao.getProjectPV(pvKey);
			map.put("pv", pv);
			int jumpNum = redisDao.getProjectJumpNum(jumpKey);
			if(pv!=0){
				map.put("jump", ((double) Math.round(((double) jumpNum/pv) *100)/100)*100 );
			}else{
				map.put("jump", 0);
			}
			Map<Object, Object> readTime = redisDao.getProjectReadTime(timeKey);
			if(readTime.size()>0){
				 int timeVal = (int) readTime.get("timeVal");
				 int countVal = (int) readTime.get("countVal");
				map.put("timeOut", (double) Math.round(((double) timeVal/countVal) *100)/100 );
			}else{
				map.put("timeOut", 0);
			}
			retList.add(map);
		}
		return retList;
	}
	
	/**
	 * 获取项目redis信息
	 * @param pid
	 * @return
	 */
	public Map<String, Object> getRedisBypid(String pid){
		Map<String, Object> map = new HashMap<String, Object>();
		String pvKey = "pv_pid_"+pid;
		String uvKey = "uv_pid_"+pid;
		String ipKey = "ip_pid_"+pid;
		int projectPV = redisDao.getProjectPV(pvKey);
		long projectUV = redisDao.getProjectUV(uvKey);
		long projectIP = redisDao.getProjectIP(ipKey);
		map.put("pv", projectPV);
		map.put("uv", projectUV);
		map.put("ip", projectIP);
		return map;
	}
	
	
	//-----------------------------------
	/**
	 * 获取落地页统计时长
	 * @param luodiyeId
	 * @return
	 */
	public Map<String, Object> getClickTime(String luodiyeId){
		String mapKey = "luo_"+luodiyeId;
		Map<String, Object> map = redisDao.getClickTime(mapKey);
		return map;
	}
	
	/**
	 * 获取落地页模块信息
	 * @param modIds
	 * @param luodiyeid
	 * @return
	 */
	public Map<String, Object>  getluoMod(String modIds,String luodiyeid){
		String[] modId = modIds.split(",");
		String modKey = "luo_mod_"+luodiyeid;
		String subKey = "luo_sub_"+luodiyeid;
		List<Map<String,Object>> list = redisDao.getLuodiyeMod(modKey, modId);
		Map<String, Object> map = redisDao.getLuodiyeSub(subKey);
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("modInfo", list);
		retMap.put("subInfo", map);
		return retMap;
	}
	
	
	//----------------------------------------------------------------
	/**
	 * 模糊查询
	 * @param pId
	 * @param Date
	 * @param linkId
	 * @param channelId
	 * @return
	 */
	@RequestMapping(value="/getParam" ,method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> getParam(String pId, String beginTime, String endTime, String linkId, String channelId){
		StringBuffer pvKey = new StringBuffer("pv_");
		StringBuffer uvKey = new StringBuffer("uv_");
		StringBuffer ipKey = new StringBuffer("ip_");
		if(StringUtils.hasText(pId)){
			pvKey.append(pId+"_");
			uvKey.append(pId+"_");
			ipKey.append(pId+"_");
		}else{
			pvKey.append("*_");
			uvKey.append("*_");
			ipKey.append("*_");
		}
		// 时间
		pvKey.append("*_");
		uvKey.append("*_");
		ipKey.append("*_");
		if(StringUtils.hasText(linkId)){
			pvKey.append(linkId+"_");
			uvKey.append(linkId+"_");
			ipKey.append(linkId+"_");
		}else{
			pvKey.append("*_");
			uvKey.append("*_");
			ipKey.append("*_");
		}
		if(StringUtils.hasText(channelId)){
			pvKey.append(channelId);
			uvKey.append(channelId);
			ipKey.append(channelId);
		}else{
			pvKey.append("*");
			uvKey.append("*");
			ipKey.append("*");
		}
		
		System.out.println(pvKey.toString());
		Set<String> setPvKey = redisDao.getLikeKeys(pvKey.toString());
		String[] arrayPvKey = setPvKey.toArray(new String[setPvKey.size()]);
		
		Set<String> setUvKey = redisDao.getLikeKeys(uvKey.toString());
		String[] arrayUvKey = setUvKey.toArray(new String[setUvKey.size()]);
		
		Set<String> setIpKey = redisDao.getLikeKeys(ipKey.toString());
		String[] arrayIpKey = setIpKey.toArray(new String[setIpKey.size()]);
		
		List<String> PvList = new ArrayList<String>();
		List<String> UvList = new ArrayList<String>();
		List<String> IpList = new ArrayList<String>();
		for (int i = 0; i < arrayIpKey.length; i++) {
			String pvKeyInfo = arrayPvKey[i];
			String uvKeyInfo = arrayUvKey[i];
			String ipKeyInfo = arrayIpKey[i];
			String[] splitPv = pvKeyInfo.split("_");
			String[] splitUv = uvKeyInfo.split("_");
			String[] splitIp = ipKeyInfo.split("_");
			
			String pvTime = splitPv[3];
			int intPvTime = Integer.parseInt(pvTime);
			String uvTime = splitUv[3];
			int intUvTime = Integer.parseInt(uvTime);
			String ipTime = splitIp[3];
			int intIpTime = Integer.parseInt(ipTime);
			
			if(StringUtils.hasText(beginTime) && StringUtils.hasText(endTime)){
				if( (Integer.parseInt(beginTime) < intPvTime && intPvTime < Integer.parseInt(endTime))){
					PvList.add(arrayPvKey[i]);
				}
				if( (Integer.parseInt(beginTime) < intUvTime && intUvTime < Integer.parseInt(endTime))){
					UvList.add(arrayUvKey[i]);
				}
				if( (Integer.parseInt(beginTime) < intIpTime && intIpTime < Integer.parseInt(endTime))){
					IpList.add(arrayIpKey[i]);
				}
			}else if(StringUtils.hasText(beginTime) && (!StringUtils.hasText(endTime))){
				if( Integer.parseInt(beginTime) < intPvTime ){
					PvList.add(arrayPvKey[i]);
				}
				if( Integer.parseInt(beginTime) < intUvTime ){
					UvList.add(arrayUvKey[i]);
				}
				if( Integer.parseInt(beginTime) < intIpTime ){
					IpList.add(arrayIpKey[i]);
				}
			}else if(!StringUtils.hasText(beginTime) && (StringUtils.hasText(endTime)) ){
				if( intPvTime < Integer.parseInt(endTime) ){
					PvList.add(arrayPvKey[i]);
				}
				if( intUvTime < Integer.parseInt(endTime) ){
					UvList.add(arrayUvKey[i]);
				}
				if( intIpTime < Integer.parseInt(endTime) ){
					IpList.add(arrayIpKey[i]);
				}
			}else{
				IpList.add(arrayIpKey[i]);
				UvList.add(arrayUvKey[i]);
				PvList.add(arrayPvKey[i]);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (String ipK : IpList) {
			long ip = redisDao.getMonyParamIP(ipK);
			if(map.get("ipCount") == null){
				map.put("ipCount", ip);
			}else{
				map.put("ipCount", (long)map.get("ipCount") + ip);
			}
		}
		for (String pvK : PvList) {
			long pv = redisDao.getMonyParamPV(pvK);
			if(map.get("pvCount") == null){
				map.put("pvCount", pv);
			}else{
				map.put("pvCount", (long)map.get("pvCount") + pv);
			}
		}
		for (String uvK : UvList) {
			long uv = redisDao.getMonyParamUV(uvK);
			if(map.get("uvCount") == null){
				map.put("uvCount",uv);
			}else{
				map.put("uvCount", (long)map.get("uvCount") + uv);
			}
		}
		return map;
	}
	
	
	
}
