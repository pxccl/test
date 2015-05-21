package cn.mldn.house.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Privilege implements Serializable {
	private Integer pid;
	private String title;
	private String note;
	private List<PrivilegeDetails> privilegeDetails = new ArrayList<PrivilegeDetails>();
	
	public List<PrivilegeDetails> getPrivilegeDetails() {
		return privilegeDetails;
	}
	public void setPrivilegeDetails(List<PrivilegeDetails> privilegeDetails) {
		this.privilegeDetails = privilegeDetails;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Privilege [pid=" + pid + ", title=" + title + ", note=" + note
				+ "]";
	}
	
}
