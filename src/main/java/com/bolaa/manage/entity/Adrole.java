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

// 数据库表(ADRole )映射对象
@Entity
@Table(name = "ADRole")
public class Adrole implements Serializable{

	private static final long serialVersionUID = 3828883120931797681L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- ROLEID*/
	private Integer roleid = null;
	
	/* Column --  --length:50--decimalLen:0*/
		private String rolename = null;

	/* Column --  --length:200--decimalLen:0*/
		private String rolenote = null;

	/* Column --  --length:3--decimalLen:0*/
		private String rolestatus = null;




	public Adrole(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLEID", insertable = true, updatable = true, nullable = false)
	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	 


		@Column(name="ROLENAME")
	public String getRolename() {
		return this.rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
		@Column(name="ROLENOTE")
	public String getRolenote() {
		return this.rolenote;
	}
	public void setRolenote(String rolenote) {
		this.rolenote = rolenote;
	}
	
		@Column(name="ROLESTATUS")
	public String getRolestatus() {
		return this.rolestatus;
	}
	public void setRolestatus(String rolestatus) {
		this.rolestatus = rolestatus;
	}
	
		
	@Embedded

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleid == null) ? 0 : roleid.hashCode());
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
		final Adrole other = (Adrole) obj;
		if (roleid == null) {
			if (other.roleid != null)
				return false;
		} else if (!roleid.equals(other.roleid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Adrole ( "
	        + "Roleid = " + this.roleid + TAB
	        + "Rolename = " + this.rolename + TAB
	        + "Rolenote = " + this.rolenote + TAB
	        + "Rolestatus = " + this.rolestatus + TAB
	        + " )";
	
	    return str;
	}

} 
