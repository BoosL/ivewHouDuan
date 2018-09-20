package com.bolaa.manage.model.req;

import java.util.Date;

public class ReqCostcalculatMod {
	private Integer pId;
	private Date beginTime;
	private Date endTime;
	private Integer linkId;
	private Integer channelId;
	private Integer pvCount;
	private Integer uvCount;
	private Integer ipCount;
	private Integer subCount;
	private Integer sum;
	private Float cost;

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
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

	public Integer getLinkId() {
		return linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getPvCount() {
		return pvCount;
	}

	public void setPvCount(Integer pvCount) {
		this.pvCount = pvCount;
	}

	public Integer getUvCount() {
		return uvCount;
	}

	public void setUvCount(Integer uvCount) {
		this.uvCount = uvCount;
	}

	public Integer getIpCount() {
		return ipCount;
	}

	public void setIpCount(Integer ipCount) {
		this.ipCount = ipCount;
	}

	public Integer getSubCount() {
		return subCount;
	}

	public void setSubCount(Integer subCount) {
		this.subCount = subCount;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public ReqCostcalculatMod(Integer pId, Date beginTime, Date endTime, Integer linkId, Integer channelId,
			Integer pvCount, Integer uvCount, Integer ipCount, Integer subCount, Integer sum, Float cost) {
		super();
		this.pId = pId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.linkId = linkId;
		this.channelId = channelId;
		this.pvCount = pvCount;
		this.uvCount = uvCount;
		this.ipCount = ipCount;
		this.subCount = subCount;
		this.sum = sum;
		this.cost = cost;
	}

	public ReqCostcalculatMod() {
		super();
	}

	@Override
	public String toString() {
		return "ReqCostcalculatMod [pId=" + pId + ", beginTime=" + beginTime + ", endTime=" + endTime + ", linkId="
				+ linkId + ", channelId=" + channelId + ", pvCount=" + pvCount + ", uvCount=" + uvCount + ", ipCount="
				+ ipCount + ", subCount=" + subCount + ", sum=" + sum + ", cost=" + cost + "]";
	}

}
