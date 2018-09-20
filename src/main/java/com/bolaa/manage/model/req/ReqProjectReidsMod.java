package com.bolaa.manage.model.req;

import java.util.Date;

public class ReqProjectReidsMod {
	private String pId;
	private Date beginTime;
	private Date endTime;
	private String linkId;
	private String channelId;

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public ReqProjectReidsMod(String pId, Date beginTime, Date endTime, String linkId, String channelId) {
		super();
		this.pId = pId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.linkId = linkId;
		this.channelId = channelId;
	}

	public ReqProjectReidsMod() {
		super();
	}

	@Override
	public String toString() {
		return "reqProjectReidsMod [pId=" + pId + ", beginTime=" + beginTime + ", endTime=" + endTime + ", linkId="
				+ linkId + ", channelId=" + channelId + "]";
	}

}
