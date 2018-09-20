package com.bolaa.manage.model.req;

public class ReqLdyitemInfo {

	private Integer ldyitemid = null;

	private String ltitle = null;

	private String itemclass = null;

	private String picurl = null;

	private String videourl = null;

	private String picurls = null;

	private String ltext = null;

	private String ismust = null;

	private Integer pid = null;

	public Integer getLdyitemid() {
		return ldyitemid;
	}

	public void setLdyitemid(Integer ldyitemid) {
		this.ldyitemid = ldyitemid;
	}

	public String getLtitle() {
		return ltitle;
	}

	public void setLtitle(String ltitle) {
		this.ltitle = ltitle;
	}

	public String getItemclass() {
		return itemclass;
	}

	public void setItemclass(String itemclass) {
		this.itemclass = itemclass;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public String getPicurls() {
		return picurls;
	}

	public void setPicurls(String picurls) {
		this.picurls = picurls;
	}

	public String getLtext() {
		return ltext;
	}

	public void setLtext(String ltext) {
		this.ltext = ltext;
	}

	public String getIsmust() {
		return ismust;
	}

	public void setIsmust(String ismust) {
		this.ismust = ismust;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public ReqLdyitemInfo(Integer ldyitemid, String ltitle, String itemclass, String picurl, String videourl,
			String picurls, String ltext, String ismust, Integer pid) {
		super();
		this.ldyitemid = ldyitemid;
		this.ltitle = ltitle;
		this.itemclass = itemclass;
		this.picurl = picurl;
		this.videourl = videourl;
		this.picurls = picurls;
		this.ltext = ltext;
		this.ismust = ismust;
		this.pid = pid;
	}

	public ReqLdyitemInfo() {
		super();
	}

	@Override
	public String toString() {
		return "ReqLdyitemInfo [ldyitemid=" + ldyitemid + ", ltitle=" + ltitle + ", itemclass=" + itemclass
				+ ", picurl=" + picurl + ", videourl=" + videourl + ", picurls=" + picurls + ", ltext=" + ltext
				+ ", ismust=" + ismust + ", pid=" + pid + "]";
	}

}
