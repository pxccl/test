package cn.mldn.house.factory;

import cn.mldn.house.service.IAdminService;
import cn.mldn.house.service.IHouseService;
import cn.mldn.house.service.IMessageService;
import cn.mldn.house.service.IPrivilegeDetailsService;
import cn.mldn.house.service.IPrivilegeService;
import cn.mldn.house.service.IUserService;
import cn.mldn.house.service.impl.AdminServiceImpl;
import cn.mldn.house.service.impl.HouseServiceImpl;
import cn.mldn.house.service.impl.MessageServiceImpl;
import cn.mldn.house.service.impl.PrivilegeDetailsServiceImpl;
import cn.mldn.house.service.impl.PrivilegeServiceImpl;
import cn.mldn.house.service.impl.UserServiceImpl;

public class ServiceFactory {
	public static IAdminService getAdminServiceInstance() {
		return new AdminServiceImpl();
	}
	public static IPrivilegeService getPrivilegeServiceInstance(){
		return new PrivilegeServiceImpl();
	}
	public static IPrivilegeDetailsService getPrivilegeDetailsServiceInstance(){
		return new PrivilegeDetailsServiceImpl();
	}
	public static IUserService getUserServiceInstance() {
		return new UserServiceImpl();
	}
	public static IHouseService getHouseServiceInstance() {
		return  new HouseServiceImpl();
	}
	public static IMessageService getMessageServiceInstance(){
		return new MessageServiceImpl();
	}
}
