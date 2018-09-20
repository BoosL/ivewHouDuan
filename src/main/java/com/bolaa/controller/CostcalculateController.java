package com.bolaa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bolaa.manage.entity.Costcalculate;
import com.bolaa.manage.model.req.ReqCostcalculatMod;
import com.bolaa.manage.service.CostcalculateService;
import com.bolaa.result.BaseListResult;
import com.bolaa.result.BaseObjectResult;
import com.bolaa.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 成本计算 Controller
 * 
 * @author Cyle
 *
 */
@Controller
@RequestMapping("/costCalculate")
@Api(value="成本计数",description = "成本计数接口")
public class CostcalculateController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	CostcalculateService costCalculateService;

	/**
	 * 添加成本计算
	 * 
	 * @param costCalculate
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "添加成本计算" ,notes = "", httpMethod = "POST")
	public BaseResult addCostcalculate(Costcalculate costCalculate) {
		logger.info("添加成本计算");
		BaseResult result = new BaseResult("1", "添加成功");
		try {
			costCalculateService.save(costCalculate);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("添加成功");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改成本计算
	 * 
	 * @param costCalculate
	 * @return
	 */
	@RequestMapping(value="/update",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "修改成本计算" ,notes = "", httpMethod = "POST")
	public BaseResult updateCostcalculate(Costcalculate costCalculate) {
		logger.info("修改成本计算");
		BaseResult result = new BaseResult("1", "修改成功");
		try {
			costCalculateService.update(costCalculate);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除成本计算
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "删除成本计算" ,notes = "", httpMethod = "POST")
	public BaseResult deleteCostcalculate(int[] ids) {
		logger.info("删除成本计算");
		BaseResult result = new BaseResult("1", "删除成功");
		for (int id : ids) {
			try {
				costCalculateService.delete(id);
			} catch (Exception e) {
				result.setE("0");
				result.setErrorMessage("删除失败");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据id 查询成本计算
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "根据id查询成本计算" ,notes = "", httpMethod = "POST")
	public BaseObjectResult<Costcalculate> getCostcalculate(int id) {
		logger.info("根据id查询成本计算");
		BaseObjectResult<Costcalculate> result = new BaseObjectResult<Costcalculate>("1", "根据id查询成本计算成功");
		try {
			Costcalculate costCalculate = costCalculateService.get(id);
			result.setResult(costCalculate);
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("根据id查询成本计算失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询成本计算集合 分页
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查询成本计算集合" ,notes = "", httpMethod = "POST")
	public BaseListResult<Costcalculate> listCostcalculate(int page, int rows) {
		logger.info("查询成本计算集合");
		BaseListResult<Costcalculate> result = new BaseListResult<Costcalculate>();
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			result = costCalculateService.findListByPage(params, page, rows);
			result.setE("1");
			result.setErrorMessage("查询成本计算集合成功");
		} catch (Exception e) {
			result.setE("0");
			result.setErrorMessage("查询成本计算集合失败");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/lookCompute",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "查看成本计算" ,notes = "", httpMethod = "POST")
	public BaseObjectResult<Costcalculate> lookCompute(@RequestBody ReqCostcalculatMod reqMod){
		BaseObjectResult<Costcalculate> result = costCalculateService.lookCompute(reqMod);
		return result;
	}
	
	@RequestMapping(value="/compute",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@ApiOperation(value = "成本计算" ,notes = "", httpMethod = "POST")
	public BaseResult saveCompute(@RequestBody ReqCostcalculatMod reqMod, HttpServletRequest request){
		BaseResult result = costCalculateService.saveCompute(reqMod);
		return result;
	}
	

}
