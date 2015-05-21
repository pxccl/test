package cn.mldn.house.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PrivilegeDetails implements Serializable {
	private Integer pdid;
	private String title;
	private String url;
	private Privilege privilege = new Privilege();
	
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "PrivilegeDetails [pdid=" + pdid + ", title=" + title + ", url="
				+ url + "]";
	} 
	
}
