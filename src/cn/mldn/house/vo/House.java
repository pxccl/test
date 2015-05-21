package cn.mldn.house.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class House implements Serializable {
	 private Integer hsid;
	 private User user = new User();
	 private String community;
	 private String address;
	 private Double area;
	 private String total;
	 private String floor;
	 private String orientation;
	 private String structure;
	 private String renovation;
	 private Date fdate;
	 private Integer status;
	 private Double price;
	 private Integer visits;
	 private String type;
	 private String note;
	public Integer getHsid() {
		return hsid;
	}
	public void setHsid(Integer hsid) {
		this.hsid = hsid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getRenovation() {
		return renovation;
	}
	public void setRenovation(String renovation) {
		this.renovation = renovation;
	}
	public Date getFdate() {
		return fdate;
	}
	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getVisits() {
		return visits;
	}
	public void setVisits(Integer visits) {
		this.visits = visits;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "House [hsid=" + hsid + ",  community="
				+ community + ", address=" + address + ", area=" + area
				+ ", total=" + total + ", floor=" + floor + ", orientation="
				+ orientation + ", structure=" + structure + ", renovation="
				+ renovation + ", fdate=" + fdate + ", status=" + status
				+ ", price=" + price + ", visits=" + visits + ", type=" + type
				+ ", note=" + note + "]";
	}

}
