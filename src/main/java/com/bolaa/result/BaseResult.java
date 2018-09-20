package com.bolaa.result;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @ClassName: BaseResult
 * @Description: web服务返回结果基类
 * @author xsm
 * @date 2015-10-20 21:35:56
 */
public class BaseResult implements Serializable {
	private static final long serialVersionUID = -6900139280435767733L;
	/**
	 * @Fields errorCode: 错误码 
	 * code="0" 标识成功
	 */
	private String e;

	/**
	 * @Fields errorMessage:错误描述
	 */
	private String errorMessage;

	private String requestId;
	
	private String responseTime;
	
	private Integer total;
	
	private Integer page;
	
	private Integer pageAll;

	public BaseResult() {
		super();
		requestId = UUID.randomUUID().toString();
		responseTime=System.currentTimeMillis()+"";
	}

	public BaseResult(String errorCode, String errorMessage) {
		this.e = StringUtils.defaultString(errorCode);
		this.errorMessage = StringUtils.defaultString(errorMessage);
		requestId = UUID.randomUUID().toString();
		responseTime=System.currentTimeMillis()+"";
	}
	
	

	public BaseResult(String errorCode, String errorMessage,Integer total,Integer page) {
		
		this.e = StringUtils.defaultString(errorCode);
		this.errorMessage = StringUtils.defaultString(errorMessage);
		requestId = UUID.randomUUID().toString();
		responseTime=System.currentTimeMillis()+"";
		this.total = total;
		this.page = page;
	}


	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	
	

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageAll() {
		return pageAll;
	}

	public void setPageAll(Integer pageAll) {
		this.pageAll = pageAll;
	}
	
}
