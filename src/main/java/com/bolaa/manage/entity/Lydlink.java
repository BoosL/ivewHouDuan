package com.bolaa.manage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// 数据库表(lydlink )映射对象
@Entity
@Table(name = "lydlink")
public class Lydlink implements Serializable{

	private static final long serialVersionUID = 4017471256248933025L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- LDYLINKID*/
	private Integer ldylinkid = null;
	
	/* Column --  --length:10--decimalLen:0*/
		private Integer luodiyeid = null;

	/* Column -- 链接代码具有唯一性 --length:200--decimalLen:0*/
		private String linkcode = null;

	/* Column -- http://域名/luodiye/渠道代码/链接代码
            
            域名可能根据分布式需求，生成不同域名 --length:500--decimalLen:0*/
		private String link = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer channelid = null;

	/* Column --  --length:200--decimalLen:0*/
		private String lnote = null;

	/* Column --  --length:4--decimalLen:0*/
		private String lstatus = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer adduserid = null;

	/* Column --  --length:19--decimalLen:0*/
		private Date addtime = null;



	public Lydlink(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LDYLINKID", insertable = true, updatable = true, nullable = false)
	public Integer getLdylinkid() {
		return this.ldylinkid;
	}

	public void setLdylinkid(Integer ldylinkid) {
		this.ldylinkid = ldylinkid;
	}
	 


		@Column(name="LUODIYEID")
	public Integer getLuodiyeid() {
		return this.luodiyeid;
	}
	public void setLuodiyeid(Integer luodiyeid) {
		this.luodiyeid = luodiyeid;
	}
	
		@Column(name="LINKCODE")
	public String getLinkcode() {
		return this.linkcode;
	}
	public void setLinkcode(String linkcode) {
		this.linkcode = linkcode;
	}
	
		@Column(name="LINK")
	public String getLink() {
		return this.link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
		@Column(name="CHANNELID")
	public Integer getChannelid() {
		return this.channelid;
	}
	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}
	
		@Column(name="LNOTE")
	public String getLnote() {
		return this.lnote;
	}
	public void setLnote(String lnote) {
		this.lnote = lnote;
	}
	
		@Column(name="LSTATUS")
	public String getLstatus() {
		return this.lstatus;
	}
	public void setLstatus(String lstatus) {
		this.lstatus = lstatus;
	}
	
		@Column(name="ADDUSERID")
	public Integer getAdduserid() {
		return this.adduserid;
	}
	public void setAdduserid(Integer adduserid) {
		this.adduserid = adduserid;
	}
	
		@Column(name="ADDTIME")
	public Date getAddtime() {
		return this.addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
		
	@Embedded

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ldylinkid == null) ? 0 : ldylinkid.hashCode());
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
		final Lydlink other = (Lydlink) obj;
		if (ldylinkid == null) {
			if (other.ldylinkid != null)
				return false;
		} else if (!ldylinkid.equals(other.ldylinkid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Lydlink ( "
	        + "Ldylinkid = " + this.ldylinkid + TAB
	        + "Luodiyeid = " + this.luodiyeid + TAB
	        + "Linkcode = " + this.linkcode + TAB
	        + "Link = " + this.link + TAB
	        + "Channelid = " + this.channelid + TAB
	        + "Lnote = " + this.lnote + TAB
	        + "Lstatus = " + this.lstatus + TAB
	        + "Adduserid = " + this.adduserid + TAB
	        + "Addtime = " + this.addtime + TAB
	        + " )";
	
	    return str;
	}

} 
