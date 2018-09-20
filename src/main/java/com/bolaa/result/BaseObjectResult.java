package com.bolaa.result;

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
public class BaseObjectResult<T> extends BaseResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2498912800071557701L;

	private final static String DEFAULT_CODE = "SUCCESS";

	private T result;

	private String requestId;

	public BaseObjectResult() {
		setE(DEFAULT_CODE);
		requestId = UUID.randomUUID().toString();
	}

	public BaseObjectResult(T object) {
		this.result = object;
		setE(DEFAULT_CODE);
		requestId = UUID.randomUUID().toString();
	}

	public BaseObjectResult(String errorCode, String errorMessage) {
		super(StringUtils.defaultString(errorCode), StringUtils
				.defaultString(errorMessage));
		requestId = UUID.randomUUID().toString();
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public static String getDefaultCode() {
		return DEFAULT_CODE;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
