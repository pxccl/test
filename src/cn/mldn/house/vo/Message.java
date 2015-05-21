package cn.mldn.house.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Message implements Serializable {
	private Integer mid;
//	private String suid;
//	private String ruid;
	private User suser = new User();
	private User ruser = new User();
	private String title;
	private String note;
	private Integer status;
	private Date pubdate;
	private Integer seq;
	private String conn;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public User getSuser() {
		return suser;
	}
	public void setSuser(User suser) {
		this.suser = suser;
	}
	public User getRuser() {
		return ruser;
	}
	public void setRuser(User ruser) {
		this.ruser = ruser;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getConn() {
		return conn;
	}
	public void setConn(String conn) {
		this.conn = conn;
	}
	@Override
	public String toString() {
		return "Message [mid=" + mid 
				+ ", title=" + title + ", note=" + note + ", status=" + status
				+ ", pubdate=" + pubdate + ", seq=" + seq + ", conn=" + conn
				+ "]";
	}
	
}
