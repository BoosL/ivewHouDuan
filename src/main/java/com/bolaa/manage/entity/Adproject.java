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

// 数据库表(ADProject )映射对象
@Entity
@Table(name = "ADProject")
public class Adproject implements Serializable{

	private static final long serialVersionUID = 425897021544211044L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- PID*/
	private Integer pid = null;
	
	/* Column --  --length:50--decimalLen:0*/
		private String pname = null;

	/* Column --  --length:19--decimalLen:0*/
		private Date begintime = null;

	/* Column --  --length:19--decimalLen:0*/
		private Date endtime = null;

	/* Column --  --length:200--decimalLen:0*/
		private String pnote = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer classid = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer adduserid = null;

	/* Column --  --length:19--decimalLen:0*/
		private Date addtime = null;

	/* Column --  --length:4--decimalLen:0*/
		private String pstatus = null;
		
	/* Column --  --length:500--decimalLen:0*/	
		private String picurl = null;



	public Adproject(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PID", insertable = true, updatable = true, nullable = false)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	 


		@Column(name="PNAME")
	public String getPname() {
		return this.pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
		@Column(name="BEGINTIME")
	public Date getBegintime() {
		return this.begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
		@Column(name="ENDTIME")
	public Date getEndtime() {
		return this.endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
		@Column(name="PNOTE")
	public String getPnote() {
		return this.pnote;
	}
	public void setPnote(String pnote) {
		this.pnote = pnote;
	}
	
		@Column(name="CLASSID")
	public Integer getClassid() {
		return this.classid;
	}
	public void setClassid(Integer classid) {
		this.classid = classid;
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
	
		@Column(name="PSTATUS")
	public String getPstatus() {
		return this.pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	
		@Column(name="PICURL")
	public String getPicurl() {
		return this.picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
		
	@Embedded

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
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
		final Adproject other = (Adproject) obj;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Adproject ( "
	        + "Pid = " + this.pid + TAB
	        + "Pname = " + this.pname + TAB
	        + "Begintime = " + this.begintime + TAB
	        + "Endtime = " + this.endtime + TAB
	        + "Pnote = " + this.pnote + TAB
	        + "Classid = " + this.classid + TAB
	        + "Adduserid = " + this.adduserid + TAB
	        + "Addtime = " + this.addtime + TAB
	        + "Pstatus = " + this.pstatus + TAB
	        + "Picurl = " + this.picurl + TAB
	        + " )";
	
	    return str;
	}

} 
