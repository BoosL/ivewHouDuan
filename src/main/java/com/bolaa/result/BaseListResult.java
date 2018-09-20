package com.bolaa.result;

import java.util.List;
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
public class BaseListResult<T> extends BaseResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1114893632282638448L;

	private final static String DEFAULT_CODE = "SUCCESS";

	private List<T> result;

	private String requestId;
	
	

	public BaseListResult() {
		setE(DEFAULT_CODE);
		requestId = UUID.randomUUID().toString();
	}

	public BaseListResult(List<T> list) {
		setE(DEFAULT_CODE);
		this.result = list;
		requestId = UUID.randomUUID().toString();
	}

	public BaseListResult(String errorCode, String errorMessage) {
		super(StringUtils.defaultString(errorCode), StringUtils
				.defaultString(errorMessage));
		requestId = UUID.randomUUID().toString();
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public static String getDefaultCode() {
		return DEFAULT_CODE;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
