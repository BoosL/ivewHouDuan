package com.bolaa.manage.model.req;

import java.util.List;

import com.bolaa.manage.entity.Luodiye;

public class ReqAddLuodiyeMod {

	private Luodiye luodiye;

	private List<ReqLdyitemsMod> items;

	public Luodiye getLuodiye() {
		return luodiye;
	}

	public void setLuodiye(Luodiye luodiye) {
		this.luodiye = luodiye;
	}

	public List<ReqLdyitemsMod> getItems() {
		return items;
	}

	public void setItems(List<ReqLdyitemsMod> items) {
		this.items = items;
	}

	public ReqAddLuodiyeMod(Luodiye luodiye, List<ReqLdyitemsMod> items) {
		super();
		this.luodiye = luodiye;
		this.items = items;
	}

	public ReqAddLuodiyeMod() {
		super();
	}

	@Override
	public String toString() {
		return "ReqAddLuodiyeMod [luodiye=" + luodiye + ", items=" + items + "]";
	}

}
