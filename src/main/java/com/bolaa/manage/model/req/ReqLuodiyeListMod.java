package com.bolaa.manage.model.req;

import java.util.Date;

public class ReqLuodiyeListMod {
	private Date addTime;
	private Integer classId;
	private Integer channelid;
	private String lstatus;
	private Integer adduserid;
	private int page;
	private int rows;

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	public String getLstatus() {
		return lstatus;
	}

	public void setLstatus(String lstatus) {
		this.lstatus = lstatus;
	}

	public Integer getAdduserid() {
		return adduserid;
	}

	public void setAdduserid(Integer adduserid) {
		this.adduserid = adduserid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public ReqLuodiyeListMod(Date addTime, Integer classId, Integer channelid, String lstatus, Integer adduserid,
			int page, int rows) {
		super();
		this.addTime = addTime;
		this.classId = classId;
		this.channelid = channelid;
		this.lstatus = lstatus;
		this.adduserid = adduserid;
		this.page = page;
		this.rows = rows;
	}

	public ReqLuodiyeListMod() {
		super();
	}

	@Override
	public String toString() {
		return "ReqLuodiyeListMod [addTime=" + addTime + ", classId=" + classId + ", channelid=" + channelid
				+ ", lstatus=" + lstatus + ", adduserid=" + adduserid + ", page=" + page + ", rows=" + rows + "]";
	}

}
