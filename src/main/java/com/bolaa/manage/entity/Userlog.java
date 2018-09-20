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

// 数据库表(UserLog )映射对象
@Entity
@Table(name = "UserLog")
public class Userlog implements Serializable{

	private static final long serialVersionUID = 2302906554807236401L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- LOGID*/
	private Integer logid = null;
	
	/* Column --  --length:19--decimalLen:0*/
		private Date logtime = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer userid = null;

	/* Column --  --length:50--decimalLen:0*/
		private String optcode = null;

	/* Column --  --length:50--decimalLen:0*/
		private String optname = null;
		
	/* Column --  --length:11--decimalLen:0*/
		private Integer pid = null;
		
	/* Column --  --length:11--decimalLen:0*/
		private Integer luodiyeid = null;


	public Userlog(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOGID", insertable = true, updatable = true, nullable = false)
	public Integer getLogid() {
		return this.logid;
	}

	public void setLogid(Integer logid) {
		this.logid = logid;
	}
	 


		@Column(name="LOGTIME")
	public Date getLogtime() {
		return this.logtime;
	}
	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}
	
		@Column(name="USERID")
	public Integer getUserid() {
		return this.userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
		@Column(name="OPTCODE")
	public String getOptcode() {
		return this.optcode;
	}
	public void setOptcode(String optcode) {
		this.optcode = optcode;
	}
	
		@Column(name="OPTNAME")
	public String getOptname() {
		return this.optname;
	}
	public void setOptname(String optname) {
		this.optname = optname;
	}
	
	
		@Column(name="PID")
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
		@Column(name="LUODIYEID")
	public Integer getLuodiyeid() {
		return luodiyeid;
	}

	public void setLuodiyeid(Integer luodiyeid) {
		this.luodiyeid = luodiyeid;
	}

	@Embedded

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logid == null) ? 0 : logid.hashCode());
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
		final Userlog other = (Userlog) obj;
		if (logid == null) {
			if (other.logid != null)
				return false;
		} else if (!logid.equals(other.logid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Userlog ( "
	        + "Logid = " + this.logid + TAB
	        + "Logtime = " + this.logtime + TAB
	        + "Userid = " + this.userid + TAB
	        + "Optcode = " + this.optcode + TAB
	        + "Optname = " + this.optname + TAB
	        + "Pid = " + this.pid + TAB
	        + "Luodiyeid = " + this.luodiyeid + TAB
	        + " )";
	
	    return str;
	}

} 
