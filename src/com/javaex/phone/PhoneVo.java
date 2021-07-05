package com.javaex.phone;

public class PhoneVo {

	private int personID;
	private String name;
	private String hp;
	private String company;

	public PhoneVo() {
	}
	
	public PhoneVo(int personID) {
		this.personID = personID;
	}

	public PhoneVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public PhoneVo(int personID, String name, String hp, String company) {
		this.personID = personID;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "PhoneVo [personID=" + personID + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}

}
