package cn.mldn.house.servlet.front;

import javax.servlet.annotation.WebServlet;

import cn.mldn.house.factory.ServiceFactory;
import cn.mldn.house.util.action.DispatcherServlet;
import cn.mldn.house.vo.House;
import cn.mldn.house.vo.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/front/user/houseServlet/*")
public class HouseServlet extends DispatcherServlet {
	House house = new House();
	public String uploadPic() {
		System.out.println("id"+super.getRequest().getAttribute("id"));
		
		return "";
	}
	public String listHouseInfor() {
//		super.getRequest().setAttribute("path", "/pages/front/user/index.jsp");
//		super.getRequest().setAttribute("pathExt", "?pagePathName=listReleaseHouseLink");
//		super.getRequest().setAttribute("msg", "null");
//		super.getRequest().setAttribute("flag", "true");
//		return "forward.page";
		try {
			User user = (User) super.getSession().getAttribute("signUser");
			super.getRequest().setAttribute("allHouse", ServiceFactory.getHouseServiceInstance().list(user.getUid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user.house.release.list.page";
	}
	public String releaseHouseInfor() {
		try {
			User user = (User)super.getSession().getAttribute("signUser");
			System.out.println(user);
			System.out.println(house);
			if (ServiceFactory.getUserServiceInstance().isPerfact(user)) {
				this.house.getUser().setUid(user.getUid());
				this.house.setStatus(1);
				this.house.setVisits(0);
				if (ServiceFactory.getHouseServiceInstance().insert(this.house)) {
					super.getRequest().setAttribute("msg", "房屋发布成功！");
				}else{
					super.getRequest().setAttribute("msg", "房屋发布失败！");
				}
				super.getRequest().setAttribute("path", "/pages/front/user/index.jsp");
				super.getRequest().setAttribute("pathExt", "?pagePathName=releaseHouseLink");
				super.getRequest().setAttribute("flag", "true");
			}else {
				super.getRequest().setAttribute("path", "/pages/front/user/index.jsp");
				super.getRequest().setAttribute("pathExt", "?pagePathName=userDetailsLink");
				super.getRequest().setAttribute("msg", "用户信息不完善,无法发布房屋,现在去完善信息?");
				super.getRequest().setAttribute("flag", "true");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward.page";
	}
	public House getHouse() {
		return house;
	}
	@Override
	public String getObjectName() {
		return "房屋";
	}

	@Override
	public String getDefaultColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnData() {
		// TODO Auto-generated method stub
		return null;
	}

}
