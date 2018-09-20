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

// 数据库表(CostCalculate )映射对象
@Entity
@Table(name = "CostCalculate")
public class Costcalculate implements Serializable{

	private static final long serialVersionUID = 27144537229416049L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- COSTID*/
	private Integer costid = null;
	
	/* Column --  --length:10--decimalLen:0*/
		private Integer pid = null;

	/* Column --  --length:19--decimalLen:0*/
		private Date begintime = null;

	/* Column --  --length:19--decimalLen:0*/
		private Date endtime = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer ldylinkid = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer channelid = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer ipcount = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer pvcount = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer uvcount = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer subcount = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer sum = null;

	/* Column --  --length:8--decimalLen:2*/
		private Float cost = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer adduserid = null;

	/* Column --  --length:19--decimalLen:0*/
		private Date addtime = null;



	public Costcalculate(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COSTID", insertable = true, updatable = true, nullable = false)
	public Integer getCostid() {
		return this.costid;
	}

	public void setCostid(Integer costid) {
		this.costid = costid;
	}
	 


		@Column(name="PID")
	public Integer getPid() {
		return this.pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	
		@Column(name="LDYLINKID")
	public Integer getLdylinkid() {
		return this.ldylinkid;
	}
	public void setLdylinkid(Integer ldylinkid) {
		this.ldylinkid = ldylinkid;
	}
	
		@Column(name="CHANNELID")
	public Integer getChannelid() {
		return this.channelid;
	}
	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}
	
		@Column(name="IPCOUNT")
	public Integer getIpcount() {
		return this.ipcount;
	}
	public void setIpcount(Integer ipcount) {
		this.ipcount = ipcount;
	}
	
		@Column(name="PVCOUNT")
	public Integer getPvcount() {
		return this.pvcount;
	}
	public void setPvcount(Integer pvcount) {
		this.pvcount = pvcount;
	}
	
		@Column(name="UVCOUNT")
	public Integer getUvcount() {
		return this.uvcount;
	}
	public void setUvcount(Integer uvcount) {
		this.uvcount = uvcount;
	}
	
		@Column(name="SUBCOUNT")
	public Integer getSubcount() {
		return this.subcount;
	}
	public void setSubcount(Integer subcount) {
		this.subcount = subcount;
	}
	
		@Column(name="SUM")
	public Integer getSum() {
		return this.sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	
		@Column(name="COST")
	public Float getCost() {
		return this.cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
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
		result = prime * result + ((costid == null) ? 0 : costid.hashCode());
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
		final Costcalculate other = (Costcalculate) obj;
		if (costid == null) {
			if (other.costid != null)
				return false;
		} else if (!costid.equals(other.costid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Costcalculate ( "
	        + "Costid = " + this.costid + TAB
	        + "Pid = " + this.pid + TAB
	        + "Begintime = " + this.begintime + TAB
	        + "Endtime = " + this.endtime + TAB
	        + "Ldylinkid = " + this.ldylinkid + TAB
	        + "Channelid = " + this.channelid + TAB
	        + "Ipcount = " + this.ipcount + TAB
	        + "Pvcount = " + this.pvcount + TAB
	        + "Uvcount = " + this.uvcount + TAB
	        + "Subcount = " + this.subcount + TAB
	        + "Sum = " + this.sum + TAB
	        + "Cost = " + this.cost + TAB
	        + "Adduserid = " + this.adduserid + TAB
	        + "Addtime = " + this.addtime + TAB
	        + " )";
	
	    return str;
	}

} 
