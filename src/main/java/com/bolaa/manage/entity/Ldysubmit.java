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

// 数据库表(LdySubmit )映射对象
@Entity
@Table(name = "LdySubmit")
public class Ldysubmit implements Serializable{

	private static final long serialVersionUID = 2812274024854664881L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- LDYSUBID*/
	private Integer ldysubid = null;
	
	/* Column --  --length:10--decimalLen:0*/
		private Integer ldyitemid = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer ldylinkid = null;

	/* Column --  --length:5000--decimalLen:0*/
		private String substance = null;

	/* Column --  --length:8000--decimalLen:0*/
		private String agent = null;

	/* Column --  --length:50--decimalLen:0*/
		private String ip = null;

	/* Column -- 同一用户提交表单有唯一标识 --length:500--decimalLen:0*/
		private String uniqueid = null;

	/* Column --  --length:19--decimalLen:0*/
		private Date addtime = null;

	/* Column --  --length:50--decimalLen:0*/
		private String province = null;

	/* Column --  --length:50--decimalLen:0*/
		private String city = null;

	/* Column --  --length:50--decimalLen:0*/
		private String district = null;



	public Ldysubmit(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LDYSUBID", insertable = true, updatable = true, nullable = false)
	public Integer getLdysubid() {
		return this.ldysubid;
	}

	public void setLdysubid(Integer ldysubid) {
		this.ldysubid = ldysubid;
	}
	 


		@Column(name="LDYITEMID")
	public Integer getLdyitemid() {
		return this.ldyitemid;
	}
	public void setLdyitemid(Integer ldyitemid) {
		this.ldyitemid = ldyitemid;
	}
	
		@Column(name="LDYLINKID")
	public Integer getLdylinkid() {
		return this.ldylinkid;
	}
	public void setLdylinkid(Integer ldylinkid) {
		this.ldylinkid = ldylinkid;
	}
	
		@Column(name="SUBSTANCE")
	public String getSubstance() {
		return this.substance;
	}
	public void setSubstance(String substance) {
		this.substance = substance;
	}
	
		@Column(name="AGENT")
	public String getAgent() {
		return this.agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	
		@Column(name="IP")
	public String getIp() {
		return this.ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
		@Column(name="UNIQUEID")
	public String getUniqueid() {
		return this.uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	
		@Column(name="ADDTIME")
	public Date getAddtime() {
		return this.addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
		@Column(name="PROVINCE")
	public String getProvince() {
		return this.province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
		@Column(name="CITY")
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
		@Column(name="DISTRICT")
	public String getDistrict() {
		return this.district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
		
	@Embedded

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ldysubid == null) ? 0 : ldysubid.hashCode());
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
		final Ldysubmit other = (Ldysubmit) obj;
		if (ldysubid == null) {
			if (other.ldysubid != null)
				return false;
		} else if (!ldysubid.equals(other.ldysubid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Ldysubmit ( "
	        + "Ldysubid = " + this.ldysubid + TAB
	        + "Ldyitemid = " + this.ldyitemid + TAB
	        + "Ldylinkid = " + this.ldylinkid + TAB
	        + "Substance = " + this.substance + TAB
	        + "Agent = " + this.agent + TAB
	        + "Ip = " + this.ip + TAB
	        + "Uniqueid = " + this.uniqueid + TAB
	        + "Addtime = " + this.addtime + TAB
	        + "Province = " + this.province + TAB
	        + "City = " + this.city + TAB
	        + "District = " + this.district + TAB
	        + " )";
	
	    return str;
	}

} 
