package com.bolaa.manage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




// 数据库表(ADClass )映射对象
@Entity
@Table(name = "ADClass")
public class Adclass implements Serializable{

	private static final long serialVersionUID = 266106509988765625L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- CLASSID*/
	private Integer classid = null;
	
	/* Column --  --length:50--decimalLen:0*/
		private String classname = null;



	public Adclass(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLASSID", insertable = true, updatable = true, nullable = false)
	public Integer getClassid() {
		return this.classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}
	 


		@Column(name="CLASSNAME")
	public String getClassname() {
		return this.classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
		
	@Embedded

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classid == null) ? 0 : classid.hashCode());
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
		final Adclass other = (Adclass) obj;
		if (classid == null) {
			if (other.classid != null)
				return false;
		} else if (!classid.equals(other.classid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Adclass ( "
	        + "Classid = " + this.classid + TAB
	        + "Classname = " + this.classname + TAB
	        + " )";
	
	    return str;
	}

} 
