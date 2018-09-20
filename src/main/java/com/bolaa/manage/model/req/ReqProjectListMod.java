package com.bolaa.manage.model.req;

import java.util.Date;

public class ReqProjectListMod {
	private Date beginTime;
	private Date endTime;
	private Integer classId;
	private String projectName;
	private String pstatus;
	private int page;
	private int rows;

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

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPstatus() {
		return pstatus;
	}

	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
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

	@Override
	public String toString() {
		return "ReqProjectListMod [beginTime=" + beginTime + ", endTime=" + endTime + ", classId=" + classId
				+ ", projectName=" + projectName + ", pstatus=" + pstatus + ", page=" + page + ", rows=" + rows + "]";
	}

	public ReqProjectListMod(Date beginTime, Date endTime, Integer classId, String projectName, String pstatus,
			int page, int rows) {
		super();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.classId = classId;
		this.projectName = projectName;
		this.pstatus = pstatus;
		this.page = page;
		this.rows = rows;
	}

	public ReqProjectListMod() {
		super();
	}

}
