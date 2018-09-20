package com.bolaa.manage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




// 数据库表(LdyItems )映射对象
@Entity
@Table(name = "LdyItems")
public class Ldyitems implements Serializable{

	private static final long serialVersionUID = 2045779415828577025L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- LDYITEMID*/
	private Integer ldyitemid = null;
	
	/* Column --  --length:200--decimalLen:0*/
		private String ltitle = null;

	/* Column --  --length:4--decimalLen:0*/
		private String itemclass = null;

	/* Column --  --length:500--decimalLen:0*/
		private String picurl = null;

	/* Column --  --length:500--decimalLen:0*/
		private String videourl = null;

	/* Column -- 可设置2-4张轮播图
            图片地址空格隔开 --length:2000--decimalLen:0*/
		private String picurls = null;

	/* Column --  --length:8000--decimalLen:0*/
		private String ltext = null;

	/* Column --  --length:2--decimalLen:0*/
		private String ismust = null;

	/* Column -- 可设置多个条目
            保存形式：
            pic1:条目1|pic2:条目2|pic3:条目3 --length:5000--decimalLen:0*/
		private String choiceitmes = null;

		private String url = null;

	public Ldyitems(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LDYITEMID", insertable = true, updatable = true, nullable = false)
	public Integer getLdyitemid() {
		return this.ldyitemid;
	}

	public void setLdyitemid(Integer ldyitemid) {
		this.ldyitemid = ldyitemid;
	}
	 


		@Column(name="LTITLE")
	public String getLtitle() {
		return this.ltitle;
	}
	public void setLtitle(String ltitle) {
		this.ltitle = ltitle;
	}
	
		@Column(name="ITEMCLASS")
	public String getItemclass() {
		return this.itemclass;
	}
	public void setItemclass(String itemclass) {
		this.itemclass = itemclass;
	}
	
		@Column(name="PICURL")
	public String getPicurl() {
		return this.picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
		@Column(name="VIDEOURL")
	public String getVideourl() {
		return this.videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	
		@Column(name="PICURLS")
	public String getPicurls() {
		return this.picurls;
	}
	public void setPicurls(String picurls) {
		this.picurls = picurls;
	}
	
		@Column(name="LTEXT")
	public String getLtext() {
		return this.ltext;
	}
	public void setLtext(String ltext) {
		this.ltext = ltext;
	}
	
		@Column(name="ISMUST")
	public String getIsmust() {
		return this.ismust;
	}
	public void setIsmust(String ismust) {
		this.ismust = ismust;
	}
	
		@Column(name="CHOICEITMES")
	public String getChoiceitmes() {
		return this.choiceitmes;
	}
	public void setChoiceitmes(String choiceitmes) {
		this.choiceitmes = choiceitmes;
	}
	
		@Column(name="URL")
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
		
	@Embedded

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ldyitemid == null) ? 0 : ldyitemid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Ldyitems other = (Ldyitems) obj;
		if (ldyitemid == null) {
			if (other.ldyitemid != null)
				return false;
		} else if (!ldyitemid.equals(other.ldyitemid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Ldyitems ( "
	        + "Ldyitemid = " + this.ldyitemid + TAB
	        + "Ltitle = " + this.ltitle + TAB
	        + "Itemclass = " + this.itemclass + TAB
	        + "Picurl = " + this.picurl + TAB
	        + "Videourl = " + this.videourl + TAB
	        + "Picurls = " + this.picurls + TAB
	        + "Ltext = " + this.ltext + TAB
	        + "Ismust = " + this.ismust + TAB
	        + "Choiceitmes = " + this.choiceitmes + TAB
	        + "Url = " + this.url + TAB
	        + " )";
	
	    return str;
	}

} 
