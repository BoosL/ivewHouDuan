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

// 数据库表(ADChannel )映射对象
@Entity
@Table(name = "ADChannel")
public class Adchannel implements Serializable{

	private static final long serialVersionUID = 2892955219091481600L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- CHANNELID*/
	private Integer channelid = null;
	
	/* Column --  --length:50--decimalLen:0*/
		private String channelname = null;

	/* Column --  --length:50--decimalLen:0*/
		private String channelcode = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer adduserid = null;

	/* Column --  --length:19--decimalLen:0*/
		private Date addtime = null;



	public Adchannel(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHANNELID", insertable = true, updatable = true, nullable = false)
	public Integer getChannelid() {
		return this.channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}
	 


		@Column(name="CHANNELNAME")
	public String getChannelname() {
		return this.channelname;
	}
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	
		@Column(name="CHANNELCODE")
	public String getChannelcode() {
		return this.channelcode;
	}
	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
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
		result = prime * result + ((channelid == null) ? 0 : channelid.hashCode());
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
		final Adchannel other = (Adchannel) obj;
		if (channelid == null) {
			if (other.channelid != null)
				return false;
		} else if (!channelid.equals(other.channelid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Adchannel ( "
	        + "Channelid = " + this.channelid + TAB
	        + "Channelname = " + this.channelname + TAB
	        + "Channelcode = " + this.channelcode + TAB
	        + "Adduserid = " + this.adduserid + TAB
	        + "Addtime = " + this.addtime + TAB
	        + " )";
	
	    return str;
	}

} 
