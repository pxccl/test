package cn.mldn.house.factory;

import java.sql.Connection;

import cn.mldn.house.dao.IAdminDAO;
import cn.mldn.house.dao.IHouseDAO;
import cn.mldn.house.dao.IMessageDAO;
import cn.mldn.house.dao.IPrivilegeDAO;
import cn.mldn.house.dao.IPrivilegeDetailsDAO;
import cn.mldn.house.dao.IUserDAO;
import cn.mldn.house.dao.impl.AdminDAOImpl;
import cn.mldn.house.dao.impl.HouseDAOImpl;
import cn.mldn.house.dao.impl.MessageDAOImpl;
import cn.mldn.house.dao.impl.PrivilegeDAOImpl;
import cn.mldn.house.dao.impl.PrivilegeDetailsDAOImpl;
import cn.mldn.house.dao.impl.UserDAOImpl;

public class DAOFactory {
	public static IAdminDAO getAdminDAOImplInstance(Connection connection) {
		return new AdminDAOImpl(connection);
	}
//	public static IRoleDAO getRoleDAOImplInstance(Connection connection) {
//		return new RoleDAOImpl(connection);
//	}
	public static IPrivilegeDAO getPrivilegeDAOImplInstance(Connection connection) {
		return new PrivilegeDAOImpl(connection);
	}
	public static IPrivilegeDetailsDAO getPrivilegeDetailsDAO(Connection connection){
		return new PrivilegeDetailsDAOImpl(connection);
	}
	public static IUserDAO getUserDAOInstance(Connection connection) {
		return new UserDAOImpl(connection);
	}
	public static IHouseDAO getHouseDAOInstance(Connection connection) {
		return new HouseDAOImpl(connection);
	}
	public static IMessageDAO getMessageDAOInstance(Connection connection){
		return new MessageDAOImpl(connection);
	}
}
