package cn.mldn.house.servlet.back;

import javax.servlet.annotation.WebServlet;

import cn.mldn.house.factory.ServiceFactory;
import cn.mldn.house.util.MD5Code;
import cn.mldn.house.util.action.DispatcherServlet;
import cn.mldn.house.vo.Admin;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/pages/back/admin/LoginServlet/*")
public class LoginServlet extends DispatcherServlet {
	Admin admin = new Admin();

	public String login() {
		try {
			Admin user = ServiceFactory.getAdminServiceInstance().login(
					this.admin.getAdid(),
					new MD5Code().getMD5ofStr(this.admin.getPassword()));
			if (user != null&&user.getAdid()!=null) {
				String code = super.getRequest().getParameter("code");
				String rand = (String) super.getSession().getAttribute("rand");
				if (rand.equalsIgnoreCase(code)) {
					super.getSession().setAttribute("user", user);
					super.setPathAndMsg("admin.login.servlet",
							"admin.login.success");
					
				} else {
					super.setPathAndMsg("admin.login.page",
							"admin.login.code.failure");
				}
			} else {
				super.setPathAndMsg("admin.login.page",
						"admin.login.user.failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(this.admin.getAdid()+" - "+this.admin.getPassword()+" - "+super.getRequest().getParameter("code")+" - "+);

		return "forward.page";
	}

	public String list() {
		Admin user = (Admin) super.getSession().getAttribute("user");
		try {
			super.getRequest().setAttribute(
					"allPrivileges",
					ServiceFactory.getPrivilegeServiceInstance().listByRid(
							user.getRole().getRid()));
			super.getRequest().setAttribute(
					"allPrivilegeDetails",
					ServiceFactory.getPrivilegeDetailsServiceInstance()
							.listByRid(user.getRole().getRid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin.index.page";
	}

	@Override
	public String getObjectName() {
		return null;
	}

	@Override
	public String getDefaultColumn() {
		return null;
	}

	@Override
	public String getColumnData() {
		return null;
	}

	public Admin getAdmin() {
		return admin;
	}
}
