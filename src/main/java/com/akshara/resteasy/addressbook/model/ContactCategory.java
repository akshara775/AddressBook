package com.akshara.resteasy.addressbook.model;

public enum ContactCategory {
	HOMEADDRESS(0),
	WORKADDRESS(1),
	HOMEPHONE(2),
	WORKPHONE(3),
	CELLPHONE(4),
	FAX(5),
	EMAILID(6);
	
	private int category;

	private ContactCategory(int category) {
		this.category = category;
	}

	public int getCategory() {
		return category;
	}

}
