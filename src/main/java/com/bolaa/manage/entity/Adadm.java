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

import org.springframework.format.annotation.DateTimeFormat;

// 数据库表(ADAdm )映射对象
@Entity
@Table(name = "ADAdm")
public class Adadm implements Serializable{

	private static final long serialVersionUID = 3982164163480881L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- USERID*/
	private Integer userid = null;
	
	/* Column --  --length:20--decimalLen:0*/
		private String username = null;

	/* Column --  --length:200--decimalLen:0*/
		private String password = null;

	/* Column --  --length:50--decimalLen:0*/
		private String phone = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer adduserid = null;

	/* Column --  --length:19--decimalLen:0*/
		@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private Date addtime = null;

	/* Column --  --length:3--decimalLen:0*/
		private String userstatus = null;

	/* Column --  --length:19--decimalLen:0*/
		@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private Date logintime = null;
		/* Column --  --length:11--decimalLen:0*/
		private Integer roleid = null;
		


	public Adadm(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERID", insertable = true, updatable = true, nullable = false)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	 


		@Column(name="USERNAME")
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
		@Column(name="PASSWORD")
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
		@Column(name="PHONE")
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
		@Column(name="USERSTATUS")
	public String getUserstatus() {
		return this.userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
	
		@Column(name="LOGINTIME")
	public Date getLogintime() {
		return this.logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	
	@Column(name="ROLEID")
	public Integer getRoleid() {
		return this.roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
		
	@Embedded

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		final Adadm other = (Adadm) obj;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Adadm ( "
	        + "Userid = " + this.userid + TAB
	        + "Username = " + this.username + TAB
	        + "Password = " + this.password + TAB
	        + "Phone = " + this.phone + TAB
	        + "Adduserid = " + this.adduserid + TAB
	        + "Addtime = " + this.addtime + TAB
	        + "Userstatus = " + this.userstatus + TAB
	        + "Logintime = " + this.logintime + TAB
	        + "roleid = " + this.roleid + TAB
	        + " )";
	
	    return str;
	}

} 
