package com.fjhw.entity;

public class XZJJ {
	/**���*/
	private String order;
	/**������*/
	private String downtown;
	/**����*/
	private String county;
	/**������*/
	private String townshipName;
	/**������*/
	private String townshipProfiles;
	/**ũҵ״��*/
	private String agricultureState;
	/**�����ĵ�·��*/
	private String path;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getAgricultureState() {
		return agricultureState;
	}
	public void setAgricultureState(String agricultureState) {
		this.agricultureState = agricultureState;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getDowntown() {
		return downtown;
	}
	public void setDowntown(String downtown) {
		this.downtown = downtown;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getTownshipName() {
		return townshipName;
	}
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}
	public String getTownshipProfiles() {
		return townshipProfiles;
	}
	public void setTownshipProfiles(String townshipProfiles) {
		this.townshipProfiles = townshipProfiles;
	}
	@Override
	public String toString() {
		return "XZJJ [order=" + order + ", downtown=" + downtown + ", county="
				+ county + ", townshipName=" + townshipName
				+ ", townshipProfiles=" + townshipProfiles
				+ ", agricultureState=" + agricultureState + ", path=" + path
				+ "]";
	}
	
}
