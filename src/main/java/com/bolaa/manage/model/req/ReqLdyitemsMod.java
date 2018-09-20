package com.bolaa.manage.model.req;

public class ReqLdyitemsMod {

	private String ltitle = null;

	private String itemclass = null;

	private String filesUrl = null;

	private String content = null;

	private String ismust = null;

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

	public String getFilesUrl() {
		return filesUrl;
	}

	public void setFilesUrl(String filesUrl) {
		this.filesUrl = filesUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsmust() {
		return ismust;
	}

	public void setIsmust(String ismust) {
		this.ismust = ismust;
	}

	public ReqLdyitemsMod(String ltitle, String itemclass, String filesUrl, String content, String ismust) {
		super();
		this.ltitle = ltitle;
		this.itemclass = itemclass;
		this.filesUrl = filesUrl;
		this.content = content;
		this.ismust = ismust;
	}

	public ReqLdyitemsMod() {
		super();
	}

	@Override
	public String toString() {
		return "ReqLdyitemsMod [ltitle=" + ltitle + ", itemclass=" + itemclass + ", filesUrl=" + filesUrl + ", content="
				+ content + ", ismust=" + ismust + "]";
	}

}
