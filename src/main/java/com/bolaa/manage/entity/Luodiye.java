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

// 数据库表(Luodiye )映射对象
@Entity
@Table(name = "Luodiye")
public class Luodiye implements Serializable{

	private static final long serialVersionUID = 4447707730465484241L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- LUODIYEID*/
	private Integer luodiyeid = null;
	
	/* Column --  --length:10--decimalLen:0*/
		private Integer pid = null;

	/* Column --  --length:20--decimalLen:0*/
		private String lname = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer adduserid = null;

	/* Column --  --length:19--decimalLen:0*/
		private Date addtime = null;



	public Luodiye(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LUODIYEID", insertable = true, updatable = true, nullable = false)
	public Integer getLuodiyeid() {
		return this.luodiyeid;
	}

	public void setLuodiyeid(Integer luodiyeid) {
		this.luodiyeid = luodiyeid;
	}
	 


		@Column(name="PID")
	public Integer getPid() {
		return this.pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
		@Column(name="LNAME")
	public String getLname() {
		return this.lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
		result = prime * result + ((luodiyeid == null) ? 0 : luodiyeid.hashCode());
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
		final Luodiye other = (Luodiye) obj;
		if (luodiyeid == null) {
			if (other.luodiyeid != null)
				return false;
		} else if (!luodiyeid.equals(other.luodiyeid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Luodiye ( "
	        + "Luodiyeid = " + this.luodiyeid + TAB
	        + "Pid = " + this.pid + TAB
	        + "Lname = " + this.lname + TAB
	        + "Adduserid = " + this.adduserid + TAB
	        + "Addtime = " + this.addtime + TAB
	        + " )";
	
	    return str;
	}

} 
