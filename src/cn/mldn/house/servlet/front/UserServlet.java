package cn.mldn.house.servlet.front;

import javax.servlet.annotation.WebServlet;

import cn.mldn.house.factory.ServiceFactory;
import cn.mldn.house.util.MD5Code;
import cn.mldn.house.util.action.DispatcherServlet;
import cn.mldn.house.vo.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/pages/front/userServlet/*","/pages/front/user/userServlet/*"})
public class UserServlet extends DispatcherServlet {
	private User user = new User();
	
	public String releaseHouse() {
		try {
			if (ServiceFactory.getUserServiceInstance().isPerfact(this.user)) {
				super.getRequest().setAttribute("path", "/pages/front/user/index.jsp");
				super.getRequest().setAttribute("pathExt", "?pagePathName=releaseHouseLink");
				super.getRequest().setAttribute("msg", "null");
				super.getRequest().setAttribute("flag", "true");
			}else {
				super.getRequest().setAttribute("path", "/pages/front/user/index.jsp");
				super.getRequest().setAttribute("pathExt", "?pagePathName=userDetailsLink");
				super.getRequest().setAttribute("msg", "用户信息不完善,无法发布房屋,现在去完善信息?");
				super.getRequest().setAttribute("flag", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	public String updateUser() {
		try {
			String oldPhoto = this.user.getPhoto();
			String photo = "nophoto.jsp";
			if (oldPhoto != null &&!"".equals(oldPhoto)) {
				photo = oldPhoto;
			}else {
				photo = super.createSingleFileName();
			}
			this.user.setPhoto(photo);
			if (ServiceFactory.getUserServiceInstance().updateUserDetails(
					this.user)) {
				super.saveFile(this.user.getPhoto());
				this.user = ServiceFactory.getUserServiceInstance().login(this.user);
				super.getSession().setAttribute(
						"signUser",this.user);
				super.setPathAndMsg("user.update.details.page",
						"user.update.details.success");
			}
		} catch (Exception e) {

		}
		return "forward.page";
	}

	public String updatePasswd() {
		try {
			User vo = (User) super.getSession().getAttribute("signUser");
			String oldpassword = new MD5Code().getMD5ofStr(super.getRequest()
					.getParameter("oldpassword"));

			if (vo.getPassword().equals(oldpassword)) {
				vo.setPassword(new MD5Code().getMD5ofStr(super.getRequest()
						.getParameter("newpassword")));
				if (ServiceFactory.getUserServiceInstance().updatePasswd(vo)) {
					super.setPathAndMsg("user.update.password.page",
							"user.update.password.success");
				}
			} else {
				super.setPathAndMsg("user.update.password.page",
						"user.update.password.failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "forward.page";
	}

	public String signIn() {
		try {
			this.user.setPassword(new MD5Code().getMD5ofStr(this.user
					.getPassword()));
			User user = ServiceFactory.getUserServiceInstance()
					.login(this.user);
			if (user != null) {
				if (user.getStatus() == 1) {
					String code = super.getRequest().getParameter("code");
					String rand = (String) super.getSession().getAttribute(
							"rand");
					if (rand.equalsIgnoreCase(code)) {
						if (ServiceFactory.getUserServiceInstance()
								.updateLastLoginDate(user.getUid())) {
							super.getSession().setAttribute("signUser", user);
							this.user = user;
							super.setPathAndMsg("user.index.page",
									"user.login.success");
						}
					} else {
						super.setPathAndMsg("user.login.page",
								"user.login.code.failure");
					}
				} else if (user.getStatus() == 2) {
					super.setPathAndMsg("user.login.page",
							"user.login.status2.failure");
				} else {
					super.setPathAndMsg("user.login.page",
							"user.login.status.failure");
				}

			} else {
				super.setPathAndMsg("user.login.page",
						"user.login.user.failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(this.admin.getAdid()+" - "+this.admin.getPassword()+" - "+super.getRequest().getParameter("code")+" - "+);

		return "forward.page";
	}

	public String signUp() {
		user.setPassword(new MD5Code().getMD5ofStr(user.getPassword()));
		try {
			if (user.getUid() != null && !"".equals(user.getUid().trim())) {
				if (ServiceFactory.getUserServiceInstance().noExistsUser(
						user.getUid())) {
					user.setStatus(1);
					if (ServiceFactory.getUserServiceInstance().regeditUser(
							user)) {
						super.setPathAndMsg("user.login.page",
								"user.signup.success");
					}
				} else {
					super.setPathAndMsg("user.regedit.page",
							"user.signup.failure.user.exists");
				}
			} else {
				super.setPathAndMsg("user.regedit.page",
						"user.signup.failure.user.null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}

	public String logout() {
		super.getSession().invalidate();
		super.setPathAndMsg("user.login.page", "user.logout.success");
		return "forward.page";
	}

	@Override
	public String getObjectName() {
		return "";
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

	public User getUser() {
		return user;
	}
}
