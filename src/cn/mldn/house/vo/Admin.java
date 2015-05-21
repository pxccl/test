package cn.mldn.house.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Admin implements Serializable {
	private String adid;
	private String password;
	private Date lastlogin;
	private Integer flag;
	private Role role = new Role();
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Admin [adid=" + adid  + ", password="
				+ password + ", lastlogin=" + lastlogin + ", flag=" + flag
				+ "]";
	}
}
