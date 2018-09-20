package com.bolaa.manage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




// 数据库表(Luoldyitems )映射对象
@Entity
@Table(name = "Luoldyitems")
public class Luoldyitems implements Serializable{

	private static final long serialVersionUID = 3822366855033830041L;
	
	// -----------------Properties (Instance Variables)----------------- //

	/* Primary key Column -- LUOLDYITEMID*/
	private Integer luoldyitemid = null;
	
	/* Column --  --length:10--decimalLen:0*/
		private Integer luodiyeid = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer ldyitemid = null;

	/* Column --  --length:10--decimalLen:0*/
		private Integer lorder = null;



	public Luoldyitems(){
	}

	// -----------------Accessors (getters and setters)----------------- //
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LUOLDYITEMID", insertable = true, updatable = true, nullable = false)
	public Integer getLuoldyitemid() {
		return this.luoldyitemid;
	}

	public void setLuoldyitemid(Integer luoldyitemid) {
		this.luoldyitemid = luoldyitemid;
	}
	 


		@Column(name="LUODIYEID")
	public Integer getLuodiyeid() {
		return this.luodiyeid;
	}
	public void setLuodiyeid(Integer luodiyeid) {
		this.luodiyeid = luodiyeid;
	}
	
		@Column(name="LDYITEMID")
	public Integer getLdyitemid() {
		return this.ldyitemid;
	}
	public void setLdyitemid(Integer ldyitemid) {
		this.ldyitemid = ldyitemid;
	}
	
		@Column(name="LORDER")
	public Integer getLorder() {
		return this.lorder;
	}
	public void setLorder(Integer lorder) {
		this.lorder = lorder;
	}
		
	@Embedded

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((luoldyitemid == null) ? 0 : luoldyitemid.hashCode());
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
		final Luoldyitems other = (Luoldyitems) obj;
		if (luoldyitemid == null) {
			if (other.luoldyitemid != null)
				return false;
		} else if (!luoldyitemid.equals(other.luoldyitemid))
			return false;
		return true;
	}
	
	public String toString()
	{
	    final String TAB = "    ";
	    String str = "";
	    str = "Luoldyitems ( "
	        + "Luoldyitemid = " + this.luoldyitemid + TAB
	        + "Luodiyeid = " + this.luodiyeid + TAB
	        + "Ldyitemid = " + this.ldyitemid + TAB
	        + "Lorder = " + this.lorder + TAB
	        + " )";
	
	    return str;
	}

} 
